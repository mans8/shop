package com.hgx.shop.product.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

/**
 * @author hgx
 * @date 2020/10/31 11:13
 * @Description
 */
@Configuration
public class MyRedisConfig {

    /**
     * 所有对redisson的使用都是通过redissonClient对象
     * @return
     */
    @Bean(destroyMethod = "shutdown")
    public RedissonClient redisson() throws IOException {

        //1.创建配置
        Config config = new Config();
        config.useSingleServer().setAddress("redis://192.168.1.80:6379");
        //2.根据config创建出redissonClient
        RedissonClient redissonClient = Redisson.create(config);
        return redissonClient;
    }

}

