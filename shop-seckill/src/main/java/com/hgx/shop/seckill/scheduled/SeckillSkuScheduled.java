package com.hgx.shop.seckill.scheduled;

import com.hgx.shop.seckill.service.SeckillService;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @author hgx
 * @date 2021/3/23 21:38
 * @Description 秒杀商品定时上架
 */
@Slf4j
@Service
public class SeckillSkuScheduled {

    @Autowired
    SeckillService seckillService;

    @Autowired
    RedissonClient redissonClient;

    private final String upload_lock = "seckill:upload:lock";

    //TODO 幂等性处理
    @Scheduled(cron = "*/3 * * * * ?")
    public void uploadSeckillSkuLast3Days() {
        //1.重复上架无需处理
        log.info("上架秒杀的商品信息...");
        //使用redis分布式锁
        RLock lock = redissonClient.getLock(upload_lock);
        lock.lock(10, TimeUnit.SECONDS);
        try {
            seckillService.uploadSeckillSkuLast3Days();
        }finally {
            lock.unlock();
        }

    }

}
