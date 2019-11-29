package com.zw.smart.mybaits.service;

import com.zw.smart.mybaits.entity.SysUser;
import com.zw.smart.mybaits.mapper.SysUserMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 自定义规则
 *
 * @author zhangwei
 */
@Service
public class CustomUserDetailService implements UserDetailsService {
    @Resource
    SysUserMapper mapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUser sysUser = mapper.selectByUsername(username);
        return sysUser;
    }
}
