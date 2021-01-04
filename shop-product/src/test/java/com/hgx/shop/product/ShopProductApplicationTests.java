package com.hgx.shop.product;

import com.hgx.shop.product.dao.AttrGroupDao;
import com.hgx.shop.product.dao.SkuSaleAttrValueDao;
import com.hgx.shop.product.entity.SpuCommentEntity;
import com.hgx.shop.product.service.SpuCommentService;
import com.hgx.shop.product.vo.SkuItemSaleAttrVo;
import com.hgx.shop.product.vo.SpuItemAttrGroupVo;
import org.junit.jupiter.api.Test;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class ShopProductApplicationTests {

    @Autowired
    SpuCommentService spuCommentService;

    @Autowired
    RedissonClient redissonClient;

    @Autowired
    AttrGroupDao attrGroupDao;

    @Autowired
    SkuSaleAttrValueDao skuSaleAttrValueDao;

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
    public void redisson() {
        System.out.println(redissonClient);
    }

    @Test
    public void testGetAttrGroupWithAttrsBySpuId() {
        List<SpuItemAttrGroupVo> attrGroupWithAttrsBySpuId = attrGroupDao.getAttrGroupWithAttrsBySpuId(30L, 225L);
        System.out.println(attrGroupWithAttrsBySpuId);
    }

    @Test
    public void testGetSaleAttrsBySpuId() {
        List<SkuItemSaleAttrVo> saleAttrsBySpuId = skuSaleAttrValueDao.getSaleAttrsBySpuId(30L);
        System.out.println(saleAttrsBySpuId);
    }

}
