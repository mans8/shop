package com.hgx.shop.cart.vo;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author hgx
 * @date 2021/1/7 22:50
 * @Description
 */
@Data
public class SkuInfoVo {

    private Long skuId;
    private Long spuId;
    private String skuName;
    private String skuDesc;
    private Long catalogId;
    private Long brandId;
    private String skuDefaultImg;
    private String skuTitle;
    private String skuSubtitle;
    private BigDecimal price;
    private Long saleCount;
}
