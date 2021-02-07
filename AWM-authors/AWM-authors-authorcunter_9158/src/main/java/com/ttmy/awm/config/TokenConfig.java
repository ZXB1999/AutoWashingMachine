package com.ttmy.awm.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

@Configuration
public class TokenConfig {

    //redis链接模板
    @Autowired
    RedisConnectionFactory redisConnectionFactory;
    /**
     * 在新生成令牌时会调用这个方法，用来将原始token以及一些用户信息封装
     * 制作token令牌的地方？JWT增强
     * @return
     */
//    @Bean
//    public JwtAccessTokenConverter accessTokenConverter() {
//        JwtAccessTokenConverter accessTokenConverter = new JwtAccessTokenConverter() {
//            /***
//             * 重写增强token方法,用于自定义一些token总需要封装的信息
//             */
//            @Override
//            public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
////                String userName = authentication.getUserAuthentication().getName();
//                // 得到用户名，去处理数据库可以拿到当前用户的信息和角色信息（需要传递到服务中用到的信息）
////                final Map<String, Object> additionalInformation = new HashMap<>();
//
////                Object data = memberFeign.getmemberByuserName(userName).getData().get("userInfo");
////                String jsonString = JSON.toJSONString(data);
////                UcenterMember member = JSONObject.parseObject(jsonString, UcenterMember.class);
//
//                // Map假装用户实体
////                Map<String,String> userinfo = new HashMap<String,String>();
////                userinfo.put("id", member.getId() + "");
////                userinfo.put("username", member.getUsername());
//
////                additionalInformation.put("userinfo", JSON.toJSONString(userinfo));
////                ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalInformation);
//                OAuth2AccessToken enhancedToken = super.enhance(accessToken, authentication);
//                return enhancedToken;
////                return null;
//            }
//        };
//        // 测试用,资源服务使用相同的字符达到一个对称加密的效果,生产时候使用RSA非对称加密方式
//        accessTokenConverter.setSigningKey("scmaSecurity");
//        return accessTokenConverter;
//    }

    /**
     * 存入redis的bean
     * @return
     */
    @Bean
    public TokenStore tokenStore() {
        RedisTokenStore tokenStore = new RedisTokenStore(redisConnectionFactory);
        return tokenStore;
    }
}
