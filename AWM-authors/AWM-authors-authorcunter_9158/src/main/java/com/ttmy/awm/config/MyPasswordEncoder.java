package com.ttmy.awm.config;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * 密码加密？？？
 *
 */
@Component
public class MyPasswordEncoder implements PasswordEncoder{

    public String encode(CharSequence charSequence) {
        return charSequence.toString();
    }

    public boolean matches(CharSequence charSequence, String s) {
        return s.equals(charSequence.toString());
    }
}
