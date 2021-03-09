package com.ttmy.awm.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ttmy.awm.api.pojo.Awmorder;
import com.ttmy.awm.dao.OrderMapper;
import com.ttmy.awm.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import com.ttmy.awm.constant.OrderState;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class OrderserviceImpl implements OrderService {
    @Autowired
    private OrderMapper orderMapper;
    public int creatorder(Awmorder neworder) {
        return orderMapper.insert(neworder);
    }

    /**
     * 当前正在进行中的订单
     * @param customerid
     * @return
     */
    public List<Awmorder> usingorder(String customerid) {
        QueryWrapper<Awmorder> wrapper = new QueryWrapper();
        wrapper.in("order_state",OrderState.USING_ORDER);
        wrapper.in("customer_id",customerid);
        return orderMapper.selectList(wrapper);
    }

    /**
     * 历史订单
     * @param customerid
     * @return
     */
    public List<Awmorder> historyorder(String customerid) {
        QueryWrapper<Awmorder> wrapper = new QueryWrapper();
        wrapper.in("order_state",OrderState.FINISH_ORDER);
        wrapper.in("customer_id",customerid);
        return orderMapper.selectList(wrapper);
    }

    /**
     * 结束后改变订单状态
     * @param changeorder
     * @return
     */
    public int updateorderstate(Awmorder changeorder) {
        QueryWrapper<Awmorder> wrapper = new QueryWrapper();
        wrapper.in("machine_id",changeorder.getMachineId());
        wrapper.in("customer_id",changeorder.getCustomerId());
        return orderMapper.update(changeorder,wrapper);
    }
}
