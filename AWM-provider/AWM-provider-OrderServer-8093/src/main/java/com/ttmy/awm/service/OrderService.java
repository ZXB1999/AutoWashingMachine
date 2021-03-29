package com.ttmy.awm.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ttmy.awm.api.pojo.Awmorder;
import com.ttmy.awm.api.pojo.Awmuser;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface OrderService {
    int creatorder (Awmorder neworder);
    List<Awmorder> usingorder (String customerid) throws JsonProcessingException;
    List<Awmorder> historyorder (String customerid);
    int updateorderstate(Awmorder neworder);
    List<Awmorder> allOrder (Integer current, Integer size);
    int countorder();
    Map<String,Integer> statisticalhour();
    Map<String,Integer> statisticalweek();
    Map<String, BigDecimal> statisticalsaleroom();
}
