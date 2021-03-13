package com.ttmy.awm.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ttmy.awm.api.pojo.Awmuser;
import com.ttmy.awm.dao.UserMapper;
import com.ttmy.awm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
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

    public Awmuser queryUserByName(String username) {
        QueryWrapper<Awmuser> wrapper = new QueryWrapper();
        wrapper.in("awmusername",username);
        return userMapper.selectOne(wrapper);
    }

    public List<Awmuser> findallUser() {
        return userMapper.selectList(null);
    }
}
