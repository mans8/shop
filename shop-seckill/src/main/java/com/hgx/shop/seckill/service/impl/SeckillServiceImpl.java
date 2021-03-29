package com.hgx.shop.seckill.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.hgx.common.to.mq.SeckillOrderTo;
import com.hgx.common.utils.R;
import com.hgx.common.vo.MemberRespVo;
import com.hgx.shop.seckill.feign.CouponFeignService;
import com.hgx.shop.seckill.feign.ProductFeignService;
import com.hgx.shop.seckill.interceptor.LoginUserInterceptor;
import com.hgx.shop.seckill.service.SeckillService;
import com.hgx.shop.seckill.to.SeckillSkuRedisTo;
import com.hgx.shop.seckill.vo.SeckillSessionsWithSkus;
import com.hgx.shop.seckill.vo.SkuInfoVo;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RSemaphore;
import org.redisson.api.RedissonClient;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * @author hgx
 * @date 2021/3/23 21:46
 * @Description
 */
@Slf4j
@Service
public class SeckillServiceImpl implements SeckillService {

    @Autowired
    CouponFeignService couponFeignService;

    @Autowired
    ProductFeignService productFeignService;

    @Autowired
    StringRedisTemplate redisTemplate;

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Autowired
    RedissonClient redissonClient;

    private final String SESSIONS_CACHE_PREFIX = "seckill:sessions";
    private final String SKUKILL_CACHE_PREFIX = "seckill:skus";
    private final String SKU_STOCK_SEMAPHORE = "seckill:stock:";//+商品随机码


    @Override
    public void uploadSeckillSkuLast3Days() {
        //1.扫描最近三天需要参与秒杀的活动
        R session = couponFeignService.getLastest3DaySession();
        if (session.getCode() == 0) {
            //上架商品
            List<SeckillSessionsWithSkus> sessionData = session.getData(new TypeReference<List<SeckillSessionsWithSkus>>() {
            });
            //缓存到redis
            //1.缓存活动信息
            saveSessionInfos(sessionData);
            //2.缓存活动的关联信息
            saveSessionSkuInfos(sessionData);

        }
    }

    /**
     * 返回当前时间可以参加的秒杀商品信息
     *
     * @return
     */
    @Override
    public List<SeckillSkuRedisTo> getCurrentSeckillSkus() {
        //1.确定当前时间属于哪个秒杀场次
        long time = new Date().getTime();

        Set<String> keys = redisTemplate.keys(SESSIONS_CACHE_PREFIX + "*");
        for (String key : keys) {
            //redis中保存格式seckill:sessions:1582250400000_158225400000
            String replace = key.replace(SESSIONS_CACHE_PREFIX, "");
            String[] s = replace.split("_");
            Long start = Long.parseLong(s[0]);
            Long end = Long.parseLong(s[1]);
            if (time >= start && time <= end) {
                //2.获取这个秒杀场次需要的所有商品信息
                List<String> range = redisTemplate.opsForList().range(key, -100, 100);
                BoundHashOperations<String, String, String> hashOps = redisTemplate.boundHashOps(SKUKILL_CACHE_PREFIX);
                List<String> list = hashOps.multiGet(range);
                if (list != null) {
                    List<SeckillSkuRedisTo> collect = list.stream().map(item -> {
                        SeckillSkuRedisTo redis = JSON.parseObject((String) item, SeckillSkuRedisTo.class);
                        return redis;
                    }).collect(Collectors.toList());
                    return collect;
                }
                break;
            }
        }

        return null;
    }

    @Override
    public SeckillSkuRedisTo getSkuSeckillInfo(Long skuId) {
        BoundHashOperations<String, String, String> hashOps = redisTemplate.boundHashOps(SKUKILL_CACHE_PREFIX);
        Set<String> keys = hashOps.keys();
        if (keys != null && keys.size() > 0) {
            String reg = "\\d_" + skuId;
            for (String key : keys) {
                if (Pattern.matches(reg, key)) {
                    String json = hashOps.get(key);
                    SeckillSkuRedisTo skuRedisTo = JSON.parseObject(json, SeckillSkuRedisTo.class);
                    //随机码
                    long current = new Date().getTime();
                    if (current >= skuRedisTo.getStartTime() && current <= skuRedisTo.getEndTime()) {

                    } else {
                        skuRedisTo.setRandomCode(null);
                    }
                    return skuRedisTo;
                }
            }
        }
        return null;
    }

    /**
     * 商品秒杀
     *
     * @param killId
     * @param key
     * @param num
     * @return
     */
    //TODO 上架秒杀商品的时候，每一个数据都有过期时间
    //TODO 秒杀后续的流程，简化了收货地址等信息
    @Override
    public String kill(String killId, String key, Integer num) {

        MemberRespVo respVo = LoginUserInterceptor.loginUser.get();
        //1.获取当前秒杀商品的详细信息
        BoundHashOperations<String, String, String> hashOps = redisTemplate.boundHashOps(SKUKILL_CACHE_PREFIX);
        String json = hashOps.get(killId);
        if (StringUtils.isEmpty(json)) {
            return null;
        } else {
            SeckillSkuRedisTo redis = JSON.parseObject(json, SeckillSkuRedisTo.class);
            //校验合法性
            Long startTime = redis.getStartTime();
            Long endTime = redis.getEndTime();
            long time = new Date().getTime();
            long ttl = endTime - time;
            //1.校验时间的合法性
            if (time >= startTime && time <= endTime) {
                //2.校验随机码和商品id
                String randomCode = redis.getRandomCode();
                String skuId = redis.getPromotionSessionId() + "_" + redis.getSkuId();
                if (randomCode.equals(key) && killId.equals(skuId)) {
                    //3.验证购物数量是否合理
                    if (num <= redis.getSeckillLimit()) {
                        //4.验证这个人是否已经购买过。幂等性；如果只要秒杀成功，就去占位。userId_SessionId_skuId_
                        String redisKey = respVo.getId() + "_" + skuId;
                        //自动过期
                        Boolean aBoolean = redisTemplate.opsForValue().setIfAbsent(redisKey, num.toString(), ttl, TimeUnit.MILLISECONDS);
                        if (aBoolean) {
                            //占位成功说明从来没有买过
                            RSemaphore semaphore = redissonClient.getSemaphore(SKUKILL_CACHE_PREFIX + randomCode);
                            //获取一次
                            boolean b = semaphore.tryAcquire(num);
                            if (b) {
                                //秒杀成功
                                //快速成功，发送MQ消息     10ms
                                String timeId = IdWorker.getTimeId();
                                SeckillOrderTo orderTo = new SeckillOrderTo();
                                orderTo.setOrderSn(timeId);
                                orderTo.setMemberId(respVo.getId());
                                orderTo.setNum(num);
                                orderTo.setPromotionSessionId(redis.getPromotionSessionId());
                                orderTo.setSkuId(redis.getSkuId());
                                orderTo.setSeckillPrice(redis.getSeckillPrice());
                                rabbitTemplate.convertAndSend("order-event-exchange", "order.seckill.order", orderTo);
                                return timeId;
                            }
                            return null;
                        } else {
                            //说明已经买过了
                            return null;
                        }
                    }
                } else {
                    return null;
                }
            } else {
                return null;
            }

        }
        return null;
    }

    private void saveSessionInfos(List<SeckillSessionsWithSkus> sessions) {

        sessions.stream().forEach(session -> {
            Long startTime = session.getStartTime().getTime();
            Long endTime = session.getEndTime().getTime();
            String key = SESSIONS_CACHE_PREFIX + startTime + "_" + endTime;
            Boolean hasKey = redisTemplate.hasKey(key);
            if (!hasKey) {
                List<String> collect =
                        session.getRelationSkus().stream().map(item -> item.getPromotionSessionId() + "_" + item.getSkuId().toString()).collect(Collectors.toList());
                //缓存活动信息
                redisTemplate.opsForList().leftPushAll(key, collect);
            }
        });

    }

    /**
     * 保存秒杀商品的sku信息
     * @param sessions
     */
    private void saveSessionSkuInfos(List<SeckillSessionsWithSkus> sessions) {
        sessions.stream().forEach(session -> {
            //准备hash操作
            BoundHashOperations<String, Object, Object> ops = redisTemplate.boundHashOps(SKUKILL_CACHE_PREFIX);
            session.getRelationSkus().stream().forEach(seckillSkuVo -> {
                //4.随机码
                String token = UUID.randomUUID().toString().replace("-", "");

                if (!ops.hasKey(seckillSkuVo.getPromotionSessionId().toString() + "_" + seckillSkuVo.getSkuId().toString())) {
                    //缓存商品
                    SeckillSkuRedisTo redisTo = new SeckillSkuRedisTo();
                    //1.sku的基本信息
                    R skuInfo = productFeignService.getSkuInfo(seckillSkuVo.getSkuId());
                    if (skuInfo.getCode() == 0) {
                        SkuInfoVo info = skuInfo.getData("skuInfo", new TypeReference<SkuInfoVo>() {
                        });
                        redisTo.setSkuInfo(info);
                    }

                    //2.sku的秒杀信息
                    BeanUtils.copyProperties(seckillSkuVo, redisTo);

                    //3.设置当前商品的秒杀时间信息
                    redisTo.setStartTime(session.getStartTime().getTime());
                    redisTo.setEndTime(session.getEndTime().getTime());

                    //设置限购数量
                    redisTo.setSeckillLimit(3);
                    //设置秒杀随机码
                    redisTo.setRandomCode(token);

                    String jsonString = JSON.toJSONString(redisTo);
                    ops.put(seckillSkuVo.getPromotionSessionId().toString() + "_" + seckillSkuVo.getSkuId().toString(), jsonString);

                    //如果当前这个场次的商品的库存信息已经上架，就不需要上架
                    //5.使用库存作为分布式信号量，最大的作用是限流
                    RSemaphore semaphore = redissonClient.getSemaphore(SKU_STOCK_SEMAPHORE + token);
                    //商品可以秒杀的数量作为信号量
                    semaphore.trySetPermits(seckillSkuVo.getSeckillCount().intValue());
                }


            });
        });

    }
}
