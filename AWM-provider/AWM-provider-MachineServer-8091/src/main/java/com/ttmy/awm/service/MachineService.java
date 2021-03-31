package com.ttmy.awm.service;



import com.ttmy.awm.api.pojo.Washingmachine;
import com.ttmy.awm.api.pojo.Washingserver;
import com.ttmy.awm.api.pojo.vo.MachineStateVo;

import java.util.List;
import java.util.Map;

public interface MachineService {

    List<Washingmachine> queryAll();

    Washingmachine findMachineById(String machineId);

    List<MachineStateVo> queryMachineState(String machineId);

    int updatestatus(Washingserver newstate);

    List<Washingmachine> queryAllMachine(Integer current, Integer size);

    int countmachine();

    int creatnewMachine(Washingmachine newMachine);

    List<Washingmachine> queryMachine(String MachineId,String Brand,Integer current, Integer size);

    int PseudodeleteMachine(String MachineId);

    List<Map<String,Object>> machinerecyclebin() throws Exception;
}
