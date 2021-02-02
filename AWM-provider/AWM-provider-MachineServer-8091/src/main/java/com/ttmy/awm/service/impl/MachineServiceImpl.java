package com.ttmy.awm.service.impl;

import com.ttmy.awm.api.pojo.Machine;
import com.ttmy.awm.dao.MachineMapper;
import com.ttmy.awm.service.MachineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MachineServiceImpl implements MachineService {
    @Autowired
    private MachineMapper machineMapper;
    public List<Machine> queryAll() {
        return machineMapper.queryAll();
    }

    public Machine quaryMachineByID(String id) {
        return machineMapper.quaryMachineByID(id);
    }
}
