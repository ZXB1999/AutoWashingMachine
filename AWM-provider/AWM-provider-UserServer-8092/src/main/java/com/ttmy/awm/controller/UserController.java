package com.ttmy.awm.controller;

import com.ttmy.awm.api.pojo.User;
import com.ttmy.awm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/User")
public class UserController {
    @Autowired
    private UserService userService;
    @GetMapping("/queryUserById/{UserId}")
    public User queryUserById(@PathVariable("UserId") String id){
        return userService.queryUserById(id);
    }
}
