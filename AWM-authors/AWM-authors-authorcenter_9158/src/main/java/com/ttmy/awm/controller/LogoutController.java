package com.ttmy.awm.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;


@RestController
public class LogoutController {
    @Resource
    private ConsumerTokenServices consumerTokenServices;

    @ApiOperation("登出并删除token")
    @GetMapping("/oauth/Logout/{accesstoken}")
    public Map<String,String> revokeToken(@PathVariable("accesstoken") String accesstoken) {
        Map<String,String> map = new HashMap<String, String>();
        if (consumerTokenServices.revokeToken(accesstoken)){
            map.put("msg","success");
            return map;
        }else {
            map.put("msg","You are not recorded");
            return map;
        }

    }
}
