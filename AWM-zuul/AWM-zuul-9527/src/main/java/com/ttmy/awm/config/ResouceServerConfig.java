package com.ttmy.awm.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;

@Configuration
public class ResouceServerConfig  {
    public static final String RESOURCE_ID = "zuulGetway";

    //测试拦截资源
    @Configuration
    @EnableResourceServer
    public class UAAServerConfig extends ResourceServerConfigurerAdapter {
        @Autowired
        private TokenStore tokenStore;
        @Override
        public void configure(ResourceServerSecurityConfigurer resources){
            resources.tokenStore(tokenStore).resourceId(RESOURCE_ID)
                    .stateless(true);
        }
        @Override
        public void configure(HttpSecurity http) throws Exception {
            http.authorizeRequests()
                    .antMatchers("/uaa/**").permitAll();///uaa/** 网关不进行拦截。
        }
    }


//    //uaa资源服务配置
//    @Configuration
//    @EnableResourceServer
//    public class UAAServerConfig extends ResourceServerConfigurerAdapter {
//        @Autowired
//        private TokenStore tokenStore;
//        @Override
//        public void configure(ResourceServerSecurityConfigurer resources){
//            resources.tokenStore(tokenStore).resourceId(RESOURCE_ID)
//                    .stateless(true);
//        }
//        @Override
//        public void configure(HttpSecurity http) throws Exception {
//            http.authorizeRequests()
//                    .antMatchers("/uaa/**").permitAll();
//        }
//    }
//    //order资源
//    //uaa资源服务配置
//    @Configuration
//    @EnableResourceServer
//    public class OrderServerConfig extends ResourceServerConfigurerAdapter {
//        @Autowired
//        private TokenStore tokenStore;
//
//        @Override
//        public void configure(ResourceServerSecurityConfigurer resources){
//            resources.tokenStore(tokenStore).resourceId(RESOURCE_ID)
//                    .stateless(true);
//        }
//
//        @Override
//        public void configure(HttpSecurity http) throws Exception {
//            http
//                    .authorizeRequests()
//                    .antMatchers("/order/**").access("#oauth2.hasScope('ROLE_API')");
//        }
//    }
//    //配置其它的资源服务..

}
