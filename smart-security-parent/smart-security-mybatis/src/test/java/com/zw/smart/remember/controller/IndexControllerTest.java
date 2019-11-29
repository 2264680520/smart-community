package com.zw.smart.remember.controller;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class IndexControllerTest {
    @Resource
    PasswordEncoder encoder;
    @Test
    void index() {
        System.out.println( encoder.encode("admin"));

    }
}