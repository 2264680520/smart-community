package com.zw.mq.mqserverconsumer.listener;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

/**
 * @author zhangwei
 * 生产者 test-topic:order
 * 消费者
 */
@Component
@RocketMQMessageListener(consumerGroup = "default-consumer-group", topic = "test-topic", selectorExpression = "order")
@Slf4j
public class TestHelloService implements RocketMQListener<String> {
    @Override
    public void onMessage(String msg) {
        log.error(msg);
    }
}
