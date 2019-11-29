package com.zw.smart.remember;



/**
 * 腾讯邮箱
 * 阿里云邮箱
 * 163邮箱
 * gmail邮箱
 * 1 配置第三方的服务   拿到密码  不是登录的邮箱密码
 * <p>
 * 2
 */
public interface MailService {
    /**
     * 发送文本邮件
     * @param to 收件人
     * @param subject 主题
     * @param content 内容
     */
    default void sendSimpleMail(String to, String subject, String content){};
    /**
     * 发送HTML邮件
     * @param to 收件人
     * @param subject 主题
     * @param content 内容
     */
   default void sendHtmlMail(String to, String subject, String content){};
    /**
     * 发送带附件的邮件
     * @param to 收件人
     * @param subject 主题
     * @param content 内容
     * @param filePath 附件
     */
    default  void sendAttachmentsMail(String to, String subject, String content, String filePath){};
}
