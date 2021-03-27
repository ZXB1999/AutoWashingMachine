package com.ttmy.awm.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ttmy.awm.api.pojo.Awmorder;
import com.ttmy.awm.api.pojo.Awmuser;
import com.ttmy.awm.api.pojo.vo.OrderPageVo;
import com.ttmy.awm.api.pojo.vo.UserPageVo;
import com.ttmy.awm.constant.BaceConst;
import com.ttmy.awm.dao.OrderMapper;
import com.ttmy.awm.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import com.ttmy.awm.constant.OrderState;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class OrderserviceImpl implements OrderService {
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    /**
     * 创建订单
     * @param neworder
     * @return
     */
    public int creatorder(Awmorder neworder) {
        return orderMapper.insert(neworder);
    }

    /**
     * 当前正在进行中的订单
     * @param customerid
     * @return
     */
    public List<Awmorder> usingorder(String customerid){
        QueryWrapper<Awmorder> wrapper = new QueryWrapper();
        wrapper.in("order_state",OrderState.USING_ORDER);
        wrapper.in("customer_id",customerid);
        List<Awmorder> list = orderMapper.selectList(wrapper);
        return list;
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

    /**
     * 管理员查询全部订单
     * @return
     */
    public List<Awmorder> allOrder(Integer current, Integer size) {
        OrderPageVo orderVo = new OrderPageVo();
        IPage<Awmorder> page = new Page<Awmorder>(current, size);
        QueryWrapper<Awmorder> wrapper = new QueryWrapper();
        wrapper.in("delflag", BaceConst.DELFLAG_USEFUL);
        wrapper.orderByDesc("create_time");
        orderMapper.selectPage(page, wrapper);
        orderVo.setCurrent(current);
        orderVo.setSize(size);
        orderVo.setTotal(page.getTotal());
        orderVo.setAwmorderList(page.getRecords());
        return orderVo.getAwmorderList();
    }

    public int countorder() {
        return orderMapper.selectCount(null);
    }
}
