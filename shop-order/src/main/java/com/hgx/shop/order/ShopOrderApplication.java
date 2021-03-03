package com.hgx.shop.order;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;


/**
 * 使用RabbitMQ
 * 1.引入AMQP场景；RabbitAutoConfiguration就会自动生效
 * 2.给容器中自动配置了
 *     RabbitTemplate、AmqpAdmin、CachingConnectionFactory、RabbitMessagingTemplate
 *     所有的属性都是 spring.reabbitmq
 *     @ConfigurationProperties(prefix = "spring.rabbitmq")
 *     public class RabbitProperties
 * 3.在配置文件中配置spring.rabbitmq信息
 * 4.@EnableRabbit：@EnableXxxxx  开启功能
 */
@EnableRedisHttpSession
@EnableRabbit
@MapperScan("com.hgx.shop.order.dao")
@EnableDiscoveryClient
@EnableFeignClients
@SpringBootApplication
public class ShopOrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShopOrderApplication.class, args);
    }

}
