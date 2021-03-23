package com.hgx.shop.order.controller;

import com.hgx.shop.order.entity.OrderEntity;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

/**
 * @author hgx
 * @date 2021/2/1 22:31
 * @Description
 */
@Controller
public class HelloController {

    @Autowired
    RabbitTemplate rabbitTemplate;

    @ResponseBody
    @RequestMapping("/test/createOrder")
    public String createOrderTest() {
        OrderEntity entity = new OrderEntity();
        entity.setOrderSn(UUID.randomUUID().toString());
        rabbitTemplate.convertAndSend("order-event-exchange", "order.create.order", entity);
        return "ok";
    }

    @GetMapping("/{page}.html")
    public String listPage(@PathVariable("page") String page) {
        return page;
    }
}
