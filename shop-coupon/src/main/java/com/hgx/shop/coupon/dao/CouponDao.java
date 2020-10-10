package com.hgx.shop.coupon.dao;

import com.hgx.shop.coupon.entity.CouponEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 优惠券信息
 * 
 * @author hgx
 * @email man133@126.com
 * @date 2020-10-10 15:39:05
 */
@Mapper
public interface CouponDao extends BaseMapper<CouponEntity> {
	
}
