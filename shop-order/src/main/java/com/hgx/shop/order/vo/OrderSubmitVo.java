package com.hgx.shop.order.vo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author hgx
 * @date 2021/3/2 20:45
 * @Description 封装订单提交的数据
 */
@Data
public class OrderSubmitVo {

    //收获地址的id
    private Long addrId;

    //支付方式
    private Integer payType;

    //无需提交需要购买的商品，去购物车再获取一遍

    //优惠，发票

    //防重令牌
    private String orderToken;

    //应付价格，验价
    private BigDecimal payPrice;

    //订单备注
    private String note;

    //用户相关信息，直接去session取出登录用户




}
