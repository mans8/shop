package com.hgx.shop.member;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@MapperScan("com.hgx.shop.member.dao")
@EnableDiscoveryClient
@SpringBootApplication
public class ShopMemberApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShopMemberApplication.class, args);
    }

}
