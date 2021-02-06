package com.ttmy.awm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * 资源认证服务器，用于发送令牌，校验权限
 * 需要使用redis存储令牌，要开启redis服务
 */
@SpringBootApplication
@EnableEurekaClient //在服务启动后，自动注册到Eureka中
@EnableDiscoveryClient //服务发现
public class AuthorCenter_9158 {
    public static void main(String[] args) {
        SpringApplication.run(AuthorCenter_9158.class,args);
    }
}
