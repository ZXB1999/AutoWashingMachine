package com.ttmy.awm.controller;

import com.ttmy.awm.api.Service.UserClientService;
import com.ttmy.awm.api.pojo.Awmuser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    private UserClientService userClientService;
    @GetMapping("/user/findbyid/{id}")
    public Awmuser findbyid(@PathVariable("id") String id){
        return this.userClientService.queryUserById(id);
    }
}
