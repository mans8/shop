package com.hgx.shop.product.vo;

import lombok.Data;

/**
 * @author hgx
 * @date 2020/10/17 20:30
 * @Description
 */
@Data
public class AttrRespVo extends AttrVo{

    /**
     * "catelogName":"手机/数码/手机"，所属分类名字
     * "groupName":"主体"，所属分组名字
     */
    private String catelogName;
    private String groupName;

    private Long[] catelogPath;

}
