package com.hgx.shop.search.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author hgx
 * @date 2020/11/2 22:25
 * @Description
 */
@Controller
public class SearchController {

    @GetMapping("/list.html")
    public String listPage(){
        return "list";
    }
}
