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
import com.ttmy.awm.service.QRcodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
public class MachineServiceImpl implements MachineService {
    @Autowired
    private MachineMapper machineMapper;

    @Autowired
    private MachineStateMapper machineStateMapper;

    @Autowired
    private WashingServerMapper washingServerMapper;

    @Autowired
    private QRcodeService qRcodeService;

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
        QueryWrapper<Washingmachine> wrapper = new QueryWrapper();
        wrapper.in("delflag",BaceConst.DELFLAG_USEFUL);
        wrapper.orderByDesc("create_time");
        machineMapper.selectPage(page, wrapper);
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
        QueryWrapper<Washingmachine> wrapper = new QueryWrapper();
        wrapper.in("delflag",BaceConst.DELFLAG_USEFUL);
        return machineMapper.selectCount(wrapper);
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
        wrapper.in("delflag",BaceConst.DELFLAG_USEFUL);
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
        Pseudodelete.setUpdateTime(new Date());
        Pseudodelete.setDelflag(BaceConst.DELFLAG_UNUSEFUL);
        return machineMapper.updateById(Pseudodelete);
    }

    public List<Map<String,Object>> machinerecyclebin() throws Exception {
        List<Map<String,Object>> returnlist = new ArrayList<Map<String,Object>>();
        QueryWrapper<Washingmachine> wrapper = new QueryWrapper();
        wrapper.in("delflag",BaceConst.DELFLAG_UNUSEFUL);
        List<Washingmachine> washingmachines = machineMapper.selectList(wrapper);
        for (Washingmachine machine: washingmachines) {
            Map<String,Object> map = new HashMap<String, Object>();
            map.put("machine_id",machine.getMachineId());
            map.put("brand",machine.getBrand());
            map.put("model",machine.getModel());
            map.put("type",machine.getType());
            map.put("create_time",machine.getCreateTime());
            map.put("update_time",machine.getUpdateTime());
            map.put("longitude",machine.getLongitude());
            map.put("latitude",machine.getLatitude());
            map.put("brob",qRcodeService.QRcode(machine.getMachineId()));
            returnlist.add(map);
        }
        return returnlist;
    }

    public int PseudodeletelistMachine(List<Washingmachine> washingmachines) {
        int flag =0;
        for (Washingmachine washingmachine: washingmachines) {
            QueryWrapper<Washingmachine> wrapper = new QueryWrapper();
            wrapper.in("machine_id",washingmachine.getMachineId());
            washingmachine.setUpdateTime(new Date());
            washingmachine.setDelflag(BaceConst.DELFLAG_UNUSEFUL);
            machineMapper.update(washingmachine,wrapper);
            flag++;
        }
        return flag;
    }

    /**
     * 查询出来的总条数 用作分页
     * @param MachineId
     * @param Brand
     * @return
     */
    public int queryMachinesize(String MachineId, String Brand) {
        QueryWrapper<Washingmachine> wrapper = new QueryWrapper();
        wrapper.in("delflag",BaceConst.DELFLAG_USEFUL);
        if (MachineId!=null&&MachineId!=""){
            wrapper.in("machine_id",MachineId);
        }
        if (Brand!=null&&Brand!=""){
            wrapper.in("brand",Brand);
        }
        return machineMapper.selectCount(wrapper);
    }

    /**
     * 更新设备接口
     * @param uptmachine
     */
    public void commituptmachine(Washingmachine uptmachine) {
        QueryWrapper<Washingmachine> wrapper = new QueryWrapper();
        wrapper.in("machine_id",uptmachine.getMachineId());
        uptmachine.setUpdateTime(new Date());
        machineMapper.update(uptmachine,wrapper);
    }
}
