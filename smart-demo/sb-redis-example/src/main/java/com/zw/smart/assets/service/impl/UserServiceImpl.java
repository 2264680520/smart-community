package com.zw.smart.assets.service.impl;

import com.zw.smart.assets.service.UserService;
import org.apache.catalina.User;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 保存对象的类型
 * String  String
 * user {
 * username:
 * password: 123
 * }
 */
@Service
@CacheConfig(cacheNames = "userService")
public class UserServiceImpl implements UserService {
//    @Resource
//    UserMapper userMapper;
//    @Resource
//    RedisHelper helper;

    @Override
    @Cacheable
    public List<User> getUsers() {
        return null;
    }
    //如果redis没有
    // 从数据库中获取数据
    // 设置redis中去

//    public List<User> getUsers() {
//        String json = (String) helper.get("user");
//        if (json != null) {
//            // 解析操作
//            List<User> list = JSONObject.parseArray((json, User.class);
//            return list;
//        } else {
//            List<User> users = userMapper.selectAll();
//            helper.set("user", users);
//            return users;
//        }
//    }
}
