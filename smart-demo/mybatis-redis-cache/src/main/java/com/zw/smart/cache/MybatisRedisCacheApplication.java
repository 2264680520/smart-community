package com.zw.smart.cache;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 *
 */
@SpringBootApplication
@MapperScan("com.zw.smart.cache.mapper")
public class MybatisRedisCacheApplication {
    public static void main(String[] args) {
        SpringApplication.run(MybatisRedisCacheApplication.class, args);
    }
}
