package com.ttmy.awm.controller;

import com.ttmy.awm.api.pojo.Awmuser;
import com.ttmy.awm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/User")
public class UserController {
    @Autowired
    private UserService userService;
    @GetMapping("/queryUserById/{UserId}")
    public List<Awmuser> queryUserById(@PathVariable("UserId") String id){
        return userService.queryUserById(id);
    }

    @PostMapping("/insert/newuser")

    public int insertnewUser(@RequestBody Awmuser newuser){
        return userService.insertNewUser(newuser);
    }
}
