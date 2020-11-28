package com.hgx.shop.product.service.impl;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hgx.common.utils.PageUtils;
import com.hgx.common.utils.Query;

import com.hgx.shop.product.dao.SkuImagesDao;
import com.hgx.shop.product.entity.SkuImagesEntity;
import com.hgx.shop.product.service.SkuImagesService;


@Service("skuImagesService")
public class SkuImagesServiceImpl extends ServiceImpl<SkuImagesDao, SkuImagesEntity> implements SkuImagesService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<SkuImagesEntity> page = this.page(
                new Query<SkuImagesEntity>().getPage(params),
                new QueryWrapper<SkuImagesEntity>()
        );

        return new PageUtils(page);
    }

    /**
     * sku图片查询
     *
     * @param skuId
     * @return
     */
    @Override
    public List<SkuImagesEntity> getImagesBySkuId(Long skuId) {
        SkuImagesDao imagesDao = this.baseMapper;
        List<SkuImagesEntity> imagesEntities = imagesDao.selectList(new QueryWrapper<SkuImagesEntity>().eq("sku_id", skuId));
        return imagesEntities;
    }

}