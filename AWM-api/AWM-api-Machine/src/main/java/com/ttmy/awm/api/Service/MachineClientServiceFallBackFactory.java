package com.ttmy.awm.api.Service;


import com.ttmy.awm.api.pojo.Washingmachine;
import com.ttmy.awm.api.pojo.Washingserver;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

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

            public Map<String, BigDecimal> servercost() {
                return null;
            }
        };
    }
}
