package com.ttmy.awm.service;

import com.ttmy.awm.api.pojo.Awmorder;

import java.util.List;

public interface OrderService {
    int creatorder (Awmorder neworder);
    List<Awmorder> usingorder (String customerid);
}
