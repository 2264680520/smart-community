package com.zw.smart.assets;

import com.zw.smart.assets.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import javax.annotation.Resource;
import java.util.ArrayList;

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
public class StringRedisTemplateTest {
    @Resource
    RedisTemplate<String, Object> redisTemplate;
    @Resource
    ValueOperations<String, Object> valueOperations;

    @Test
    public void testOptionsString() {
//        valueOperations.set("hello", "java");
//        valueOperations.set("expireKey", "替你操心", 60, TimeUnit.SECONDS);
//        valueOperations.set("expireKey", "替你操心", Duration.ofSeconds(1));
//        String hello = (String) valueOperations.get("hello");
        //        nx 存在就不设置,不存在就设置
//        valueOperations.setIfAbsent("isAbsent", false);

//     xx  如果存在就设置值  不存在就不设置
        valueOperations.setIfPresent("isAbsent", "hello");
    }

    @Test
    public void testOptionsObject() {
        User.builder().build();
        valueOperations.set("user", User.builder().uid(1L).username("hello").build());
    }

    //    转化json
    @Test
    public void testOptionsList() {
//        最终json数据
        valueOperations.set("user", new ArrayList<String>());
    }

    @Test
    public void testOptionIncr() {
//        自增
        valueOperations.increment("incrKey", 2);
//        自减
        valueOperations.decrement("incrKey", 1);
    }

    @Test
    public void testOptionBit() {
        valueOperations.set("testBit", "hello");
        valueOperations.setBit("testBit", 21, false);
//        Boolean bit = valueOperations.getBit("testBit", 10);
    }

    @Test
    public void testOptionBitCount() {
//        bitCount key  开始索引  结束索引
        Long count = (Long) redisTemplate.execute((RedisCallback<Object>) connection ->
                // 返回 统计1的个数
                connection.bitCount("testBit".getBytes(), 0, -1)
        );
        log.info(count + "");
    }

}
