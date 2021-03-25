package com.ttmy.awm.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ttmy.awm.api.pojo.vo.MachinePageVo;
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

    /**
     * 分页查询设备
     * @param current
     * @param size
     * @return
     */
    public List<Washingmachine> queryAllMachine(Integer current, Integer size) {
        MachinePageVo machinePageVo = new MachinePageVo();
        IPage<Washingmachine> page = new Page<Washingmachine>(current, size);
        machineMapper.selectPage(page, null);
        machinePageVo.setCurrent(current);
        machinePageVo.setSize(size);
        machinePageVo.setTotal(page.getTotal());
        machinePageVo.setWashingmachineList(page.getRecords());
        return machinePageVo.getWashingmachineList();
    }

    /**
     * 条数统计
     * @return
     */
    public int countmachine() {
        return machineMapper.selectCount(null);
    }

    /**
     * 创建新订单
     * @admin
     * @param newMachine
     * @return
     */
    public int creatnewMachine(Washingmachine newMachine) {

        if(machineMapper.insert(newMachine)!=0){
            Washingserver newstate = new Washingserver();
            newstate.setMachineId(newMachine.getMachineId());
            newstate.setState(MachineState.MACHINE_USEFUL);
            newstate.setServer(newMachine.getType());
            return washingServerMapper.insert(newstate);
        }
        return 0;
    }

    /**
     * 按设备id或品牌查询设备
     * @param MachineId
     * @param Brand
     * @param current
     * @param size
     * @return
     */
    public List<Washingmachine> queryMachine(String MachineId, String Brand, Integer current, Integer size) {
        QueryWrapper<Washingmachine> wrapper = new QueryWrapper();
        MachinePageVo machinePageVo = new MachinePageVo();
        IPage<Washingmachine> page = new Page<Washingmachine>(current, size);
        if (MachineId!=null&&MachineId!=""){
            wrapper.in("machine_id",MachineId);
        }
        if (Brand!=null&&Brand!=""){
            wrapper.in("brand",Brand);
        }
        machineMapper.selectPage(page, wrapper);
        machinePageVo.setCurrent(current);
        machinePageVo.setSize(size);
        machinePageVo.setTotal(page.getTotal());
        machinePageVo.setWashingmachineList(page.getRecords());
        return machinePageVo.getWashingmachineList();
    }

    /**
     * 伪删除设备
     * @param MachineId
     * @return
     */
    public int PseudodeleteMachine(String MachineId) {
        Washingmachine Pseudodelete = new Washingmachine();
        Pseudodelete.setMachineId(MachineId);
        Pseudodelete.setDelflag(BaceConst.DELFLAG_UNUSEFUL);
        return machineMapper.updateById(Pseudodelete);
    }

}
