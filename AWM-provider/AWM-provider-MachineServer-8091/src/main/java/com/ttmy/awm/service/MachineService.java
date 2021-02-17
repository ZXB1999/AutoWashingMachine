package com.ttmy.awm.service;



import com.ttmy.awm.api.pojo.Washingmachine;
import com.ttmy.awm.api.pojo.vo.MachineStateVo;

import java.util.List;

public interface MachineService {

    List<Washingmachine> queryAll();

    Washingmachine findMachineById(String machineId);

    List<MachineStateVo> queryMachineState(String machineId);

}
