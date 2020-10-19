package com.hgx.shop.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hgx.common.utils.PageUtils;
import com.hgx.shop.product.entity.SpuInfoDescEntity;

import java.util.Map;

/**
 * spu信息介绍
 *
 * @author hgx
 * @email man133@126.com
 * @date 2020-10-10 13:50:13
 */
public interface SpuInfoDescService extends IService<SpuInfoDescEntity> {

    PageUtils queryPage(Map<String, Object> params);

    void saveSpuInfoDesc(SpuInfoDescEntity descEntity);
}

