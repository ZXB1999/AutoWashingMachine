package com.ttmy.awm.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ttmy.awm.api.pojo.vo.MachineStateVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface MachineStateMapper extends BaseMapper<MachineStateVo> {

    @Select("select\n" +
            "    m.brand , m.type, m.model , s.state ,s.server , c.serverlevel , c.cost\n" +
            "from\n" +
            "    washingmachine m ,washingserver s ,servercontext c\n" +
            "where\n" +
            "    m.machine_id=s.machine_id\n" +
            "    and m.type=s.server and s.server =c.servertype\n" +
            "    and m.delflag='0'\n" +
            "    and m.machine_id=#{id}")
    List<MachineStateVo> queryMachineState(String id);
}
