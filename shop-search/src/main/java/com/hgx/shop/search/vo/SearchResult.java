package com.hgx.shop.search.vo;

import com.hgx.common.to.es.SkuEsModel;
import lombok.Data;

import java.util.List;

/**
 * @author hgx
 * @date 2020/11/2 17:55
 * @Description
 */
@Data
public class SearchResult {

    //查询到的所有产品信息
    private List<SkuEsModel> product;

    private Integer pageNum;
    private Long total;
    private Integer totalPages;

    //当前查询到的结果，所有涉及的品牌
    private List<BrandVo> brands;
    //当前查询到的结果，所有涉及的所有分类
    private List<CatalogVo> catalogs;
    //当前查询到的结果，所有涉及的所有属性
    private List<AttrVo> attrs;

    //===============以上是所有返回给页面的信息====================

    @Data
    public static class BrandVo {
        private Long brandId;
        private String brandName;
        private String brandImg;
    }

    @Data
    public static class CatalogVo {
        private Long catalogId;
        private String catalogName;
    }

    @Data
    public static class AttrVo {
        private Long attrId;
        private String attrName;
        private List<String> attrValue;
    }
}
