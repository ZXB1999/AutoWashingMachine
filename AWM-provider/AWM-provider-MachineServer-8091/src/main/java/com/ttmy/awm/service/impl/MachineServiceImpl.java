package com.ttmy.awm.service.impl;

import com.ttmy.awm.api.pojo.Washingmachine;
import com.ttmy.awm.api.pojo.vo.MachineStateVo;
import com.ttmy.awm.dao.MachineMapper;
import com.ttmy.awm.dao.MachineStateMapper;
import com.ttmy.awm.service.MachineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MachineServiceImpl implements MachineService {
    @Autowired
    private MachineMapper machineMapper;

    @Autowired
    private MachineStateMapper machineStateMapper;

    /**
     * 查询所有机器
     * @return
     */
    public List<Washingmachine> queryAll() {
        return machineMapper.selectList(null);
    }

    public Washingmachine findMachineById(String machineId) {
        return machineMapper.selectById(machineId);
    }

    public List<MachineStateVo> queryMachineState(String machineId) {
        return machineStateMapper.queryMachineState(machineId);
    }

}
