package com.ttmy.awm.dao;

import com.ttmy.awm.api.pojo.Machine;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface MachineMapper {
    List<Machine> queryAll();
}
