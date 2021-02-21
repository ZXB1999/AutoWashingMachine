package com.ttmy.awm.controller;

import com.ttmy.awm.api.pojo.Awmuser;
import com.ttmy.awm.service.UserService;
import com.ttmy.awm.constant.LoginMsg;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @ApiOperation("验证支付密码")
    @PostMapping("/examine")
    public Boolean examinePaypwd(@RequestBody Awmuser newuser){
        return userService.examinePaypwd(newuser);
    }

}
