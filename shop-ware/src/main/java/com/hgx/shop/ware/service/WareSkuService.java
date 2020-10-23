package com.hgx.shop.ware.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hgx.common.utils.PageUtils;
import com.hgx.shop.ware.entity.WareSkuEntity;
import com.hgx.shop.ware.vo.SkuHasStockVo;
import java.util.List;
import java.util.Map;

/**
 * 商品库存
 *
 * @author hgx
 * @email man133@126.com
 * @date 2020-10-10 16:40:15
 */
public interface WareSkuService extends IService<WareSkuEntity> {

    PageUtils queryPage(Map<String, Object> params);

    void addStock(Long skuId, Long wareId, Integer skuNum);

    List<SkuHasStockVo> getSkuHasStock(List<Long> skuIds);
}

