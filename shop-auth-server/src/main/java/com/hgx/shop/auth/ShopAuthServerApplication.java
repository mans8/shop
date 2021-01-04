package com.hgx.shop.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@EnableRedisHttpSession  //整合redis作为session存储
@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
public class ShopAuthServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShopAuthServerApplication.class, args);
    }

}
