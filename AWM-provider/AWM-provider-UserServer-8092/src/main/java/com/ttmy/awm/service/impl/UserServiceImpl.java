package com.ttmy.awm.service.impl;

import com.ttmy.awm.api.pojo.User;
import com.ttmy.awm.dao.UserMapper;
import com.ttmy.awm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    public User queryUserById(String UserId) {
        return userMapper.queryUserById(UserId);
    }
}
