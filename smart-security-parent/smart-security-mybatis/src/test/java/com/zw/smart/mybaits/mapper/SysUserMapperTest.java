package com.zw.smart.mybaits.mapper;

import com.zw.smart.mybaits.entity.SysUser;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SysUserMapperTest {
    @Resource
    SysUserMapper userMapper;

    @Test
    void selectByUsername() {
        SysUser admin = userMapper.selectByUsername("admin");
        System.out.println(admin.getUsername());

        admin.getRoles().forEach(role -> {
            System.out.println(role.getName());
        });

    }
}