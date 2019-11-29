package com.zw.mq.mqserverconsumer.service.msg;

import com.zw.mq.mqserverconsumer.entity.Product;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.jcp.xml.dsig.internal.dom.DOMPGPData;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class MqMsgService {
    @Value("${rocketmq.app-topic}")
    String topic;
    @Resource
    RocketMQTemplate rocketMQTemplate;

    /**
     * topic:tags
     * 通过发送复杂的数据类型
     *
     * @param data
     */
    public void sendBaseMsg(List<Product> data) {
        rocketMQTemplate.syncSend(topic, data);
    }

    /**
     * 发送顺序消息
     *
     * @param data
     */
    public void sendBaseOrderly(List<Product> data) {
        rocketMQTemplate.syncSendOrderly(topic, data, "1");
    }

    /**
     * 发送延迟消息
     *
     * @param data
     */
    public void sendBaseDelay(List<Product> data) {
        rocketMQTemplate.syncSend(topic, MessageBuilder.withPayload(data).build(), 3000, 1);
    }

    /**
     * 单向的消息
     */
    public void sendBaseOnWay(List<Product> data) {
        rocketMQTemplate.sendOneWay(topic, data);
    }

    /**
     *
     * @param msg
     */
    public void asyncSend(String msg) {
        rocketMQTemplate.asyncSend(topic, msg, new SendCallback() {
            @Override
            public void onSuccess(SendResult sendResult) {

            }

            @Override
            public void onException(Throwable throwable) {

            }
        });


    }


}
