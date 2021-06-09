package com.ttmy.awm.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ttmy.awm.api.pojo.Bankse;
import com.ttmy.awm.dao.BankMapper;
import com.ttmy.awm.service.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@Transactional
public class BankServiceImpl implements BankService {
    @Autowired
    private BankMapper bankMapper;
    public BigDecimal queryBalance(String UserId) {
        QueryWrapper<Bankse> wrapper = new QueryWrapper();
        wrapper.in("user",UserId);
        Bankse bankse = bankMapper.selectOne(wrapper);
        if (bankse==null){
            Bankse newbankse = new Bankse();
            newbankse.setBalance(new BigDecimal(0)).setUser(UserId);
            bankMapper.insert(newbankse);
            return newbankse.getBalance();
        }else {
            return bankse.getBalance();
        }
    }

    public void recharge(String UserId) {
        QueryWrapper<Bankse> wrapper = new QueryWrapper();
        wrapper.in("user",UserId);
        Bankse bankse = bankMapper.selectOne(wrapper);
        if (bankse==null){
            Bankse newbankse = new Bankse();
            newbankse.setBalance(new BigDecimal(100)).setUser(UserId);
            bankMapper.insert(newbankse);
        }else {
            BigDecimal newbalances = bankse.getBalance().add(new BigDecimal(100));
            bankMapper.update(bankse.setBalance(newbalances),wrapper);
        }
    }
}
