package com.zw.smart.remember;

import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UerControllerTest {
    @Resource
    PasswordEncoder encoder;

    @Test
    void login() {
        System.out.println(encoder.encode("admin"));
    }
}