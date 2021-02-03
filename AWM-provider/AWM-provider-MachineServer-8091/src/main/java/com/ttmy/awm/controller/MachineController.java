package com.ttmy.awm.controller;


import com.ttmy.awm.api.pojo.Washingmachine;
import com.ttmy.awm.service.MachineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/Machine")
public class MachineController {
    @Autowired
    private MachineService machineService;

    @GetMapping("/queryAll/list")
    public List<Washingmachine> queryAll() {
        return machineService.queryAll();
    }

}
