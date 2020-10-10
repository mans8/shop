package com.hgx.shop.product;

import com.hgx.shop.product.entity.SpuCommentEntity;
import com.hgx.shop.product.entity.SpuImagesEntity;
import com.hgx.shop.product.service.SpuCommentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ShopProductApplicationTests {

    @Autowired
    SpuCommentService spuCommentService;

    @Test
    void contextLoads() {
        SpuCommentEntity spuCommentEntity = new SpuCommentEntity();
        spuCommentEntity.setContent("dfsdfsd");
        spuCommentEntity.setMemberIp("dsfsdf");
        spuCommentService.save(spuCommentEntity);
        System.out.println("保存成功");
    }

}
