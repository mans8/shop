package com.hgx.shop.ware.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hgx.common.utils.PageUtils;
import com.hgx.shop.ware.entity.PurchaseEntity;
import com.hgx.shop.ware.vo.MergeVo;
import com.hgx.shop.ware.vo.PurchaseDoneVo;

import java.util.List;
import java.util.Map;

/**
 * 采购信息
 *
 * @author hgx
 * @email man133@126.com
 * @date 2020-10-10 16:40:15
 */
public interface PurchaseService extends IService<PurchaseEntity> {

    PageUtils queryPage(Map<String, Object> params);

    PageUtils queryPageUnreceivePurchase(Map<String, Object> params);

    void mergePurchase(MergeVo mergeVo);

    void received(List<Long> ids);

    void done(PurchaseDoneVo doneVo);
}

