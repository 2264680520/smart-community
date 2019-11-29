package com.zw.smart.account.message;

import com.zw.smart.account.domain.dto.UserDto;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import sun.rmi.runtime.Log;

import javax.annotation.Resource;

/**
 * @author zhangwei
 */
@Service
@Slf4j
public class MqMailSender {
    @Value("${rocketmq.user-topic}")
    private String userTopic;
    @Value("${rocketmq.usertopic-register}")
    private String userTopicRegister;
    @Resource
    RocketMQTemplate rocketMQTemplate;

    public void sendRegisterMsg(UserDto userDto) {
        rocketMQTemplate.asyncSend(userTopic, userDto, new SendCallback() {
            @Override
            public void onSuccess(SendResult sendResult) {
                log.debug("发送成功");
            }

            @Override
            public void onException(Throwable throwable) {
                log.debug("发送错误");
            }
        });
    }


}
