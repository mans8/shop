package com.hgx.shop.ware.vo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author hgx
 * @date 2021/3/1 23:02
 * @Description
 */
@Data
public class FareVo {
    private MemberAddressVo address;
    private BigDecimal fare;
}
