package com.ttmy.awm.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ttmy.awm.api.pojo.Awmuser;
import com.ttmy.awm.mapper.LoginMapper;
import com.ttmy.awm.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    LoginMapper loginMapper;

    public Awmuser qutyByname(String username) {
        QueryWrapper<Awmuser> wrapper = new QueryWrapper();
        wrapper.in("awmusername",username);
        return loginMapper.selectOne(wrapper);
    }
}
