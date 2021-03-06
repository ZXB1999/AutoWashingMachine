package com.ttmy.awm.controller;

import com.ttmy.awm.api.Service.MachineClientService;
import com.ttmy.awm.api.pojo.Awmorder;
import com.ttmy.awm.api.pojo.Washingserver;
import com.ttmy.awm.service.OrderService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class OrderController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private MachineClientService machineClientService;

    @ApiOperation("创建订单")
    @PostMapping("/creatneworder")
    public void creatneworder (@RequestBody Awmorder neworder){
        if(orderService.creatorder(neworder)>0) {
            Washingserver newstate = new Washingserver();

            //这里可以根据服务等级开启定时器

            newstate.setMachineId(neworder.getMachineId());
            newstate.setState("1");

            machineClientService.updatestatus(newstate);
            System.out.println("订单成功创建，开启定时器，并改变设备状态");
            //订单成功创建，开启定时器，并改变设备状态
        }
    }

    @ApiOperation("当前订单")
    @GetMapping("/useingOrder")
    public List<Awmorder> useingOrder(){
        return orderService.usingorder();
    }
}
