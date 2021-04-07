package com.ttmy.awm.service;

import com.ttmy.awm.api.pojo.Awmuser;
import org.springframework.web.bind.annotation.PathVariable;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

public interface UserService {
    Boolean examinePaypwd(Awmuser newuser);

    Awmuser queryUserByName(String username);

    List<Awmuser> findallUser(Integer current, Integer size);

    int countuser();

    int PseudodeletelistUser(List<Awmuser> users);

    List<Awmuser> userrecyclebin();

    List<Awmuser> MulticonditionalqueryUser(Map<String,Object> map,int current,int size);

    int MulticonditionalqueryUsersize(Map<String,Object> map);
}
