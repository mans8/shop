package com.hgx.shop.order.web;

import com.hgx.shop.order.service.OrderService;
import com.hgx.shop.order.vo.OrderConfirmVo;
import com.hgx.shop.order.vo.OrderSubmitVo;
import com.hgx.shop.order.vo.SubmitOrderResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.ExecutionException;

/**
 * @author hgx
 * @date 2021/2/10 10:29
 * @Description
 */
@Controller
public class OrderWebController {

    @Autowired
    OrderService orderService;

    /**
     * 订单结算请求接口
     *
     * @param model
     * @param request
     * @return
     * @throws ExecutionException
     * @throws InterruptedException
     */
    @GetMapping("/toTrade")
    public String toTrade(Model model, HttpServletRequest request) throws ExecutionException, InterruptedException {
        OrderConfirmVo confirmVo = orderService.confirmOrder();
        model.addAttribute("orderConfirmData", confirmVo);
        //展示订单确认的数据
        return "confirm";
    }

    /**
     * 提交订单接口（下单）
     *
     * @param vo
     * @return
     */
    @PostMapping("/submitOrder")
    public String submitOrder(OrderSubmitVo vo, Model model, RedirectAttributes redirectAttributes) {
        //创建订单，验令牌，验价格，锁库存...
        SubmitOrderResponseVo responseVo = orderService.submitOrder(vo);

        //下单失败回到订单确认页重新确认订单信息
        System.out.println("订单提交的数据" + vo);
        if (responseVo.getCode() == 0) {
            //下单成功来到支付选择页
            model.addAttribute("submitOrderResp", responseVo);
            return "pay";
        } else {
            String msg = "下单失败;";
            switch (responseVo.getCode()) {
                case 1:
                    msg += "订单信息过期，请刷新再次提交";
                    break;
                case 2:
                    msg += "订单商品价格发生变化，请确认后再次提交";
                    break;
                case 3:
                    msg += "库存锁定失败，商品库存不足";
                    break;
            }
            redirectAttributes.addFlashAttribute("msg", msg);
            return "redirect:http://order.shop.com/toTrade";
        }
    }
}
