package com.hgx.shop.ware.feign;

import com.hgx.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author hgx
 * @date 2021/3/18 22:23
 * @Description
 */
@FeignClient("shop-order")
public interface OrderFeignService {

    //根据订单号远程查询订单
    @GetMapping("/order/order/status/{orderSn}")
    R getOrderStatus(@PathVariable("orderSn") String orderSn);
}
