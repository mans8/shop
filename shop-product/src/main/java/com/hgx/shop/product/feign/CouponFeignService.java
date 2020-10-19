package com.hgx.shop.product.feign;

import com.hgx.common.to.SkuReductionTo;
import com.hgx.common.to.SpuBoundTo;
import com.hgx.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author hgx
 * @date 2020/10/19 13:15
 * @Description
 */
@FeignClient("shop-coupon")
public interface CouponFeignService {

    @PostMapping("/coupon/spubounds/save")
    R saveSpuBounds(@RequestBody SpuBoundTo spuBoundTo);

    @PostMapping("/coupon/skufullreduction/saveinfo")
    R saveSkuReduction(@RequestBody SkuReductionTo skuReductionTo);

}
