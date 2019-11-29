package com.zw.smart.mybaits.service.impl;

import com.zw.smart.mybaits.entity.SysUser;
import com.zw.smart.mybaits.mapper.SysUserMapper;
import com.zw.smart.mybaits.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 *
 */
@Service
public class UserServiceImpl implements UserService {
    @Resource
    SysUserMapper userMapper;

    @Override
    public SysUser login(String username, String password) {
        return userMapper.selectByUsername(username);
    }

}
