package com.ttmy.awm.config;

import com.ttmy.awm.service.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;


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

    @Autowired(required = false)
    AuthorizationCodeServices authorizationCodeServices;

    @Autowired
    ClientDetailsService clientDetailsService;

    @Autowired
    TokenConfig tokenConfig;

    @Autowired
    MyUserDetailsService myUserDetailsService;

    /**
     * 客户端详情
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
                .secret("scma_app") //客户端密钥，与客户端id一起，校验客户端合法性
                .resourceIds(SOURCE_ID)//可以访问的资源列表，可访问那些资源呢
                .authorizedGrantTypes("password","client_credentials","refresh_token","authorization_code")//认证的模式，授权码（authorization-code）需要客户端获取许可码再发送给认证服务器
                                                                    // 隐藏式（implicit）？？？？
                                                                     //密码式（password）需要发送用户名密码
                                                                    // 客户端凭证（client credentials） 无需发送
                .scopes("all") //生效范围、客户端范围，名称自定义，必填*/
                .authorities("ADMIN") //client 的权限,  不能为null.  强调一下这不是用户的权限(角色) , 这是client自己的属性
                .redirectUris("http://www.baidu.com")//回调地址
                ;
    }
    /**令牌怎么发放，怎么存储
     * 绑定令牌 授权服务器端点配置 就是配置从哪个url申请令牌，里面还要配置令牌生成的方法
     * @param endpoints
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
//        endpoints.accessTokenConverter(accessTokenConverter());//转换token格式
        endpoints
                .authenticationManager(authenticationManager)//密码模式要用
                .authorizationCodeServices(authorizationCodeServices)//授权码模式要用
                .tokenServices(tokenServices())//令牌管理服务
                .allowedTokenEndpointRequestMethods(HttpMethod.POST)//允许post提交

        ;//这里将token存入redis
    }


    //令牌访问端点（url）安全约束
    @Override
    public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
        oauthServer
                .tokenKeyAccess("permitAll()")  //公钥令牌端点（url）公开 ‘/oauth/token_key’
                .checkTokenAccess("permitAll()") // 检测令牌端点（url）公开 ‘/oauth/check_token’
                .allowFormAuthenticationForClients();// 允许表单认证
    }

    //客户端服务
    @Bean
    public AuthorizationServerTokenServices tokenServices(){
        DefaultTokenServices services = new DefaultTokenServices();
        services.setClientDetailsService(clientDetailsService);
        services.setSupportRefreshToken(true);
        services.setTokenStore(tokenConfig.tokenStore());
        services.setAccessTokenValiditySeconds(ACCESS_TOKEN_TIMER); //有效时间
        services.setRefreshTokenValiditySeconds(REFRESH_TOKEN_TIMER); //刷新时间
        return services;
    }
}
