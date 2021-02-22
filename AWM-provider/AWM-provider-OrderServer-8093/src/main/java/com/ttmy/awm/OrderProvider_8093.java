package com.ttmy.awm;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableEurekaClient //在服务启动后，自动注册到Eureka中
@EnableDiscoveryClient //服务发现
@MapperScan("com.ttmy.awm.dao")
@EnableFeignClients(basePackages = {"com.ttmy.awm.api"})
public class OrderProvider_8093 {
    public static void main(String[] args) {
        SpringApplication.run(OrderProvider_8093.class,args);
    }
}
