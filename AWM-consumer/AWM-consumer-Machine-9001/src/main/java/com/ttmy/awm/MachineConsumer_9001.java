package com.ttmy.awm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients(basePackages = {"com.ttmy.awm.api"})
public class MachineConsumer_9001 {
    public static void main(String[] args) {
        SpringApplication.run(MachineConsumer_9001.class,args);
    }
}
