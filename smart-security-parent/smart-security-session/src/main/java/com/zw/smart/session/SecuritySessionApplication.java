package com.zw.smart.session;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * @author zhangwei
 */
@SpringBootApplication
@EnableRedisHttpSession
public class SecuritySessionApplication {
    public static void main(String[] args) {
        SpringApplication.run(SecuritySessionApplication.class, args);
    }
}
