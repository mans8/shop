package com.hgx.shop.product.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;


import com.hgx.shop.product.entity.AttrEntity;
import com.hgx.shop.product.service.AttrAttrgroupRelationService;
import com.hgx.shop.product.service.AttrService;
import com.hgx.shop.product.service.CategoryService;
import com.hgx.shop.product.vo.AttrGroupRelationVo;
import com.hgx.shop.product.vo.AttrGroupWithAttrsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.hgx.shop.product.entity.AttrGroupEntity;
import com.hgx.shop.product.service.AttrGroupService;
import com.hgx.common.utils.PageUtils;
import com.hgx.common.utils.R;


/**
 * 属性分组
 *
 * @author hgx
 * @email man133@126.com
 * @date 2020-10-10 13:50:13
 */
@RestController
@RequestMapping("product/attrgroup")
public class AttrGroupController {
    @Autowired
    private AttrGroupService attrGroupService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    AttrService attrService;

    @Autowired
    AttrAttrgroupRelationService relationService;

    /**
     * /product/attrgroup/attr/relation
     *
     * @param vos
     * @return
     */
    @PostMapping("/attr/relation")
    public R addRelation(@RequestBody List<AttrGroupRelationVo> vos) {
        relationService.saveBatch(vos);
        return R.ok();
    }

    /**
     * /product/attrgroup/{catelogId}/withattr
     * @param catelogId
     * @return
     */
    @GetMapping("/{catelogId}/withattr")
    public R getAttrGroupWithAttrs(@PathVariable("catelogId") Long catelogId) {
        List<AttrGroupWithAttrsVo> vos = attrGroupService.getAttrGroupWithAttrsByCatelogId(catelogId);
        return R.ok().put("data", vos);
    }


    /**
     * /product/attrgroup/{attrgroupId}/attr/relation
     *
     * @param attrgrouId
     * @return
     */
    @GetMapping("/{attrgroupId}/attr/relation")
    public R arrtRelation(@PathVariable("attrgroupId") Long attrgrouId) {
        List<AttrEntity> entities = attrService.getRelationAttr(attrgrouId);
        return R.ok().put("data", entities);
    }

    /**
     * /product/attrgroup/{attrgroupId}/noattr/relation
     *
     * @param attrgroupId
     * @param params
     * @return
     */
    @GetMapping("/{attrgroupId}/noattr/relation")
    public R attrNoRelation(@PathVariable("attrgroupId") Long attrgroupId,
                            @RequestParam Map<String, Object> params) {
        PageUtils page = attrService.getNoRelationAttr(params, attrgroupId);
        return R.ok().put("page", page);
    }


    /**
     * @param vos
     * @return
     */
    @PostMapping("/attr/relation/delete")
    public R deleteRelation(@RequestBody AttrGroupRelationVo[] vos) {
        attrService.deleteRelation(vos);
        return R.ok();
    }

    /**
     * 列表
     */
    @RequestMapping("/list/{catelogId}")
    //@RequiresPermissions("product:attrgroup:list")
    public R list(@RequestParam Map<String, Object> params, @PathVariable("catelogId") Long catelogId) {
        //PageUtils page = attrGroupService.queryPage(params);
        PageUtils page = attrGroupService.queryPage(params, catelogId);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{attrGroupId}")
    //@RequiresPermissions("product:attrgroup:info")
    public R info(@PathVariable("attrGroupId") Long attrGroupId) {
        AttrGroupEntity attrGroup = attrGroupService.getById(attrGroupId);

        Long catelogId = attrGroup.getCatelogId();
        Long[] path = categoryService.findCatelogPath(catelogId);
        attrGroup.setCatelogPath(path);

        return R.ok().put("attrGroup", attrGroup);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("product:attrgroup:save")
    public R save(@RequestBody AttrGroupEntity attrGroup) {
        attrGroupService.save(attrGroup);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("product:attrgroup:update")
    public R update(@RequestBody AttrGroupEntity attrGroup) {
        attrGroupService.updateById(attrGroup);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("product:attrgroup:delete")
    public R delete(@RequestBody Long[] attrGroupIds) {
        attrGroupService.removeByIds(Arrays.asList(attrGroupIds));

        return R.ok();
    }

}
