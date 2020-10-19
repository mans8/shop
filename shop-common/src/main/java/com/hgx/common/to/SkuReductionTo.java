package com.hgx.common.to;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author hgx
 * @date 2020/10/19 13:56
 * @Description
 */
@Data
public class SkuReductionTo {

    private Long skuId;
    private int fullCount;
    private BigDecimal discount;
    private int countStatus;
    private BigDecimal fullPrice;
    private BigDecimal reducePrice;
    private int priceStatus;
    private List<MemberPrice> memberPrice;
}
