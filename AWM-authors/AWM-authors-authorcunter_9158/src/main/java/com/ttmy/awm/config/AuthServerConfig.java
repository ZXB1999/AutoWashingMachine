package com.ttmy.awm.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;


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

    @Autowired
    TokenConfig tokenConfig;


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
                .scopes("all") //生效范围、客户端范围，名称自定义，必填*/
                .authorities("ADMIN") //client 的权限,  不能为null.  强调一下这不是用户的权限(角色) , 这是client自己的属性
                .secret("scma_app") //密码,要保密
                .accessTokenValiditySeconds(ACCESS_TOKEN_TIMER) //有效时间
                .refreshTokenValiditySeconds(REFRESH_TOKEN_TIMER) ;//刷新时间
    }

    //绑定令牌 授权服务器端点配置
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
//        endpoints.accessTokenConverter(accessTokenConverter());//转换token格式
        endpoints.tokenStore(tokenConfig.tokenStore()).authenticationManager(authenticationManager);//这里将token存入redis
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
        oauthServer.allowFormAuthenticationForClients();// 允许表单认证
    }

}
