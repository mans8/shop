package com.hgx.shop.ware.vo;

import lombok.Data;

import java.util.List;

/**
 * @author hgx
 * @date 2021/3/2 22:15
 * @Description 锁库存
 */
@Data
public class WareSkuLockVo {
    //订单号
    private String ordeSn;

    //需要锁住的所有库存
    private List<OrderItemVo> locks;

}
