package com.hgx.shop.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hgx.common.utils.PageUtils;
import com.hgx.shop.product.entity.SpuInfoDescEntity;
import com.hgx.shop.product.entity.SpuInfoEntity;
import com.hgx.shop.product.vo.SpuSaveVo;

import java.util.Map;

/**
 * spu信息
 *
 * @author hgx
 * @email man133@126.com
 * @date 2020-10-10 13:50:13
 */
public interface SpuInfoService extends IService<SpuInfoEntity> {

    PageUtils queryPage(Map<String, Object> params);

    void saveSpuInfo(SpuSaveVo vo);

    void saveBaseSpuInfo(SpuInfoEntity infoEntity);

    PageUtils queryPageByCondition(Map<String, Object> params);
}

