package com.ttmy.awm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient //在服务启动后，自动注册到Eureka中
@EnableDiscoveryClient //服务发现
public class AuthorProvider_9158 {
    public static void main(String[] args) {
        SpringApplication.run(AuthorProvider_9158.class,args);
    }
}
