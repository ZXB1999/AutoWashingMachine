package com.ttmy.awm.api.Service;


import com.ttmy.awm.api.pojo.Washingmachine;
import com.ttmy.awm.api.pojo.Washingserver;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MachineClientServiceFallBackFactory implements FallbackFactory {
    public MachineClientService create(Throwable throwable) {
        return new MachineClientService() {
            public List<Washingmachine> queryUserById() {
                return (List<Washingmachine>) new java.awt.List();
            }

            public Washingmachine querybyid(String id) {
                return new Washingmachine().setBrand("服务降级");
            }

            public int updatestatus(Washingserver newstate) {
                return 0;
            }
        };
    }
}
