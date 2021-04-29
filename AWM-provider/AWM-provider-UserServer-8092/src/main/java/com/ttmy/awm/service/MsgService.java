package com.ttmy.awm.service;

import com.ttmy.awm.api.pojo.Usermsg;

public interface MsgService {
    Usermsg getuserMsg(String username);

    /**
     * 更新电话信息
     * @param username
     * @param phonenumber
     * @return
     */
    int setphonenumber(String username,String phonenumber);
}
