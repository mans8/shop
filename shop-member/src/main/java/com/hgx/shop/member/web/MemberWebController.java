package com.hgx.shop.member.web;

import com.hgx.common.utils.R;
import com.hgx.shop.member.feign.OrderFeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.Map;

/**
 * @author hgx
 * @date 2021/3/20 14:30
 * @Description
 */
@Controller
public class MemberWebController {

    @Autowired
    OrderFeignService orderFeignService;

    @GetMapping("/memberOrder.html")
    public String memberOrderPage(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum, Model model) {
        //查出当前登录的所有用户的所有顶大数据
        Map<String, Object> page = new HashMap<>();
        page.put("page", pageNum.toString());
        //
        R r = orderFeignService.listWithItem(page);
        model.addAttribute("orders", r);
        return "orderList";
    }

}
