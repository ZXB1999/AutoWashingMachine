package com.ttmy.awm.service;

import com.ttmy.awm.api.pojo.Awmuser;

import java.util.List;

public interface UserService {
    Boolean examinePaypwd(Awmuser newuser);

    Awmuser queryUserByName(String username);
}
