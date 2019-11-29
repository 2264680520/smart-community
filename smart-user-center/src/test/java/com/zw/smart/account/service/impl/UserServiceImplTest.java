package com.zw.smart.account.service.impl;

import com.zw.smart.account.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
@Slf4j
class UserServiceImplTest {
    @Resource
    UserService userService;

    @Test
    void active() {
        String uuid = "ce034b8d-ed51-4a3a-92c0-03e0fb58626e";
        userService.activeUser(uuid);

    }
}