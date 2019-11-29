package com.zw.smart.remember.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author zhangwei
 */
@Component
@Slf4j
public class CustomLogoutHandler implements LogoutHandler {
    @Resource
    PersistentTokenRepository repository;

    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        log.info("退出系统成功");
        UserDetails details = (UserDetails) authentication.getPrincipal();
        repository.removeUserTokens(details.getUsername());
    }
}
