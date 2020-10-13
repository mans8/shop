package com.hgx.shop.product.service.impl;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hgx.common.utils.PageUtils;
import com.hgx.common.utils.Query;

import com.hgx.shop.product.dao.CategoryDao;
import com.hgx.shop.product.entity.CategoryEntity;
import com.hgx.shop.product.service.CategoryService;


@Service("categoryService")
public class CategoryServiceImpl extends ServiceImpl<CategoryDao, CategoryEntity> implements CategoryService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CategoryEntity> page = this.page(
                new Query<CategoryEntity>().getPage(params),
                new QueryWrapper<CategoryEntity>()
        );

        return new PageUtils(page);
    }

    /**
     * 获取所有菜单
     *
     * @return 所有菜单并组成树形结构
     */
    @Override
    public List<CategoryEntity> listWithTree() {

        //1.查出所有分类
        List<CategoryEntity> entities = baseMapper.selectList(null);

        //2.找到所有一级分类并组成树形结构
        return entities.stream().filter(categoryEntity ->
                categoryEntity.getParentCid() == 0
        ).map((menu) -> {
            menu.setChildren(getChildrens(menu, entities));
            return menu;
        }).sorted((menu1, menu2) -> {
            return menu1.getSort() - menu2.getSort();
        }).collect(Collectors.toList());
    }

    /**
     * 获取父菜单下的所有子菜单
     *
     * @param root 父菜单
     * @param all  需要遍历的所有菜单
     * @return 父菜单下所有子菜单并组成树形结构
     */
    private List<CategoryEntity> getChildrens(CategoryEntity root, List<CategoryEntity> all) {

        return all.stream().filter(categoryEntity -> {
            return categoryEntity.getParentCid() == root.getCatId();
        }).map(categoryEntity -> {
            //1.递归查找子菜单
            categoryEntity.setChildren(getChildrens(categoryEntity, all));
            return categoryEntity;
        }).sorted((menu1, menu2) -> {
            //2.菜单排序
            return (menu1.getSort() == null ? 0 : menu1.getSort()) - (menu2.getSort() == null ? 0 : menu2.getSort());
        }).collect(Collectors.toList());

    }

}