package com.hgx.shop.seckill.service;

import com.hgx.shop.seckill.to.SeckillSkuRedisTo;

import java.util.List;

/**
 * @author hgx
 * @date 2021/3/23 21:45
 * @Description
 */
public interface SeckillService {
    void uploadSeckillSkuLast3Days();

    List<SeckillSkuRedisTo> getCurrentSeckillSkus();

    SeckillSkuRedisTo getSkuSeckillInfo(Long skuId);

    String kill(String killId, String key, Integer num) throws InterruptedException;
}
