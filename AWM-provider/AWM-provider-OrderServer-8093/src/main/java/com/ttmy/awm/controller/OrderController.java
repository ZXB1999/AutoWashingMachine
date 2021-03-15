package com.ttmy.awm.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ttmy.awm.api.Service.MachineClientService;
import com.ttmy.awm.api.Service.UserClientService;
import com.ttmy.awm.api.pojo.Awmorder;

import com.ttmy.awm.api.pojo.Washingserver;
import com.ttmy.awm.constant.MachineState;
import com.ttmy.awm.service.OrderService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrderController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private MachineClientService machineClientService;
    @Autowired
    private UserClientService userClientService;

    /**
     * 创建订单并改变设备状态
     * @param neworder
     */
    @ApiOperation("创建订单")
    @PostMapping("/creatneworder")
    public synchronized void creatneworder (@RequestBody Awmorder neworder){
        if(orderService.creatorder(neworder)>0) {
            Washingserver newstate = new Washingserver();
            newstate.setMachineId(neworder.getMachineId());
            newstate.setState(MachineState.MACHINE_USEING);
            machineClientService.updatestatus(newstate);
        }
    }

    @ApiOperation("当前订单")
    @GetMapping("/useingOrder/{userid}")
    public List<Awmorder> useingOrder(@PathVariable("userid") String userid) throws JsonProcessingException {
        return orderService.usingorder(userClientService.queryUserById(userid).getAwmuserId());
    }

    @ApiOperation("历史订单")
    @GetMapping("/historyOrder/{userid}")
    public List<Awmorder> historyOrder(@PathVariable("userid") String userid){
        return orderService.historyorder(userClientService.queryUserById(userid).getAwmuserId());
    }

    @ApiOperation("结束后更新订单状态")
    @PutMapping("/changeorderstate")
    public int changeorderstate(@RequestBody Awmorder neworder){
        return orderService.updateorderstate(neworder);
    }

    @ApiOperation("查询全部订单(ADMIN)")
    @GetMapping("/allOrder/{current}/{size}")
    public List<Awmorder> allOrder(@PathVariable("current") int current,@PathVariable("size") int size){
        return orderService.allOrder(current,size);
    }

    @ApiOperation("查询总条数(ADMIN)")
    @GetMapping("/countorder")
    public int countorder(){
        return orderService.countorder();
    }
}
