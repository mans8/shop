package com.hgx.shop.search.controller;

import com.hgx.shop.search.services.MallSearchService;
import com.hgx.shop.search.vo.SearchParam;
import com.hgx.shop.search.vo.SearchResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author hgx
 * @date 2020/11/2 22:25
 * @Description
 */
@Controller
public class SearchController {

    @Autowired
    MallSearchService mallSearchService;

    @GetMapping("/list.html")
    public String listPage(SearchParam param, Model model) {
        //1.根据传递来页面查询参数，去es中检索商品
        SearchResult result = mallSearchService.search(param);
        model.addAttribute("result", result);
        return "list";
    }
}
