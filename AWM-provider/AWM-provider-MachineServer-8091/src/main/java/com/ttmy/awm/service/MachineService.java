package com.ttmy.awm.service;



import com.ttmy.awm.api.pojo.Washingmachine;
import com.ttmy.awm.api.pojo.Washingserver;
import com.ttmy.awm.api.pojo.vo.MachineStateVo;

import java.util.List;

public interface MachineService {

    List<Washingmachine> queryAll();

    Washingmachine findMachineById(String machineId);

    List<MachineStateVo> queryMachineState(String machineId);

    int updatestatus(Washingserver newstate);

    List<Washingmachine> queryAllMachine(Integer current, Integer size);

    int countmachine();

    int creatnewMachine(Washingmachine newMachine);
}
