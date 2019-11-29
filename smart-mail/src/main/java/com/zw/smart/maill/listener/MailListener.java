package com.zw.smart.maill.listener;

import com.zw.smart.maill.dto.UserDto;
import com.zw.smart.remember.MailService;
import com.zw.smart.maill.utils.RedisService;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.MessagingException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * 缺少邮箱地址
 * 邮件  第三方的邮件服务 (Spring boot 自带邮件组件)
 * redis
 * 发送的内容
 * 链接[点击激活]
 * 如果点击失效复制爱url地址到浏览器激活
 *
 * @author zhangwei
 */
@Slf4j
@Service
@RocketMQMessageListener(consumerGroup = "mall-group", topic = "user-topic")
public class MailListener implements RocketMQListener<UserDto> {
    private static final String ACTIVE_URL = "?code=";
    @Resource
    MailService mailService;
    @Value("${active.base-url}")
    private String baseUrl;
    @Resource
    RedisService redisService;

    //生成的内容
    //api/v2/account/active?activeCode=?
    // 将数据放入redis中  设置过期时间 10分钟
    //  key uuid   value: 用户id  过期时间10分钟
    @Override
    public void onMessage(UserDto userDto) {
        try {
            mailService.sendHtmlMail(userDto.getEmail(), "智慧社区注册邮件", getToUrl(userDto.getUid()));
        } catch (MessagingException e) {
            log.error(e.getMessage());
        }
    }

    private String getToUrl(int uid) {
        String uuid = UUID.randomUUID().toString();
        redisService.setEx(uuid, String.valueOf(uid), 10, TimeUnit.MINUTES);
        return baseUrl + ACTIVE_URL + uuid;
    }

}
