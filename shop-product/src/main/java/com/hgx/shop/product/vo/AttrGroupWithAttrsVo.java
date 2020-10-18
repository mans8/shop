package com.hgx.shop.product.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.hgx.shop.product.entity.AttrEntity;
import lombok.Data;

import java.util.List;

/**
 * @author hgx
 * @date 2020/10/18 16:07
 * @Description
 */
@Data
public class AttrGroupWithAttrsVo {

    /**
     * 分组id
     */
    private Long attrGroupId;
    /**
     * 组名
     */
    private String attrGroupName;
    /**
     * 排序
     */
    private Integer sort;
    /**
     * 描述
     */
    private String descript;
    /**
     * 组图标
     */
    private String icon;
    /**
     * 所属分类id
     */
    private Long catelogId;

    private List<AttrEntity> attrs;

}
