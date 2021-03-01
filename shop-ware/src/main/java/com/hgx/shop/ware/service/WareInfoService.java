package com.hgx.shop.ware.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hgx.common.utils.PageUtils;
import com.hgx.shop.ware.entity.WareInfoEntity;
import com.hgx.shop.ware.vo.FareVo;

import java.util.Map;

/**
 * 仓库信息
 *
 * @author hgx
 * @email man133@126.com
 * @date 2020-10-10 16:40:15
 */
public interface WareInfoService extends IService<WareInfoEntity> {

    PageUtils queryPage(Map<String, Object> params);

    /**
     * 根据用户收货地址计算运费
     * @param addrId
     * @return
     */
    FareVo getFare(Long addrId);
}

