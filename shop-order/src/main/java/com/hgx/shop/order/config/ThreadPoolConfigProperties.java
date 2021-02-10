package com.hgx.shop.order.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author hgx
 * @date 2021/2/1 22:40
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
