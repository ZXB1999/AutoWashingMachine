package com.ttmy.awm.api.Service;

import com.ttmy.awm.api.pojo.Awmuser;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

@Component
public class UserClientServiceFallBackFactory implements FallbackFactory {
    public UserClientService create(Throwable throwable) {
        return new UserClientService(){
            public Awmuser queryUserById(String id) {
                return new Awmuser()
                        .setAwmuserId(id)
                        .setAwmname("服务降级")
                        .setAwmusername("服务降级");
            }
        } ;
    }
}
