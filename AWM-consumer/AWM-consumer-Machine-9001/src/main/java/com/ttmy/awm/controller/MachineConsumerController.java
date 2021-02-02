package com.ttmy.awm.controller;

import com.ttmy.awm.api.Service.MachineClientService;
import com.ttmy.awm.api.pojo.Machine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
public class MachineConsumerController {
//    //消费者，不应该有service层
//    //RestTemplate ... 供我们直接调用就可以了 注册到Spring中
//    @Autowired
//    //提供多种便捷访问远程http服务的方法，简单的restful服务模板
//    private RestTemplate restTemplate;
//
//    //Ribbon,这里的地址应该是一个变量，通过服务名来访问
//    private static final String REST_URL_PREFIX = "http://AWM-PROVIDER-MACHINESERVER-8091";

    @Autowired
    private MachineClientService machineClientService;

    @RequestMapping("/consumer/Machine/list")
    public List<Machine> list(){
//        return restTemplate.getForObject(REST_URL_PREFIX + "/Machine/queryAll/list", List.class);
        return machineClientService.queryUserById();
    }

    @RequestMapping("/consumer/Machine/findbyid/{id}")
    public Machine findbyid(@PathVariable("id") String id){
        return machineClientService.querybyid(id);
    }
}
