package com.ttmy.awm.api.Service;

import com.ttmy.awm.api.pojo.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient(value = "AWM-PROVIDER-USERSERVER-8092",fallbackFactory = UserClientServiceFallBackFactory.class)
public interface UserClientService {
    @GetMapping("/User/queryUserById/{UserId}")
    User queryUserById(@PathVariable("UserId") String id);
}
