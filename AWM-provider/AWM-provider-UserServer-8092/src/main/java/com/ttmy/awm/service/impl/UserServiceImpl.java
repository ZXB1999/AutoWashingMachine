package com.ttmy.awm.service.impl;

import com.ttmy.awm.api.pojo.Awmuser;
import com.ttmy.awm.dao.UserMapper;
import com.ttmy.awm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    public List<Awmuser> queryUserById(String UserId) {
        Map<String,Object> a = new HashMap();
        a.put("awmuser_id",UserId);
        return userMapper.selectByMap(a);
    }

    public int insertNewUser(Awmuser newuser){
        return userMapper.insert(newuser);
    }
}
