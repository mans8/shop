package com.hgx.shop.product.feign;

import com.hgx.common.to.es.SkuEsModel;
import com.hgx.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * @author hgx
 * @date 2020/10/23 17:35
 * @Description
 */
@FeignClient("shop-search")
public interface SearchFeignService {

    /**
     * 商品上架包旭数据到es
     * @param skuEsModels
     * @return
     */
    @PostMapping("/search/save/product")
    R productStatusUp(@RequestBody List<SkuEsModel> skuEsModels);

}
