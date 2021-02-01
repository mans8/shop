package com.hgx.shop.order.service.impl;

import com.hgx.shop.order.entity.OrderEntity;
import com.hgx.shop.order.entity.OrderReturnReasonEntity;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hgx.common.utils.PageUtils;
import com.hgx.common.utils.Query;

import com.hgx.shop.order.dao.OrderItemDao;
import com.hgx.shop.order.entity.OrderItemEntity;
import com.hgx.shop.order.service.OrderItemService;


@Service("orderItemService")
@RabbitListener(queues = {"hello-java-queue"})
public class OrderItemServiceImpl extends ServiceImpl<OrderItemDao, OrderItemEntity> implements OrderItemService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<OrderItemEntity> page = this.page(
                new Query<OrderItemEntity>().getPage(params),
                new QueryWrapper<OrderItemEntity>()
        );

        return new PageUtils(page);
    }

    /**
     * queues: 声明需要监听的所有队列
     *
     * 参数可以写一下类型
     * 1.Message message:原生消息详细信息。头 + 体
     * 2.T<发送消息的类型> OrderReturnReasonEntity context；
     * 3.Channel channel:当前传输数据的通道
     * Queue:可以很多人来监听，只要收到消息，队列删除消息，而且只能有一个收到此消息
     * 场景：
     *     1).订单服务启动多个：同一个消息，只能有一个客户端收到
     *     2).只有一个消息完全处理完，方法运行结束，我们就可以接收到下一个消息
     * @param message
     */
    @RabbitHandler
    //@RabbitListener(queues = {"hello-java-queue"})
    public void recieveMessage(Message message,
                               OrderReturnReasonEntity content,
                               Channel channel) throws IOException {
        //{"id":1,"name":"哈哈","sort":null,"status":null,"createTime":158114698745}
        byte[] body = message.getBody();
        //消息头属性信息
        MessageProperties properties = message.getMessageProperties();
        System.out.println("接收到内容："+message + "===>内容：" + content.getName());
        //通道内按顺序自增
        long deliveryTag = message.getMessageProperties().getDeliveryTag();
        //签收消息，非批量签收
        try {
            channel.basicAck(deliveryTag,false);
        }catch (Exception e){
            //requeue=true 发回服务器重新入队
            //requeue=false 丢弃
            channel.basicNack(deliveryTag,false,true);
        }

    }

    @RabbitHandler
    public void recieveMessage2(OrderEntity content) throws InterruptedException{
        System.out.println("接收到消息：" + content);
    }
}