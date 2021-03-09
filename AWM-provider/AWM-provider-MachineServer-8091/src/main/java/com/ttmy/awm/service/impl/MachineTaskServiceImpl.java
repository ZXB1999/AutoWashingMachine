package com.ttmy.awm.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ttmy.awm.api.Service.OrderClientService;
import com.ttmy.awm.api.pojo.Awmorder;
import com.ttmy.awm.api.pojo.Washingserver;
import com.ttmy.awm.dao.WashingServerMapper;
import com.ttmy.awm.service.MachineTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.ttmy.awm.constant.*;

@Service
@Transactional
public class MachineTaskServiceImpl implements MachineTaskService {
    @Autowired
    WashingServerMapper washingServerMapper;
    @Autowired
    OrderClientService orderClientService;
    @Async
    public void startProduction(Awmorder neworder) throws InterruptedException {
        System.out.println("异步任务开始");
        for (int i = 0; i < 100; i++) {
            System.out.println(i);
            Thread.sleep(1000);
        }
        System.out.println("异步任务结束");
        neworder.setOrderState(OrderState.FINISH_ORDER);
        //通知订单模块改变状态
        orderClientService.changeorderstate(neworder);
        QueryWrapper<Washingserver> wrapper = new QueryWrapper();
        wrapper.in("machine_id",neworder.getMachineId());
        washingServerMapper.update(new Washingserver().setState(MachineState.MACHINE_USEFUL),wrapper);
    }
}
