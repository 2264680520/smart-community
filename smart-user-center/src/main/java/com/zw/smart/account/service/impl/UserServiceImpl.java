package com.zw.smart.account.service.impl;

import com.zw.smart.account.common.result.ResponseEntity;
import com.zw.smart.account.common.result.ResponseStatus;
import com.zw.smart.account.domain.dto.UserDto;
import com.zw.smart.account.domain.entity.User;
import com.zw.smart.account.mapper.UserMapper;
import com.zw.smart.account.message.MqMailSender;
import com.zw.smart.account.service.UserService;
import com.zw.smart.account.common.utils.redis.RedisService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 判断账号是否存在
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService {
    @Resource
    MqMailSender mailSender;
    @Resource
    private UserMapper userMapper;
    @Resource
    RedisService redisService;

    /**
     * @param username
     * @return
     */
    @Override
    public User findUserByName(String username) {
        return userMapper.selectByName(username);
    }

    /**
     * 判断用户是否存在
     *
     * @param userDto
     * @return 邮箱验证
     */
    @Override
    public ResponseEntity register(UserDto userDto) {
        // 1 判断账号是否存在
        User user = findUserByName(userDto.getUsername());
        if (user == null) {
            user = new User();
            BeanUtils.copyProperties(userDto, user);
            int count = userMapper.insert(user);
            if (count == 1) {
                userDto.setUid(user.getUid());
                mailSender.sendRegisterMsg(userDto);
            } else {
                return ResponseEntity.error(ResponseStatus.ACCOUNT_REGISTER_ERROR);
            }
        } else {
            return ResponseEntity.error(ResponseStatus.ACCOUNT_IS_EXISTENT);
        }
        return ResponseEntity.success(userDto);
    }

    @Override
    public boolean activeUser(String token) {
        int count = 0;
        Object obj = redisService.get(token);
        if (obj != null) {
            count = userMapper.updateByUid(Integer.parseInt(String.valueOf(obj)));
            redisService.delete(token);
        }
        return count != 0;
    }
}
