package com.ttmy.awm.api.Service;


import com.ttmy.awm.api.pojo.Washingmachine;
import com.ttmy.awm.api.pojo.Washingserver;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Component
@FeignClient(value = "AWM-PROVIDER-MACHINESERVER-8091",fallbackFactory = MachineClientServiceFallBackFactory.class)
public interface MachineClientService {
    @GetMapping("/Machine/queryAll/list")
    List<Washingmachine> queryUserById();

    @GetMapping("/Machine/querybyid/{id}")
    Washingmachine querybyid(@PathVariable("id") String id);

    @PostMapping("/UpdateState")
    int updatestatus(@RequestBody Washingserver newstate);

    @GetMapping("/servercost")
    public Map<String, BigDecimal> servercost();
}
