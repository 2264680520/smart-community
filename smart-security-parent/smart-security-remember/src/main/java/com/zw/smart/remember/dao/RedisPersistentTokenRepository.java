package com.zw.smart.remember.dao;

import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.mail.MailParseException;
import org.springframework.security.core.parameters.P;
import org.springframework.security.web.authentication.rememberme.PersistentRememberMeToken;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * token 随机的字符串
 * 用户名
 * 登录密码
 * 过期时间
 */
@Component
public class RedisPersistentTokenRepository implements PersistentTokenRepository {
    @Resource
    RedisTemplate<String, Object> redisTemplate;
    public static final String KEY_REDIS_REMEMBER = "spring:security:rememberMe:series:";
    public static final String KEY_REDIS_USERNAME = "spring:security:rememberMe:username:";

    private static final String KEY_REDIS_REMEMBER_USERNAME = "username";
    private static final String KEY_REDIS_REMEMBER_series = "series";
    private static final String KEY_REDIS_REMEMBER_TOKEN_VALUE = "tokenValue";
    private static final String KEY_REDIS_REMEMBER_DATE = "date";


    public String generateKey(String series) {
        return KEY_REDIS_REMEMBER + series;
    }

    public String generateKeyByUserName(String username) {
        return KEY_REDIS_USERNAME + username;
    }

    @Override
    public void createNewToken(PersistentRememberMeToken token) {
        String key = generateKey(token.getSeries());
        Map<String, String> map = new HashMap<>(8);
        map.put(KEY_REDIS_REMEMBER_USERNAME, token.getUsername());
        map.put(KEY_REDIS_REMEMBER_TOKEN_VALUE, token.getTokenValue());

        map.put(KEY_REDIS_REMEMBER_DATE, String.valueOf(token.getDate().getTime()));
        redisTemplate.opsForHash().putAll(key, map);
        //对key的操作  设置过期时间
        redisTemplate.expire(key, 7, TimeUnit.DAYS);
//        通过name 查询到 seriesId
        String userKey = generateKeyByUserName(token.getUsername());
        redisTemplate.opsForValue().set(userKey, key);
    }

    @Override
    public void updateToken(String series, String tokenValue, Date lastUsed) {
        String key = generateKey(series);
        boolean isHas = redisTemplate.hasKey(key);
        if (isHas) {
            Map<String, String> map = new HashMap<>(8);
            map.put(KEY_REDIS_REMEMBER_TOKEN_VALUE, tokenValue);
            map.put(KEY_REDIS_REMEMBER_DATE, String.valueOf(lastUsed.getTime()));
            redisTemplate.opsForHash().putAll(key, map);
        }
    }

    /**
     * spring:security:rememberMe:series:0po8SZKtI+JeJZw9BMjJhA==
     *
     * @param seriesId
     * @return
     */
    @Override
    public PersistentRememberMeToken getTokenForSeries(String seriesId) {
        String key = generateKey(seriesId);
        if (redisTemplate.hasKey(key)) {
            HashOperations<String, String, Object> hashOperations = redisTemplate.opsForHash();
            String username = String.valueOf(hashOperations.get(key, KEY_REDIS_REMEMBER_USERNAME));
            String tokenValue = (String) hashOperations.get(key, KEY_REDIS_REMEMBER_TOKEN_VALUE);
            String date = (String) hashOperations.get(key, KEY_REDIS_REMEMBER_DATE);
            long time = Long.parseLong(date);
            Date date1 = new Date(time);
            return new PersistentRememberMeToken(username, seriesId, tokenValue, date1);
        }
        return null;
    }

    /**
     * 不是特别重要的数据 都是可以存到redis
     * <p>
     * 退出登录的时候要删除
     */
    @Override
    public void removeUserTokens(String username) {
        String userKey = generateKeyByUserName(username);
        Object o = redisTemplate.opsForValue().get(userKey);
        if (o != null) {
            String key = String.valueOf(o);
            redisTemplate.delete(key);
            redisTemplate.delete(userKey);
        }
    }
}
