package com.hgx.shop.order.vo;

import lombok.Data;

/**
 * @author hgx
 * @date 2021/2/28 23:17
 * @Description 是否有库存
 */
@Data
public class SkuStockVo {

    private Long skuId;
    private Boolean hasStock;
}
