package com.hgx.shop.product.vo;

import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 * @author hgx
 * @date 2020/11/28 17:09
 * @Description
 */
@Data
@ToString
public class SpuItemAttrGroupVo {

    private String groupName;
    private List<Attr> attrs;

}
