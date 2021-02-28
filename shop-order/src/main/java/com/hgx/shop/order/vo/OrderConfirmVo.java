package com.hgx.shop.order.vo;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * @author hgx
 * @date 2021/2/10 21:57
 * @Description
 */
public class OrderConfirmVo {

    //收获地址，ums_member_receive_address表
    @Getter
    @Setter
    List<MemberAddressVo> address;

    //所有选中的购物项
    @Getter
    @Setter
    List<OrderItemVo> items;

    //发票......

    //优惠券信息...
    @Getter
    @Setter
    Integer integration;

    @Getter
    @Setter
    Map<Long,Boolean> stocks;

    //防止订单重复提交令牌
    @Getter
    @Setter
    String orderToken;

    public Integer getCount() {
        Integer i = 0;
        if (items != null) {
            for (OrderItemVo item : items) {
                i += item.getCount();
            }
        }
        return i;
    }

    //订单总额
    //BigDecimal total;

    public BigDecimal getTotal() {
        BigDecimal sum = new BigDecimal("0");
        if (items != null) {
            for (OrderItemVo item : items) {
                BigDecimal multiply = item.getPrice().multiply(new BigDecimal(item.getCount().toString()));
                sum.add(multiply);
            }
        }
        return sum;
    }

    //应付价格
    //BigDecimal payPrice;

    public BigDecimal getPayPrice() {
        return getTotal();
    }
}
