package com.ttmy.awm.controller;

import com.ttmy.awm.api.pojo.Awmuser;
import com.ttmy.awm.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/User")
public class UserController {
    @Autowired
    private UserService userService;

    @ApiOperation("根据唯一标识查询用户")
    @GetMapping("/queryUserById/{UserId}")
    public List<Awmuser> queryUserById(@PathVariable("UserId") String id){
        return userService.queryUserById(id);
    }

    @ApiOperation("创建新用户")
    @PostMapping("/insert/newuser")
    public int insertnewUser(@RequestBody Awmuser newuser){
        return userService.insertNewUser(newuser);
    }

    @ApiOperation("用户登陆验证")
    @PostMapping("/login")
    public Map<String,String> login(@RequestBody Awmuser newuser){
        if(userService.checkUser(newuser)){
            Map<String, String> map = new HashMap<String, String>();
            map.put("token",UUID.randomUUID().toString().replaceAll("-",""));
            return map;
        }
        return null;
    }
}
