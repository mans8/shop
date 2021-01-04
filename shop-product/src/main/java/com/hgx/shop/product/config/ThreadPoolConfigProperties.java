package com.hgx.shop.product.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author hgx
 * @date 2021/1/4 1:40
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
