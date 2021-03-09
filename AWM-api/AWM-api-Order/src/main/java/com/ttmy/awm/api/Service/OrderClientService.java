package com.ttmy.awm.api.Service;

import com.ttmy.awm.api.pojo.Awmorder;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Component
@FeignClient(value = "AWM-PROVIDER-ORDERSERVER-8093")
public interface OrderClientService {
    @PostMapping("/creatneworder")
    public void creatneworder (@RequestBody Awmorder neworder);

    @PutMapping("/changeorderstate")
    public int changeorderstate(@RequestBody Awmorder neworder);
}
