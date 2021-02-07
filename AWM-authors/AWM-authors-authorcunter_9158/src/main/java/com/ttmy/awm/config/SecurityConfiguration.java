package com.ttmy.awm.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    //http拦截
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin() // 表单登录。跳转到security默认的登录表单页
                .and()
                .authorizeRequests() // 对请求授权
                .antMatchers("/oauth/**").permitAll() //暴漏 所有 接口给前端
                .anyRequest() // 任何请求
                .authenticated()// 需要身份认证
        ;
    }

    //密码编码器，不编码模式
    @Bean
    public static PasswordEncoder passwordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
