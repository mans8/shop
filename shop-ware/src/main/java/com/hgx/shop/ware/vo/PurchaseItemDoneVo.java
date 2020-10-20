package com.hgx.shop.ware.vo;

import lombok.Data;

/**
 * @author hgx
 * @date 2020/10/20 11:16
 * @Description
 */
@Data
public class PurchaseItemDoneVo {
    private Long itemId;
    private Integer status;
    private String reason;
}
