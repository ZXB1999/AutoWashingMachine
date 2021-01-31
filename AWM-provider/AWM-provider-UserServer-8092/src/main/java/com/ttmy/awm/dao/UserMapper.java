package com.ttmy.awm.dao;

import com.ttmy.awm.api.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserMapper {
    User queryUserById(String UserId);
}
