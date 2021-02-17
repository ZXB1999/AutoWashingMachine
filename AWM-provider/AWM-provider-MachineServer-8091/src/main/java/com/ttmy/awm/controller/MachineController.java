package com.ttmy.awm.controller;


import com.ttmy.awm.api.pojo.Washingmachine;
import com.ttmy.awm.api.pojo.vo.MachineStateVo;
import com.ttmy.awm.service.MachineService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MachineController {
    @Autowired
    private MachineService machineService;

    @ApiOperation("查询所有机器")
    @GetMapping("/queryAll/list")
    public List<Washingmachine> queryAll() {
        return machineService.queryAll();
    }

    @ApiOperation("按ID(扫描二维码)查询机器")
    @GetMapping("/ById/{id}")
    public Washingmachine queryById(@PathVariable("id") String machineId) {
        return machineService.findMachineById(machineId);
    }

    @ApiOperation("按ID(扫描二维码)查询机器")
    @GetMapping("/ByState/{id}")
    public List<MachineStateVo> queryByMachineState(@PathVariable("id") String machineId) {
        return machineService.queryMachineState(machineId);
    }



}
