package com.ttmy.awm.service;

import com.ttmy.awm.api.pojo.Machine;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MachineService {
    List<Machine> queryAll();

    Machine quaryMachineByID(String id);
}
