package com.ttmy.awm.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


@RestController
public class LogoutController {
    @Resource
    private ConsumerTokenServices consumerTokenServices;

    @ApiOperation("登出并删除token")
    @GetMapping("/oauth/Logout/{accesstoken}")
    public String revokeToken(@PathVariable("accesstoken") String accesstoken) {
        if (consumerTokenServices.revokeToken(accesstoken)){
            return "退出登录";
        }
        return "您还没有登陆";
    }
}
