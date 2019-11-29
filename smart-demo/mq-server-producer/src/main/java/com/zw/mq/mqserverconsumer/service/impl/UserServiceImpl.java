package com.zw.mq.mqserverconsumer.service.impl;

import com.zw.mq.mqserverconsumer.service.UserService;
import com.zw.mq.mqserverconsumer.service.msg.MqMsgService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 *
 */
@Service
public class UserServiceImpl implements UserService {
    @Resource
    MqMsgService mqMsgService;

    public String reigster(String username, String password) {
        //
        mqMsgService.asyncSend("发送邮件");
        //
        return "";
    }


}
