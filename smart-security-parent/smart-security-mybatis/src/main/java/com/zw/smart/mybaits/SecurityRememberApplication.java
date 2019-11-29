package com.zw.smart.mybaits;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 *
 */
@SpringBootApplication
@MapperScan("com.zw.smart.mybaits.mapper")
public class SecurityRememberApplication {
    public static void main(String[] args) {
        SpringApplication.run(SecurityRememberApplication.class, args);
    }
}
