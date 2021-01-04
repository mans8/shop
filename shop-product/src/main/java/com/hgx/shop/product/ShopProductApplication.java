package com.hgx.shop.product;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;


/*
1.整合mybatis-plus
（1）导入依赖
<dependency>
<groupId>com.baomidou</groupId>
<artifactId>mybatis-plus-boot-starter</artifactId>
<version>3.2.0</version>
</dependency>
（2）配置数据源
导入数据库驱动
在application.yml配置数据源相关信息
（3）配置mybatis-plus
使用@MapperScan
在application.ym中告诉sql映射文件配置

*/
@EnableRedisHttpSession  //开启springsession
@EnableCaching      //开启缓存功能
@EnableFeignClients(basePackages = "com.hgx.shop.product.feign")
@MapperScan("com.hgx.shop.product.dao")
@EnableDiscoveryClient
@SpringBootApplication
public class ShopProductApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShopProductApplication.class, args);
    }

}
