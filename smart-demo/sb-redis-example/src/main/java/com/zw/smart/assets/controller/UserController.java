package com.zw.smart.assets.controller;

import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 */
@RestController
public class UserController {
    @RequestMapping("/")
//    value 对象redis的key
//    返回的值做redis的值保存
//    key 复杂 spEL 表示
    // #
    @Cacheable(value = "userController")
    public String list(int page) {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    }
    
    @CachePut(value = "userController")
    @RequestMapping("/save")
    public String save() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    }

}
