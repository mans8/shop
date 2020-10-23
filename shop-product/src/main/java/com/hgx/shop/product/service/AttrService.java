package com.hgx.shop.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hgx.common.utils.PageUtils;
import com.hgx.shop.product.entity.AttrEntity;
import com.hgx.shop.product.vo.AttrGroupRelationVo;
import com.hgx.shop.product.vo.AttrRespVo;
import com.hgx.shop.product.vo.AttrVo;

import java.util.List;
import java.util.Map;

/**
 * 商品属性
 *
 * @author hgx
 * @email man133@126.com
 * @date 2020-10-10 13:50:13
 */
public interface AttrService extends IService<AttrEntity> {

    PageUtils queryPage(Map<String, Object> params);

    PageUtils getNoRelationAttr(Map<String, Object> params, Long attrgroupId);

    void saveAttr(AttrVo attr);

    PageUtils queryBaseAttrPage(Map<String, Object> params, Long catelogId, String type);

    AttrRespVo getAttrInfo(Long attrId);

    void updateAttr(AttrVo attr);

    List<AttrEntity> getRelationAttr(Long attrgrouId);

    void deleteRelation(AttrGroupRelationVo[] vos);

    List<Long> selectSearchAttrIds(List<Long> attrIds);
}

