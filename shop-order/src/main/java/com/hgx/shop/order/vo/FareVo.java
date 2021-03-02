package com.hgx.shop.order.vo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author hgx
 * @date 2021/3/2 21:21
 * @Description
 */
@Data
public class FareVo {

    private MemberAddressVo address;
    private BigDecimal fare;
}
