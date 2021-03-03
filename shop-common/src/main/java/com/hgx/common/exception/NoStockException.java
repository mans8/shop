package com.hgx.common.exception;

/**
 * @author hgx
 * @date 2021/3/3 20:00
 * @Description
 */
public class NoStockException extends RuntimeException {

    private Long skuId;

    public NoStockException(Long skuId) {
        super("商品id:" + skuId + "没有足够的库存了");
    }

    public Long getSkuId() {
        return skuId;
    }

    public void setSkuId(Long skuId) {
        this.skuId = skuId;
    }
}
