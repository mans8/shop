package com.hgx.shop.order.feign;

import com.hgx.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * @author hgx
 * @date 2021/2/28 23:13
 * @Description 用于远程调用库存系统接口
 */
@FeignClient("shop-ware")
public interface WmsFeignService {

    @PostMapping("/ware/waresku/hasstock")
    R getSkuHasStock(@RequestBody List<Long> skuIds);
}
