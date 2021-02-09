package com.ttmy.awm.controller;

import com.ttmy.awm.api.pojo.Awmuser;
import com.ttmy.awm.service.LoginService;
import com.ttmy.awm.service.RegisterService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class RegisterController {
    @Autowired
    RegisterService registerService;
    @Autowired
    LoginService loginService;

    @ApiOperation("注册新用户")
    @PostMapping("/oauth/register")
    public Map<String,String> register(@RequestBody Awmuser user) {
        Map<String,String> map = new HashMap<String, String>();
        if(loginService.qutyByname(user.getAwmusername())!=null){
            map.put("msg","该用户已经存在");
            return map;
        }
        if (registerService.registerUser(user)!=1){
            map.put("msg","注册失败");
            return map;
        }
        map.put("msg","注册成功");
        return map;
    }
}
