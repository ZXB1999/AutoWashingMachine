package com.ttmy.awm.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ttmy.awm.api.pojo.Awmorder;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface OrderMapper extends BaseMapper<Awmorder> {
}
