package com.ttmy.awm.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

@Configuration
@EnableAuthorizationServer//开启授权服务器
/**
 * 权限服务器核心配置
 */
public class AuthServerConfig extends AuthorizationServerConfigurerAdapter {
    // 资源ID，可定义在数据库
    private static final String SOURCE_ID = "scmaSecurity";
    private static final int ACCESS_TOKEN_TIMER = 60 * 60 * 24;
    private static final int REFRESH_TOKEN_TIMER = 60 * 60 * 24 * 30;

    //权限消息bean
    @Autowired(required = false)
    AuthenticationManager authenticationManager;
    //redis链接模板
    @Autowired
    RedisConnectionFactory redisConnectionFactory;

    //这里是外部数据入口，通过feign
//    @Autowired
//    private MemberFeign memberFeign;

    /**
     * 这个方法在服务刚启动就会加载，用来初始化验证信息
     * 认证服务的入口！！！！！！！！
     * 如果grant——type是"client_credentials"客户端模式,服务端不会再验证了,就会直接走accessTokenConverter()方法返回授权码
     * 如果grant——type是"password"密码模式,服务端就会介入验证合法性
     * @param clients
     * @throws Exception
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()//内存模式，将来可以改为基于数据库判定
                .withClient("myapp")//客户端id，对应的是请求参数“client_id”
                .resourceIds(SOURCE_ID)//资源id，暂时不理解
                .authorizedGrantTypes("client_credentials","password","refresh_token")//认证的模式，授权码（authorization-code）需要客户端获取许可码再发送给认证服务器
                                                                    // 隐藏式（implicit）？？？？
                                                                     //密码式（password）需要发送用户名密码
                                                                    // 客户端凭证（client credentials） 无需发送
                .scopes("all") //生效范围
                .authorities("ADMIN") //client 的权限,  不能为null.  强调一下这不是用户的权限(角色) , 这是client自己的属性
                .secret("scma_app") //密文？加密依据？密钥？
                .accessTokenValiditySeconds(ACCESS_TOKEN_TIMER) //有效时间
                .refreshTokenValiditySeconds(REFRESH_TOKEN_TIMER) ;//刷新时间
    }

    //绑定令牌
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
//        endpoints.accessTokenConverter(accessTokenConverter());//转换token格式
        endpoints.tokenStore(tokenStore()).authenticationManager(authenticationManager);
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
        oauthServer.allowFormAuthenticationForClients();// 允许表单认证
    }

    /**
     * 在新生成令牌时会调用这个方法，用来将原始token以及一些用户信息封装
     * 制作token令牌的地方？JWT增强
     * @return
     */
    @Bean
    public JwtAccessTokenConverter accessTokenConverter() {
        JwtAccessTokenConverter accessTokenConverter = new JwtAccessTokenConverter() {
            /***
             * 重写增强token方法,用于自定义一些token总需要封装的信息
             */
            @Override
            public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
//                String userName = authentication.getUserAuthentication().getName();
                // 得到用户名，去处理数据库可以拿到当前用户的信息和角色信息（需要传递到服务中用到的信息）
//                final Map<String, Object> additionalInformation = new HashMap<>();

//                Object data = memberFeign.getmemberByuserName(userName).getData().get("userInfo");
//                String jsonString = JSON.toJSONString(data);
//                UcenterMember member = JSONObject.parseObject(jsonString, UcenterMember.class);

                // Map假装用户实体
//                Map<String,String> userinfo = new HashMap<String,String>();
//                userinfo.put("id", member.getId() + "");
//                userinfo.put("username", member.getUsername());

//                additionalInformation.put("userinfo", JSON.toJSONString(userinfo));
//                ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalInformation);
                OAuth2AccessToken enhancedToken = super.enhance(accessToken, authentication);
                return enhancedToken;
//                return null;
            }
        };
        // 测试用,资源服务使用相同的字符达到一个对称加密的效果,生产时候使用RSA非对称加密方式
        accessTokenConverter.setSigningKey("scmaSecurity");
        return accessTokenConverter;
    }

    @Bean
    public TokenStore tokenStore() {
        RedisTokenStore tokenStore = new RedisTokenStore(redisConnectionFactory);
        return tokenStore;
    }
}
