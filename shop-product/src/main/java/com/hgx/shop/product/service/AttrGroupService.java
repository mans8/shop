package com.hgx.shop.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hgx.common.utils.PageUtils;
import com.hgx.shop.product.entity.AttrGroupEntity;
import com.hgx.shop.product.vo.AttrGroupWithAttrsVo;

import java.util.List;
import java.util.Map;

/**
 * 属性分组
 *
 * @author hgx
 * @email man133@126.com
 * @date 2020-10-10 13:50:13
 */
public interface AttrGroupService extends IService<AttrGroupEntity> {

    PageUtils queryPage(Map<String, Object> params);

    PageUtils queryPage(Map<String, Object> params, Long catelogId);

    List<AttrGroupWithAttrsVo> getAttrGroupWithAttrsByCatelogId(Long catelogId);
}

