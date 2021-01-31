package com.ttmy.awm.api.Service;

import com.ttmy.awm.api.pojo.Machine;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MachineClientServiceFallBackFactory implements FallbackFactory {
    public MachineClientService create(Throwable throwable) {
        return new MachineClientService() {
            public List<Machine> queryUserById() {
                return (List<Machine>) new java.awt.List();
            }
        };
    }
}
