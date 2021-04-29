package com.ttmy.awm.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ttmy.awm.api.Service.UserClientService;
import com.ttmy.awm.api.pojo.Awmuser;
import com.ttmy.awm.api.pojo.Usermsg;
import com.ttmy.awm.dao.MsgMapper;
import com.ttmy.awm.service.MsgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MsgServiceImpl implements MsgService {
    @Autowired
    private MsgMapper msgMapper;
    @Autowired
    private UserClientService userClientService;

    public Usermsg getuserMsg(String username) {
        Awmuser awmuser = userClientService.queryUserById(username);
        QueryWrapper<Usermsg> wrapper = new QueryWrapper();
        wrapper.in("userid",awmuser.getAwmuserId());
        Usermsg usermsg = msgMapper.selectOne(wrapper);
        if(usermsg==null){
            msgMapper.insert(new Usermsg().setUserid(awmuser.getAwmuserId()));
        }
        return usermsg;
    }

    public int setphonenumber(String username, String phonenumber) {
        Awmuser awmuser = userClientService.queryUserById(username);
        QueryWrapper<Usermsg> wrapper = new QueryWrapper();
        wrapper.in("userid",awmuser.getAwmuserId());
        System.out.println(phonenumber);
        return msgMapper.update(msgMapper.selectOne(wrapper).setPhonenum(phonenumber),wrapper);
    }

}
