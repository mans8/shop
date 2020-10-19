package com.hgx.common.to;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author hgx
 * @date 2020/10/19 13:28
 * @Description
 */
@Data
public class SpuBoundTo {

    private Long spuId;
    private BigDecimal buyBounds;
    private BigDecimal growBounds;

}
