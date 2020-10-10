package com.hgx.shop.coupon;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@MapperScan("com.hgx.shop.coupon.dao")
@EnableDiscoveryClient
@SpringBootApplication
public class ShopCouponApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShopCouponApplication.class, args);
    }

}
