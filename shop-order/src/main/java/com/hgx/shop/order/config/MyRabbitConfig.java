package com.hgx.shop.order.config;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import javax.annotation.PostConstruct;


/**
 * @author hgx
 * @date 2021/1/20 23:34
 * @Description
 */
@Configuration
public class MyRabbitConfig {

    @Autowired
    RabbitTemplate rabbitTemplate;

    /**
     * 使用json序列化机制，进行消息转换
     * @return
     */
    @Bean
    public MessageConverter messageConverter(){
        return new Jackson2JsonMessageConverter();
    }

    /**
     * 定制rabbitTemplate
     * 1.服务器收到消息就回调
     *      1.spring.rabbitmq.publisher-confirm-type=correlated
     *      2.设置确认回调
     * 2.消息正确抵达队列回调
     *      1.spring.rabbitmq.publisher-returns=true
     *        spring.rabbitmq.template.mandatory=true
     *      2.设置确认回调ReturnCallback
     * 3.消费端确认（保证每个消息被正确消费，此时才可以broker删除这个消息）
     *      1.spring.rabbitmq.listener.direct.acknowledge-mode=manual
     *      2.默认是自动确认的，只要消息接收到，客户端会自动确认，服务端就会移除这个消息
     *         问题：收到很多消息，自动回复给服务器ack，只有一个消磁处理成功，宕机了。发生消息丢失。
     *         手动确认模式。只要没有明确告诉mq消息送达。没有ack，消息就一直是unacked状态。即使consumer宕机，消息不会丢失，会重新变为ready，下一次有新的consumer连接就发给它
     *      3.如何签收
     *          channel.basicAck(deliveryTag,false);//签收
     *          channel.basicNack(deliveryTag,false,true);//拒签
     */
    @PostConstruct  //对象创建完成以后，执行这个方法
    public void  initRabbitTemplate(){
        //设置确认回调
        rabbitTemplate.setConfirmCallback(new RabbitTemplate.ConfirmCallback() {
            /**
             *只要消息抵达服务器broker就ack=true
             * @param correlationData  当前消息的唯一关联数据（这个是消息的唯一id）
             * @param ack  消息是否成功收到
             * @param cause  失败的原因
             */
            @Override
            public void confirm(CorrelationData correlationData, boolean ack, String cause) {

            }
        });

        //设置消息抵达队列的确认回调
        rabbitTemplate.setReturnCallback(new RabbitTemplate.ReturnCallback() {
            /**
             * 只要消息没有投递给指定队列，就触发这个失败回调
             * @param message  投递失败的消息详细信息
             * @param replyCode  回复的状态码
             * @param replyText  回复的文本内容
             * @param exchange  当时这个消息发给哪个交换机
             * @param routingKey  当时这个消息用哪个路由器
             */
            @Override
            public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {

            }
        });
    }
}
