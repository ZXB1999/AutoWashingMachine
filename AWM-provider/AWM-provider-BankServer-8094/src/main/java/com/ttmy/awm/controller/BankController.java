package com.ttmy.awm.controller;

import com.ttmy.awm.api.Service.UserClientService;
import com.ttmy.awm.api.pojo.Awmuser;
import com.ttmy.awm.service.BankService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
public class BankController {

    @Autowired
    private BankService bankService;

    @Autowired
    private UserClientService userClientService;

    @ApiOperation("查询余额接口")
    @GetMapping("/queryBalance/{id}")
    public BigDecimal queryBalance(@PathVariable("id") String UserId) {
        Awmuser awmuser = userClientService.queryUserById(UserId);
        if (awmuser!=null){
            return bankService.queryBalance(UserId);
        }
        else {
            return null;
        }
    }

    @ApiOperation("充值接口")
    @GetMapping("/recharge/{id}")
    public void recharge(@PathVariable("id") String UserId) {
        Awmuser awmuser = userClientService.queryUserById(UserId);
        if (awmuser!=null){
            bankService.recharge(UserId);
        }
    }
}
