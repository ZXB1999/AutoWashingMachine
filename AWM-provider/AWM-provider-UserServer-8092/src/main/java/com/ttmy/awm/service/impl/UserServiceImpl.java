package com.ttmy.awm.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ttmy.awm.api.pojo.Awmuser;
import com.ttmy.awm.api.pojo.vo.UserPageVo;
import com.ttmy.awm.constant.BaceConst;
import com.ttmy.awm.dao.UserMapper;
import com.ttmy.awm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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

    public List<Awmuser> findallUser(Integer current, Integer size) {
        UserPageVo userVo = new UserPageVo();
        IPage<Awmuser> page = new Page<Awmuser>(current, size);
        QueryWrapper<Awmuser> wrapper = new QueryWrapper();
        wrapper.in("delflag", BaceConst.DELFLAG_USEFUL);
        wrapper.orderByAsc("create_time");
        userMapper.selectPage(page, wrapper);
        userVo.setCurrent(current);
        userVo.setSize(size);
        userVo.setTotal(page.getTotal());
        userVo.setAwmuserList(page.getRecords());
        return userVo.getAwmuserList();
    }

    public int countuser() {
        return userMapper.selectCount(null);
    }

    public int PseudodeletelistUser(List<Awmuser> users) {
        for (Awmuser user: users) {
            user.setDelflag(BaceConst.DELFLAG_UNUSEFUL).setUpdateTime(new Date());
            QueryWrapper<Awmuser> wrapper = new QueryWrapper();
            wrapper.in("awmuser_id",user.getAwmuserId());
            userMapper.update(user,wrapper);
            System.out.println(user.toString());
        }
        return 0;
    }

    public List<Awmuser> userrecyclebin() {
        QueryWrapper<Awmuser> wrapper = new QueryWrapper();
        wrapper.in("delflag",BaceConst.DELFLAG_UNUSEFUL);
        return userMapper.selectList(wrapper);
    }

    public List<Awmuser> MulticonditionalqueryUser(Map<String, Object> map,int current,int size) {
        UserPageVo userVo = new UserPageVo();
        IPage<Awmuser> page = new Page<Awmuser>(current, size);
        QueryWrapper<Awmuser> wrapper = new QueryWrapper();
        wrapper.in("delflag",BaceConst.DELFLAG_USEFUL);
        if(map.containsKey("awmusername")){
            wrapper.like("awmusername",map.get("awmusername"));
        }
        if(map.containsKey("begin")&&map.containsKey("end")){
            wrapper.between("create_time",map.get("begin"),map.get("end"));
        }
        userMapper.selectPage(page, wrapper);
        userVo.setCurrent(current);
        userVo.setSize(size);
        userVo.setTotal(page.getTotal());
        userVo.setAwmuserList(page.getRecords());
        return userVo.getAwmuserList();
    }

    public int MulticonditionalqueryUsersize(Map<String, Object> map) {
        QueryWrapper<Awmuser> wrapper = new QueryWrapper();
        wrapper.in("delflag",BaceConst.DELFLAG_USEFUL);
        if(map.containsKey("awmusername")){
            wrapper.like("awmusername",map.get("awmusername"));
        }
        if(map.containsKey("begin")&&map.containsKey("end")){
            wrapper.between("create_time",map.get("begin"),map.get("end"));
        }
        return userMapper.selectCount(wrapper);
    }
}
