package com.ttmy.awm.service;

import com.ttmy.awm.api.pojo.Awmuser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    LoginService loginService;
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        /**
         * 这个方法要依据用户名到库里查密码，把用户名密码对象返回，再有别的方法比对两个密码
         */
        Awmuser user = loginService.qutyByname(username);
        UserDetails userDetails;
        if(user.getAwmusername().equals("admin")){
            userDetails = User.withUsername(user.getAwmusername()).password(user.getPassword()).authorities("ADMIN").build();
//            System.out.println("管理员登陆了");
        }else {
            userDetails = User.withUsername(user.getAwmusername()).password(user.getPassword()).authorities("USER").build();
//            System.out.println("用户登陆了");
        }
        return userDetails;
    }
}
