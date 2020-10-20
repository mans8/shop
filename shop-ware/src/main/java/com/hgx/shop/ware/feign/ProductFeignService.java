package com.hgx.shop.ware.feign;

import com.hgx.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author hgx
 * @date 2020/10/20 12:46
 * @Description
 */
@FeignClient("shop-product")
public interface ProductFeignService {

    /**
     * /product/skuinfo/info/{skuId}
     * @param skuId
     * @return
     */
    @RequestMapping("/product/skuinfo/info/{skuId}")
    public R info(@PathVariable("skuId") Long skuId);

}
