package com.zw.smart.hello.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * @author zhangwei
 */
@Controller
public class UserController {
    /**
     * @param request
     * @return 转化重定向
     */
    @RequestMapping("/login")
    public String login(HttpServletRequest request) {
        String method = request.getMethod();
        if (method.equals("GET")) {
            return "login";
        } else if (method.equals("POST")) {
//          登录逻辑判断
//            request.getParameter("username");
//            request.getParameter("password");
            return "redirect:index";
        } else {
            return "不支持的请求方式";
        }

    }


    @RequestMapping("/logout")
    public String logout() {
        return "index";
    }
}
