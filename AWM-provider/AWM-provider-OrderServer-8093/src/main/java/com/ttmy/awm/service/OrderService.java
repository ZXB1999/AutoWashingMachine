package com.ttmy.awm.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ttmy.awm.api.pojo.Awmorder;
import com.ttmy.awm.api.pojo.Awmuser;

import java.util.List;

public interface OrderService {
    int creatorder (Awmorder neworder);
    List<Awmorder> usingorder (String customerid) throws JsonProcessingException;
    List<Awmorder> historyorder (String customerid);
    int updateorderstate(Awmorder neworder);
    List<Awmorder> allOrder (Integer current, Integer size);
    int countorder();
}
