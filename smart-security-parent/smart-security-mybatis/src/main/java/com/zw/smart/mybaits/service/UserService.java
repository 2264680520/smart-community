package com.zw.smart.mybaits.service;

import com.zw.smart.mybaits.entity.SysUser;

public interface UserService {
    SysUser login(String username, String password);
}
