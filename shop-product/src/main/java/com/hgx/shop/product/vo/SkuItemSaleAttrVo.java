package com.hgx.shop.product.vo;

import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 * @author hgx
 * @date 2020/11/28 17:11
 * @Description
 */
@Data
@ToString
public class SkuItemSaleAttrVo {

    private Long attrId;
    private String attrName;
    private List<String> attrValues;
}
