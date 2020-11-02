package com.hgx.shop.product;

import com.hgx.shop.product.entity.SpuCommentEntity;
import com.hgx.shop.product.service.SpuCommentService;
import org.junit.jupiter.api.Test;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ShopProductApplicationTests {

    @Autowired
    SpuCommentService spuCommentService;

    @Autowired
    RedissonClient redissonClient;

    @Test
    void contextLoads() {
        SpuCommentEntity spuCommentEntity = new SpuCommentEntity();
        spuCommentEntity.setContent("dfsdfsd");
        spuCommentEntity.setMemberIp("dsfsdf");
        spuCommentService.save(spuCommentEntity);
        System.out.println("保存成功");
    }

    /**
     * 测试redisson是否配置成功
     */
    @Test
    public void redisson(){
        System.out.println(redissonClient);
    }



}
