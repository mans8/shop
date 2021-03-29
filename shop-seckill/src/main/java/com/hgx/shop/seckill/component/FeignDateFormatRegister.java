package com.hgx.shop.seckill.component;

import java.util.Date;
import java.text.SimpleDateFormat;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.format.FormatterRegistry;
import org.springframework.core.convert.converter.Converter;
import org.springframework.cloud.openfeign.FeignFormatterRegistrar;

/**
 * @author hgx
 * @date 2021/3/27 1:26
 * @Description 解决feign服务端从客户端获取对象，时间相差14个小时的问题
 */
@Slf4j
@Component
public class FeignDateFormatRegister implements FeignFormatterRegistrar {

    public FeignDateFormatRegister() {
    }

    @Override
    public void registerFormatters(FormatterRegistry registry) {
        registry.addConverter(Date.class, String.class, new Date2StringConverter());
    }

    private class Date2StringConverter implements Converter<Date, String> {
        @Override
        public String convert(Date source) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            return sdf.format(source);
        }
    }
}
