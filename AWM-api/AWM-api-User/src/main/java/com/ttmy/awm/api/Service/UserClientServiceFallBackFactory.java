package com.ttmy.awm.api.Service;

import com.ttmy.awm.api.pojo.User;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

@Component
public class UserClientServiceFallBackFactory implements FallbackFactory {
    public UserClientService create(Throwable throwable) {
        return new UserClientService(){
            public User queryUserById(String id) {
                return new User()
                        .setAwmuserId(id)
                        .setAwmname("服务降级")
                        .setAwmusername("服务降级");
            }
        } ;
    }
}
