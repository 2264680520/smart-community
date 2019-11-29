package com.zw.smart.maill.utils;
import java.util.Properties;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.atomic.AtomicInteger;

import javax.mail.internet.MimeMessage;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.scheduling.annotation.Async;

@Slf4j
public class MailUtil {
    private Logger logger = LoggerFactory.getLogger(MailUtil.class);

    private final AtomicInteger count = new AtomicInteger(1);

    @Async
    public void start(final JavaMailSender mailSender,final SimpleMailMessage message) {
            try {
                if (count.get() == 2) {
                    log.info("the task is down");
                }
                mailSender.send(message);
                log.info("send email success");
            }catch (Exception e){
                log.error("send email fail" , e);
            }

    }
    @Async
    public void startHtml(final JavaMailSender mailSender,final MimeMessage message) {
                try {
                    if (count.get() == 2) {
                        log.info("the task is down");
                    }
                    log.info("start send email and the index is " + count);
                    mailSender.send(message);
                    log.info("send email success");
                }catch (Exception e){
                    log.error("send email fail" , e);
                }
    }
    /**
     * 获取 Sender 多实例发送
     * @return
     */
    public static JavaMailSenderImpl createMailSender(){
        JavaMailSenderImpl sender = new JavaMailSenderImpl();
        sender.setHost("smtp.mxhichina.com");
        sender.setPort(25);
        sender.setUsername("admin@52itstyle.com");
        sender.setPassword("123456");
        sender.setDefaultEncoding("Utf-8");
        Properties p = new Properties();
        p.setProperty("mail.smtp.timeout",1000+"");
        p.setProperty("mail.smtp.auth","true");
        sender.setJavaMailProperties(p);
        return sender;
    }

}
