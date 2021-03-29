package com.hgx.shop.seckill.feign;

import com.hgx.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author hgx
 * @date 2021/3/23 21:50
 * @Description
 */
@FeignClient("shop-coupon")
public interface CouponFeignService {

    @GetMapping("/coupon/seckillsession/lastest3DaySession")
    R getLastest3DaySession();

}
