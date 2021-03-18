package com.hgx.shop.ware.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author hgx
 * @date 2021/2/11 0:07
 * @Description
 */
@Data
public class OrderItemVo {

    private Long skuId;
    private Boolean check;
    private String title;
    private String image;
    private List<String> skuAttr;
    private BigDecimal price;
    private Integer count;
    private BigDecimal totalPrice;

    private BigDecimal weight;

}
