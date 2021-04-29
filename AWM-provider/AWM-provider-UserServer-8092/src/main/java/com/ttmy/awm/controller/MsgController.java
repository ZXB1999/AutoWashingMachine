package com.ttmy.awm.controller;

import com.ttmy.awm.api.pojo.Usermsg;
import com.ttmy.awm.service.MsgService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class MsgController {
    @Autowired
    private MsgService msgService;

    @ApiOperation("获取某位用户的扩展资料")
    @PostMapping("/usermsg/{username}")
    public Usermsg usermsg(@PathVariable String username){
        return msgService.getuserMsg(username);
    }

    @ApiOperation("更新电话信息")
    @PostMapping("/uptphone/{username}")
    public int uptphone(@PathVariable String username,@RequestBody Map<String,String> phonenumber){
        return msgService.setphonenumber(username,phonenumber.get("phonenumber"));
    }
}
