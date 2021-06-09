package com.ttmy.awm.api.Service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.math.BigDecimal;

@Component
@FeignClient(value = "AWM-PROVIDER-BANKSERVER-8094")
public interface BankClientService {
    @GetMapping("/queryBalance/{id}")
    BigDecimal queryBalance(@PathVariable("id") String UserId);
}
