package com.ttmy.awm.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * 第一层（最外层安全拦截）
 * spring security 配置，可保护路由内的资源
 */
@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter  {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin() // 表单登录。跳转到security默认的登录表单页
                .and()
                .authorizeRequests() // 对请求授权
                .antMatchers("/AWM/**").permitAll() //暴漏 /AWM 接口给前端
                .anyRequest() // 任何请求
                .authenticated()// 需要身份认证
        ;
    }
}
