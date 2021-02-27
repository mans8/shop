package com.hgx.shop.order.feign;

import com.hgx.shop.order.vo.OrderItemVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * @author hgx
 * @date 2021/2/15 22:55
 * @Description
 */

@FeignClient("shop-cart")
public interface CartFeignService {

    @GetMapping(value = "/currentUserCartItems")
    List<OrderItemVo> getCurrentUserCartItems();
}
