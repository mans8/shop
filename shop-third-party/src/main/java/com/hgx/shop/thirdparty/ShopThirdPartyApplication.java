package com.hgx.shop.thirdparty;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class ShopThirdPartyApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShopThirdPartyApplication.class, args);
    }

}
