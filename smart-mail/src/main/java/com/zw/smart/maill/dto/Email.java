package com.zw.smart.maill.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.HashMap;

@Data
public class Email implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 必填参数 接收方邮件
     */
    private String email;
    /**
     *  主题
     */
    private String subject;
    /**
     * 邮件内容
     */
    private String content;
    /**
     * 选填 模板
     */
    private String template;
    /**
     * 自定义参数
     */
    private HashMap<String, String> kvMap;
}