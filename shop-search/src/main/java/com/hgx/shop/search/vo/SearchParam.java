package com.hgx.shop.search.vo;

import lombok.Data;

import java.util.List;

/**
 * @author hgx
 * @date 2020/11/2 21:39
 * @Description 封装页面所有可能传递过来的查询条件
 * catalog3Id=225&keyword=小米&sort=saleCount_asc&hasStock=0/1
 */
@Data
public class SearchParam {

    //页面传递过来的全文匹配关键字
    private String keyword;

    //三级分类id
    private Long catalog3Id;

    /**
     * 排序条件
     * sort = saleCount_asc/desc
     * sort = skuPrice_asc/desc
     * sort = hotScore_asc/desc
     */
    private String sort;

    //是否显示有货，0无库存，1有库存
    private Integer hasStock;

    //价格区间查询
    private String skuPrice;

    //按照品牌进行查询，可以多选
    private List<Long> brandId;

    //按照属性进行筛选
    private List<String> attrs;

    //页码
    private Integer pageNum = 1;

    private String _queryString;//原生的所有查询条件

}
