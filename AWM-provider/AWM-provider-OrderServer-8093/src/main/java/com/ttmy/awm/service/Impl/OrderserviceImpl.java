package com.ttmy.awm.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ttmy.awm.api.Service.MachineClientService;
import com.ttmy.awm.api.pojo.Awmorder;
import com.ttmy.awm.api.pojo.vo.OrderPageVo;
import com.ttmy.awm.constant.BaceConst;
import com.ttmy.awm.dao.OrderMapper;
import com.ttmy.awm.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ttmy.awm.constant.OrderState;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class OrderserviceImpl implements OrderService {
    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private MachineClientService machineClientService;

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

    /**
     * 统计每小时订单量
     * @return
     */
    public Map<String,Integer> statisticalhour() {
        int temporary=0;
        Map<String,Integer> map = new HashMap<String, Integer>();
        for (String hour: orderMapper.statisticalhour()) {
            if(map.containsKey(hour)){
                temporary = map.get(hour)+1;
                map.remove(hour);
                map.put(hour,temporary);
            }else {
                map.put(hour,new Integer(1));
            }
        }
        return map;
    }

    /**
     * 统计星期销售量
     * @return
     */
    @Override
    public Map<String, Integer> statisticalweek() {
        int temporary=0;
        Map<String,Integer> map = new HashMap<String, Integer>();
        for (String week: orderMapper.statisticalweek()) {
            if(map.containsKey(week)){
                temporary = map.get(week)+1;
                map.remove(week);
                map.put(week,temporary);
            }else {
                map.put(week,new Integer(1));
            }
        }
        return map;
    }

    /**
     * 统计星期销售额
     * @return
     */
    @Override
    public Map<String, BigDecimal> statisticalsaleroom() {
        BigDecimal temporary = new BigDecimal(0);
        Map<String, BigDecimal> saleroom =new HashMap<String, BigDecimal>();
        //拿到单价了
        Map<String, BigDecimal> servercost = machineClientService.servercost();
        List<Map<String, String>> statisticalsaleroom = orderMapper.statisticalsaleroom();
        for (Map<String, String> map: statisticalsaleroom) {
            if (saleroom.containsKey(map.get("DAYNAME(create_time)"))){
                temporary = saleroom.get(map.get("DAYNAME(create_time)")).add(servercost.get(map.get("serverlevel")));
                saleroom.remove(map.get("DAYNAME(create_time)"));
                saleroom.put(map.get("DAYNAME(create_time)"),temporary);
            }else {
                saleroom.put(map.get("DAYNAME(create_time)"),servercost.get(map.get("serverlevel")));
            }
        }
        return saleroom;
    }
}
