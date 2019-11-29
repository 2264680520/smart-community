package com.zw.mq.mqserverconsumer;

import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.Resource;

@SpringBootApplication
public class MqServerConsumerApplication implements CommandLineRunner {
    @Resource
    RocketMQTemplate rocketMQTemplate;
    private String default_topic = "test-topic";

    public static void main(String[] args) {
        SpringApplication.run(MqServerConsumerApplication.class, args);
    }

    /**
     * 同步消息
     * 异步消息
     *
     * @param args
     * @throws Exception
     */
    @Override
    public void run(String... args) throws Exception {
        /**
         * String destination, 目的地  主题 topic
         * Message<?> message,  消息体
         * long timeout, 发送超时的时间
         * int delayLevel   延迟等级        1-18
         *      1     2
         *     1秒   5秒
         *
         */
//        for (int i = 0; i < 20000; i++) {
//            rocketMQTemplate.syncSend(default_topic, "如家思想学编程" + i, 3000);
//        }


    }
}
