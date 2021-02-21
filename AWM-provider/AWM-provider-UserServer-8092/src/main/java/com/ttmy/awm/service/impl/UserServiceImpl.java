package com.ttmy.awm.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
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

    public Boolean examinePaypwd(Awmuser newuser) {
        QueryWrapper<Awmuser> wrapper = new QueryWrapper();
        wrapper.in("awmusername",newuser.getAwmusername());
        wrapper.in("paypwd",newuser.getPaypwd());
        if (userMapper.selectOne(wrapper)!=null){
            return true;
        }
        return false;
    }
}
