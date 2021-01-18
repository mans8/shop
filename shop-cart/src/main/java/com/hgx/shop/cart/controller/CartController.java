package com.hgx.shop.cart.controller;

import com.hgx.shop.cart.services.CartService;
import com.hgx.shop.cart.vo.Cart;
import com.hgx.shop.cart.vo.CartItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.concurrent.ExecutionException;

/**
 * @author hgx
 * @date 2021/1/5 15:52
 * @Description 购物车
 */
@Controller
public class CartController {

    @Autowired
    CartService cartService;

    /**
     * 勾选购物项
     * @param skuId
     * @param check
     * @return
     */
    @GetMapping("/checkItem")
    public String checkItem(@RequestParam("skuId") Long skuId,
                            @RequestParam("check") Integer check){
        cartService.checkItem(skuId,check);
        return "redirect:http://cart.shop.com/cart.html";
    }

    /**
     * 浏览器有一个cookie：user-key标识用户身份，一个月后过期
     * 第一次使用jd购物车功能，都会给一个临时用户身份
     * 浏览器以后保存，每次访问都会带上cookie
     * <p>
     * 登录：session有
     * 没登录：按照cookie里面带来user-key来做
     * 第一次：如果没有临时用户，帮忙创建一个临时用户
     *
     * @return
     */
    @GetMapping("/cart.html")
    public String cartListPage(Model model) throws ExecutionException, InterruptedException {

        //1.快速得到用户信息：id，user-key.
        //System.out.println(userInfoTo);
        Cart cart = cartService.getCart();
        model.addAttribute("cart",cart);
        return "cartList";
    }

    /**
     * 添加商品到购物车
     * RedirectAttributes ra
     *     ra.addFlashAttribute();将数据放在session里面可以在页面取出，但是只能取出一次
     *     ra.addAttribute("skuId",skuId);将数据放在url后面
     * @return
     */
    @GetMapping("/addToCart")
    public String addToCart(@RequestParam("skuId") Long skuId,
                            @RequestParam("num") Integer num,
                            RedirectAttributes ra) throws ExecutionException, InterruptedException {

        cartService.addToCart(skuId, num);
        ra.addAttribute("skuId",skuId);
        //model.addAttribute("skuId", skuId);
        return "redirect:http://cart.shop.com/addToCartSuccess.html";
    }

    /**
     * 添加到成功页
     * @param skuId
     * @param model
     * @return
     */
    @GetMapping("/addToCartSuccess.html")
    public String addToCartSuccessPage(@RequestParam("skuId") Long skuId,Model model){
        //重定向到成功页面，再次查询购物车数据即可
        CartItem item = cartService.getCartItem(skuId);
        model.addAttribute("item",item);
        return "success";
    }


}
