package com.hgx.shop.ware.vo;

import lombok.Data;

/**
 * @author hgx
 * @date 2021/3/2 22:22
 * @Description 锁定库存结果
 */
@Data
public class LockStockResult {

    private Long skuId;
    private Integer num;
    private Boolean locked;
}
