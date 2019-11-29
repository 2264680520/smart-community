package com.zw.smart.mybaits.configuration;

import com.zw.smart.mybaits.service.CustomUserDetailService;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.annotation.Resource;

/**
 * @author zhangwei
 */
@Configuration
public class WebSecurityAdapter extends WebSecurityConfigurerAdapter {
    @Resource
    CustomUserDetailService customUserDetailService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserDetailService)
                .passwordEncoder(new BCryptPasswordEncoder());
    }

    /**
     * 后台  所有的访问都要做认证
     *
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 自定义登录界面
        http.formLogin()
                .loginPage("/login");
//        http.logout().logoutUrl().addLogoutHandler()
//        记住我的操作
//        http.rememberMe().tokenRepository()
        // 授权请求配置
        http.authorizeRequests()
                //不做认证的操作 * ? **
                .antMatchers("/login", "/", "/index").permitAll()
                .antMatchers("/admin/**").hasRole("admin")
                .anyRequest()
                // 对所有的请求做认证的操作
                .authenticated();


    }
}
