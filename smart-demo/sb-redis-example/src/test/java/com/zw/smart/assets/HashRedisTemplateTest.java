package com.zw.smart.assets;

import com.zw.smart.assets.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.HashOperations;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * 操作redis
 * 增删改查
 * 特点
 * 过期时间
 * 自增/自减
 * 位图的操作
 */
@SpringBootTest
@Slf4j
public class HashRedisTemplateTest {
    @Resource
    HashOperations<String, String, Object> hashOperations;

    @Test
    public void testOptionsObject() {
        User user = User.builder()
                .uid(2L)
                .username("hash")
                .build();
        hashOperations.put("user:uid:" + user.getUid(), "username", user.getUsername());
        Map<String, Object> map = new HashMap<>();
        map.put("username", "hash");
        map.put("age", 10);
        hashOperations.putAll("user:uid:3", map);
        hashOperations.put("user:uid:3", "age", 11);

    }


}
