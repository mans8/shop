package com.hgx.shop.product;

import com.aliyun.oss.OSSClient;
import com.hgx.shop.product.entity.SpuCommentEntity;
import com.hgx.shop.product.entity.SpuImagesEntity;
import com.hgx.shop.product.service.SpuCommentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

@SpringBootTest
class ShopProductApplicationTests {

    @Autowired
    SpuCommentService spuCommentService;

    @Autowired
    private OSSClient ossClient;

    /**
     * 测试上传图片到阿里云oss
     * @throws FileNotFoundException
     */
    @Test
    public void testUpload() throws FileNotFoundException {

        // 上传文件流。
        InputStream inputStream = new FileInputStream("C:\\Users\\Administrator\\Desktop\\360截图16240131415143.jpg");
        ossClient.putObject("shop-hgx", "360截图16240131415143.jpg", inputStream);

        // 关闭OSSClient。
        ossClient.shutdown();

    }

    @Test
    void contextLoads() {
        SpuCommentEntity spuCommentEntity = new SpuCommentEntity();
        spuCommentEntity.setContent("dfsdfsd");
        spuCommentEntity.setMemberIp("dsfsdf");
        spuCommentService.save(spuCommentEntity);
        System.out.println("保存成功");
    }

}
