package com.ttmy.awm.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ttmy.awm.api.pojo.Awmorder;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface OrderMapper extends BaseMapper<Awmorder> {

    @Select("SELECT EXTRACT(HOUR FROM create_time) FROM awmorder")
    List<String> statisticalhour();

    @Select("SELECT DAYNAME(create_time) FROM awmorder")
    List<String> statisticalweek();

    @Select("SELECT DAYNAME(create_time) ,serverlevel FROM awmorder")
    List<Map<String,String>> statisticalsaleroom();
}
