package com.ttmy.awm.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ttmy.awm.constant.*;
import com.ttmy.awm.api.pojo.Washingmachine;
import com.ttmy.awm.api.pojo.Washingserver;
import com.ttmy.awm.api.pojo.vo.MachineStateVo;
import com.ttmy.awm.dao.MachineMapper;
import com.ttmy.awm.dao.MachineStateMapper;
import com.ttmy.awm.dao.WashingServerMapper;
import com.ttmy.awm.service.MachineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class MachineServiceImpl implements MachineService {
    @Autowired
    private MachineMapper machineMapper;

    @Autowired
    private MachineStateMapper machineStateMapper;

    @Autowired
    private WashingServerMapper washingServerMapper;

    /**
     * 查询所有机器
     * @return
     */
    public List<Washingmachine> queryAll() {
        QueryWrapper<Washingmachine> wrapper = new QueryWrapper();
        wrapper.in("delflag",BaceConst.DELFLAG_USEFUL);
        return machineMapper.selectList(wrapper);
    }

    public Washingmachine findMachineById(String machineId) {
        QueryWrapper<Washingmachine> wrapper = new QueryWrapper();
        wrapper.in("machine_id",machineId);
        wrapper.in("delflag",BaceConst.DELFLAG_USEFUL);
        return machineMapper.selectOne(wrapper);
    }

    /**
     * 根据设备ID查找机器
     * @param machineId
     * @return
     */
    public List<MachineStateVo> queryMachineState(String machineId) {
        return machineStateMapper.queryMachineState(machineId);
    }

    /**
     * 更新设备状态
     * @param newstate
     * @return
     */
    public int updatestatus(Washingserver newstate) {
        QueryWrapper<Washingserver> wrapper = new QueryWrapper();
        wrapper.in("machine_id",newstate.getMachineId());
        return washingServerMapper.update(newstate,wrapper);
    }

}
