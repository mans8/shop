package com.hgx.shop.product.web;

import com.hgx.shop.product.entity.CategoryEntity;
import com.hgx.shop.product.service.CategoryService;
import com.hgx.shop.product.vo.Catelog2Vo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * @author hgx
 * @date 2020/10/25 20:20
 * @Description
 */
@Controller
public class IndexController {

    @Autowired
    CategoryService categoryService;

    @GetMapping({"/","/index.html"})
    public String indexPage(Model model){
        //TODO 1、查出所有一级分类
        List<CategoryEntity> categoryEntities = categoryService.getLevel1Categorys();

        //视图解析器进行拼串
        model.addAttribute("categorys",categoryEntities);
        return "index";
    }

    @ResponseBody
    @GetMapping("/index/json/catalog.json")
    public Map<String, List<Catelog2Vo>> getCatalogJson(){
        Map<String, List<Catelog2Vo>> catalogJson = categoryService.getCatalogJson();
        return catalogJson;
    }
}
