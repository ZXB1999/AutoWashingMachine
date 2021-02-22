package com.ttmy.awm.service.Impl;

import com.ttmy.awm.api.pojo.Awmorder;
import com.ttmy.awm.dao.OrderMapper;
import com.ttmy.awm.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderserviceImpl implements OrderService {
    @Autowired
    private OrderMapper orderMapper;
    public int creatorder(Awmorder neworder) {
        return orderMapper.insert(neworder);
    }
}
