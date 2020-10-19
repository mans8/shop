package com.hgx.shop.ware.vo;

import lombok.Data;

import java.util.List;

/**
 * @author hgx
 * @date 2020/10/20 1:07
 * @Description
 */
@Data
public class MergeVo {

    //整单
    private Long purchaseId;

    //合并项集合
    private List<Long> items;
}
