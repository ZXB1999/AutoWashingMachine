package com.ttmy.awm.service.impl;

import com.ttmy.awm.api.pojo.Washingmachine;
import com.ttmy.awm.dao.MachineMapper;
import com.ttmy.awm.service.MachineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MachineServiceImpl implements MachineService {
    @Autowired
    private MachineMapper machineMapper;

    public List<Washingmachine> queryAll() {
        return machineMapper.selectList(null);
    }

}
