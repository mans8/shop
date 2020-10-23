package com.hgx.shop.product.feign;

import com.hgx.common.to.SkuHasStockVo;
import com.hgx.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * @author hgx
 * @date 2020/10/23 16:06
 * @Description
 */
@FeignClient("shop-ware")
public interface WareFeignService {

    @PostMapping("/ware/waresku/hasstock")
    R getSkuHasStock(@RequestBody List<Long> skuIds);

}
