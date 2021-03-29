package com.hgx.shop.ware.listener;

import com.alibaba.fastjson.TypeReference;
import com.hgx.common.to.mq.OrderTo;
import com.hgx.common.to.mq.StockDetailTo;
import com.hgx.common.to.mq.StockLockedTo;
import com.hgx.common.utils.R;
import com.hgx.shop.ware.entity.WareOrderTaskDetailEntity;
import com.hgx.shop.ware.entity.WareOrderTaskEntity;
import com.hgx.shop.ware.service.WareSkuService;
import com.hgx.shop.ware.vo.OrderVo;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * @author hgx
 * @date 2021/3/18 22:56
 * @Description
 */
@Service
@RabbitListener(queues = "stock.release.stock.queue")
public class StockReleaseListener {

    @Autowired
    WareSkuService wareSkuService;

    @RabbitHandler
    public void handleStockLockedRelease(StockLockedTo to, Message message, Channel channel) throws IOException {
        System.out.println("收到解锁库存的消息");
        try {
            wareSkuService.unlockStock(to);
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (Exception e) {
            channel.basicReject(message.getMessageProperties().getDeliveryTag(), true);
        }
    }

    @RabbitHandler
    public void handleOrderCloseRelease(OrderTo orderTo, Message message, Channel channel) throws IOException {
        System.out.println("订单关闭准备解锁库存...");
        try {
            wareSkuService.unlockStock(orderTo);
            channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
        } catch (Exception e) {
            channel.basicReject(message.getMessageProperties().getDeliveryTag(), true);
        }


    }
}
