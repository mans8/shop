package com.hgx.shop.product.vo;

import com.hgx.shop.product.entity.SkuImagesEntity;
import com.hgx.shop.product.entity.SkuInfoEntity;
import com.hgx.shop.product.entity.SpuInfoDescEntity;
import com.hgx.shop.product.entity.SpuInfoEntity;
import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 * @author hgx
 * @date 2020/11/26 22:59
 * @Description 商品详情skuinfo
 */
@Data
public class SkuItemVo {
    //1、sku基本信息获取 pms_sku_info
    SkuInfoEntity info;

    //2、sku图片信息 pms_sku_images
    List<SkuImagesEntity> images;

    //3、获取spu的销售属性组合
    List<SkuItemSaleAttrVo> saleAttr;

    //4、获取spu介绍
    SpuInfoDescEntity desp;

    //5、获取spu规格参数信息
    List<SpuItemAttrGroupVo> groupAttrs;

}
