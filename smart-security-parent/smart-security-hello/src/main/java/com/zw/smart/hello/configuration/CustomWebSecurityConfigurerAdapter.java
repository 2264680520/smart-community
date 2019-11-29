package com.zw.smart.hello.configuration;

import com.zw.smart.hello.handler.CustomFailHandler;
import com.zw.smart.hello.handler.CustomSuccessHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.Resource;

/**
 * @author zhangwei
 */
@Configuration
public class CustomWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {
    @Resource
    CustomSuccessHandler successHandler;
    CustomFailHandler failHandler;

    /**
     * 自定义登录的账号密码
     * 通过配置文件
     * 在java代码中配置
     * 通过数据加载
     *
     * @param manager
     * @throws Exception
     */
    // 认证管理配置的核心方法 AuthenticationManagerBuilder
    @Override
    public void configure(AuthenticationManagerBuilder manager) throws Exception {
//        String admin = passwordEncoder().encode("admin");
        manager

                .inMemoryAuthentication()
                .passwordEncoder(passwordEncoder())
                .withUser("admin")
                .roles("admin")
                // admin 主要要使用加密过的字符串
                .password("$2a$10$buN.8vVANJ.0LUK4wH2OveNBbJk1jbc9HVwH2slSIF27T5usiE/te");
    }

    /**
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    //    授权的核心方法
//    指定登录界面
//    from表单
//    ajax 请求
//   登录失败  定制
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //登录操作
        http.formLogin()
                // 设置登录的指定界面
                .loginPage("/login")
                //设置登录成功之跳转指定界面
                .defaultSuccessUrl("/index")
                // 自定义处理成功的接口回调
                .successHandler(successHandler)
                //  自定处理失败后的接口回调
                .failureHandler(failHandler)
                // 所有的访问必须通过验证才能访问 否则直接跳转登录界面
                .permitAll();

        //登出操作
        http.logout().logoutUrl("/logout").permitAll();
        // 关闭
        http.csrf().disable();

        http.authorizeRequests()
                //  ? * **
                // 放行登录界面
                .antMatchers("/login").permitAll()
                .anyRequest()
                .authenticated();

//
    }
}
