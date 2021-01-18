package com.hgx.shop.cart.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author hgx
 * @date 2021/1/7 0:40
 * @Description
 */
@ConfigurationProperties(prefix = "shop.thread")
//@Component
@Data
public class ThreadPoolConfigProperties {

    private Integer coreSize;

    private Integer maxSize;

    private Integer keepAliveTime;
}
