package com.ttmy.awm.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ttmy.awm.api.pojo.Awmorder;
import com.ttmy.awm.dao.OrderMapper;
import com.ttmy.awm.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderserviceImpl implements OrderService {
    @Autowired
    private OrderMapper orderMapper;
    public int creatorder(Awmorder neworder) {
        return orderMapper.insert(neworder);
    }

    public List<Awmorder> usingorder() {
        QueryWrapper<Awmorder> wrapper = new QueryWrapper();
        wrapper.in("order_state","1");
        return orderMapper.selectList(wrapper);
    }
}
