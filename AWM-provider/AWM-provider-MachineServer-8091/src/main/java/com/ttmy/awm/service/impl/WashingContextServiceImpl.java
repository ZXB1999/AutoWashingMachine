package com.ttmy.awm.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ttmy.awm.api.pojo.Servercontext;
import com.ttmy.awm.dao.WashingContextMapper;
import com.ttmy.awm.service.WashingContextService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class WashingContextServiceImpl implements WashingContextService {

    @Autowired
    private WashingContextMapper washingContextMapper;

    public Map<String, BigDecimal> servercost() {
        Map<String, BigDecimal> map = new HashMap<String,BigDecimal>();
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.select("DISTINCT serverlevel,cost");
        List<Servercontext> list = washingContextMapper.selectList(wrapper);
        for (Servercontext context: list) {
            map.put(context.getServerlevel(),context.getCost());
        }
        return map;
    }
}
