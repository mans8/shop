package com.hgx.shop.order.config;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @author hgx
 * @date 2021/2/26 21:24
 * @Description
 */
@Configuration
public class ShopFeignConfig {

    @Bean("requestInterceptor")
    public RequestInterceptor requestInterceptor() {
        return new RequestInterceptor() {
            @Override
            public void apply(RequestTemplate requestTemplate) {
                //1.RequestContextHolder拿到刚进来的这个请求
                ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
                if (attributes != null) {
                    //老请求
                    HttpServletRequest request = attributes.getRequest();
                    if (request != null) {
                        //同步请求头数据，cookie
                        String cookie = request.getHeader("Cookie");
                        //给新请求同步了老请求的cookie
                        requestTemplate.header("Cookie", cookie);
                    }
                }
            }
        };
    }

}
