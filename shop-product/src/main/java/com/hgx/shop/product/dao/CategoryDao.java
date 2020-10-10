package com.hgx.shop.product.dao;

import com.hgx.shop.product.entity.CategoryEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 商品三级分类
 * 
 * @author hgx
 * @email man133@126.com
 * @date 2020-10-10 13:50:13
 */
@Mapper
public interface CategoryDao extends BaseMapper<CategoryEntity> {
	
}
