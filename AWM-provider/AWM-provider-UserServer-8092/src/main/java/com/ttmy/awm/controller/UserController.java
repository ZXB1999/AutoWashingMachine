package com.ttmy.awm.controller;

import com.ttmy.awm.api.pojo.Awmuser;
import com.ttmy.awm.service.UserService;
import com.ttmy.awm.constant.LoginMsg;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
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

    @ApiOperation("获取用户唯一id")
    @GetMapping("/queryUserByName/{UserId}")
    public Awmuser queryUserByName(@PathVariable("UserId") String name){
        return userService.queryUserByName(name);
    }

    @ApiOperation("查询所有用户(ADMIN)")
    @GetMapping("/findallUser/{current}/{size}")
    public List<Awmuser> findallUser(@PathVariable("current") int current,@PathVariable("size") int size){
        return userService.findallUser(current,size);
    }

    @ApiOperation("查询总条数(ADMIN)")
    @GetMapping("/countuser")
    public int countuser(){
        return userService.countuser();
    }

    @ApiOperation("伪删除用户(ADMIN)")
    @PutMapping("/PseudodeletelistUser")
    public int PseudodeletelistUser(@RequestBody List<Awmuser> users){
        return userService.PseudodeletelistUser(users);
    }

    @ApiOperation("小黑屋-用户封禁回收站(ADMIN)")
    @GetMapping("/blackhouse")
    public List<Awmuser> blackhouse(){
        return userService.userrecyclebin();
    }

    @ApiOperation("多条件查询用户(ADMIN)")
    @PostMapping("/MulticonditionalqueryUser/{current}/{size}")
    public List<Awmuser> MulticonditionalqueryUser(@RequestBody Map<String,Object> map,@PathVariable("current") int current,@PathVariable("size") int size){
        return userService.MulticonditionalqueryUser(map,current,size);
    }

    @ApiOperation("多条件查询用户数据量(ADMIN)")
    @PostMapping("/MulticonditionalqueryUsersize")
    public int MulticonditionalqueryUser(@RequestBody Map<String,Object> map){
        return userService.MulticonditionalqueryUsersize(map);
    }
}
