package com.zw.smart.account.controller;

import com.zw.smart.account.common.result.ResponseEntity;
import com.zw.smart.account.domain.dto.UserDto;
import com.zw.smart.account.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;


/**
 * @author zhangwei
 */
@RestController
@RequestMapping("/account")
public class UserController extends BaseController {
    @Resource
    UserService userService;

    /**
     * 对数据进行校验
     *
     * @param userDto
     * @return
     * @Validated 对dto进行数据校验
     */
    @PostMapping("/register")
    public ResponseEntity register(@Valid @RequestBody UserDto userDto) {
        return userService.register(userDto);
    }

    @RequestMapping("/verify")
    public String isExist(String username) {
        return "";
    }
//    /api/v2/account/active?token=activekey
//    过期时间
//    key  value  过期时间 activekey
//    "activekey": id
    /**
     * uid  安全吗?
     * 激活账号
     * {
     * stutas:200
     * msg:success
     * data: 0
     * }
     *
     * @return
     */
    @RequestMapping("/active")
    public boolean activeUser(String code) {
        return userService.activeUser(code);
    }
}
