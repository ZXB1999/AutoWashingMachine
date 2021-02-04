package com.ttmy.awm.service;

import com.ttmy.awm.api.pojo.Awmuser;

import java.util.List;

public interface UserService {
    List<Awmuser> queryUserById(String UserId);

    int insertNewUser(Awmuser newuser);

    boolean checkUser(Awmuser newuser);
}
