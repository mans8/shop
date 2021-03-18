package com.hgx.common.to.mq;

import lombok.Data;

import java.util.List;

/**
 * @author hgx
 * @date 2021/3/18 21:31
 * @Description  锁库存
 */
@Data
public class StockLockedTo {

    //库存工作单的id
    private Long id;

    //工作单详情
    private StockDetailTo detail;

}
