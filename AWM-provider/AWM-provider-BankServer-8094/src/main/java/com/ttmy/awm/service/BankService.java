package com.ttmy.awm.service;

import java.math.BigDecimal;

public interface BankService {
    BigDecimal queryBalance(String UserId);

    void recharge(String UserId);
}
