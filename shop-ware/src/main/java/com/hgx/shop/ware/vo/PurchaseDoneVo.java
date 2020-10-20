package com.hgx.shop.ware.vo;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author hgx
 * @date 2020/10/20 11:14
 * @Description
 */
@Data
public class PurchaseDoneVo {

    @NotNull
    private Long id;//采购单id

    private List<PurchaseItemDoneVo> items;

}
