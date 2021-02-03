package com.ttmy.awm.controller;

import com.ttmy.awm.api.Service.MachineClientService;
import com.ttmy.awm.api.pojo.Washingmachine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MachineConsumerController {

    @Autowired
    private MachineClientService machineClientService;

    @RequestMapping("/consumer/Machine/list")
    public List<Washingmachine> list(){
        return machineClientService.queryUserById();
    }

    @RequestMapping("/consumer/Machine/findbyid/{id}")
    public Washingmachine findbyid(@PathVariable("id") String id){
        return machineClientService.querybyid(id);
    }
}
