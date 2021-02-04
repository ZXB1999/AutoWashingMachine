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

    /**
     * 根据账号查询用户
     * @param UserId
     * @return
     */
    public List<Awmuser> queryUserById(String UserId) {
        Map<String,Object> a = new HashMap();
        a.put("awmuser_id",UserId);
        return userMapper.selectByMap(a);
    }

    /**
     * 新增用户（注册）
     * @param newuser
     * @return
     */
    public int insertNewUser(Awmuser newuser){
        return userMapper.insert(newuser);
    }

    /**
     * 账号密码验证
     * @param newuser
     * @return
     */
    public boolean checkUser(Awmuser newuser) {
        Map<String,Object> a = new HashMap();
        a.put("awmusername",newuser.getAwmusername());
        a.put("password",newuser.getPassword());
        return !userMapper.selectByMap(a).isEmpty();
    }


}
