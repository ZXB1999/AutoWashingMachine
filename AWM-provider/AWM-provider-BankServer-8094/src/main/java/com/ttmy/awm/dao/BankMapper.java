package com.ttmy.awm.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ttmy.awm.api.pojo.Bankse;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface BankMapper extends BaseMapper<Bankse> {
}
