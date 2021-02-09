package com.ttmy.awm.service.Impl;

import com.ttmy.awm.api.pojo.Awmuser;
import com.ttmy.awm.mapper.LoginMapper;
import com.ttmy.awm.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegisterServiceImpl implements RegisterService {
    @Autowired
    LoginMapper loginMapper;
    public int registerUser(Awmuser user) {
        return loginMapper.insert(user);
    }
}
