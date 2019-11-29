package com.zw.smart.remember.impl;

import com.zw.smart.maill.dto.Email;
import com.zw.smart.remember.MailService;
import com.zw.smart.maill.utils.MailUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.annotation.Resource;
import javax.mail.internet.MimeMessage;

/**
 * @author zhangwei
 */

@Service
public class MailServiceImpl implements MailService {
    @Resource
    private JavaMailSender mailSender;
    /**
     * thymeleaf
     */
    @Resource
    private SpringTemplateEngine templateEngine;
    /**
     * 发送者
     */
    @Value("${spring.mail.username}")
    public String username;

    public void send(Email mail) throws Exception {
        MailUtil mailUtil = new MailUtil();
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(username);
        message.setTo(mail.getEmail());
        message.setSubject(mail.getSubject());
        message.setText(mail.getContent());
        mailUtil.start(mailSender, message);
    }

    public void sendThymeleaf(Email mail) throws Exception {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setFrom(username);
        helper.setTo(mail.getEmail());
        helper.setSubject(mail.getSubject());
        Context context = new Context();
        context.setVariable("email", mail);
        String text = templateEngine.process(mail.getTemplate(), context);
        helper.setText(text, true);
        mailSender.send(message);
    }
}
