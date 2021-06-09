package com.ttmy.awm;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableEurekaClient //在服务启动后，自动注册到Eureka中
@EnableDiscoveryClient //服务发现
@MapperScan("com.ttmy.awm.dao")
@EnableFeignClients(basePackages = {"com.ttmy.awm.api"})
@EnableTransactionManagement
public class BankProvider_8094 {
    public static void main(String[] args) {
        SpringApplication.run(BankProvider_8094.class,args);
    }
}
