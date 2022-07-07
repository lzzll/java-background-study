package com.example.lzzll.security.oauthconfig;

import com.example.lzzll.security.service.SecurityUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.approval.ApprovalStore;
import org.springframework.security.oauth2.provider.approval.JdbcApprovalStore;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.security.oauth2.provider.code.JdbcAuthorizationCodeServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

import javax.sql.DataSource;

/**
 * @program: springboot-security-oauth2-demo
 * @description:
 * @author: 波波烤鸭
 * @create: 2019-12-04 23:12
 */
@Configuration
@EnableAuthorizationServer
public class OauthServerConfig extends AuthorizationServerConfigurerAdapter {
    //数据库连接池对象
    @Autowired
    private DataSource dataSource;

    // 持久化策略到redis中
    @Autowired
    private RedisConnectionFactory redisConnectionFactory;

    //认证业务对象
    @Autowired
    private SecurityUserService userService;

    //授权模式专用对象
    @Autowired
    private AuthenticationManager authenticationManager;

    //客户端信息来源
    @Bean
    public JdbcClientDetailsService jdbcClientDetailsService(){
        return new JdbcClientDetailsService(dataSource);
    }

    /**
     * 指定token的持久化策略
     * InMemoryTokenStore表示将token存储在内存
     * RedisTokenStore表示将token存储在redis中
     * JdbcTokenStore存储在数据库中
     * @return
     */
    @Bean
    public TokenStore tokenStore(){
        return new JdbcTokenStore(dataSource);
//        return new RedisTokenStore(redisConnectionFactory);
//        return new InMemoryTokenStore();
    }

    //授权信息保存策略
    @Bean
    public ApprovalStore approvalStore(){
        return new JdbcApprovalStore(dataSource);
    }

    //授权码模式数据来源
    @Bean
    public AuthorizationCodeServices authorizationCodeServices(){
        return new JdbcAuthorizationCodeServices(dataSource);
    }

    //指定客户端信息的数据库来源
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        // 从数据库获取客户端数据来源
        clients.withClientDetails(jdbcClientDetailsService());

        // 直接在内存中写死客户端
////        password 方案一：明文存储，用于测试，不能用于生产
////        String finalSecret = "123456";
////        password 方案二：用 BCrypt 对密码编码
////        String finalSecret = new BCryptPasswordEncoder().encode("123456");
//        // password 方案三：支持多种编码，通过密码的前缀区分编码方式
//        String DEMO_RESOURCE_ID = "order";
//        String finalSecret = "{bcrypt}" + new BCryptPasswordEncoder().encode("123456");
//        //配置两个客户端,一个用于password认证一个用于client认证
//        //并且设置两个作用域web、app，分别针对不同场景
//        clients.inMemory().withClient("client_1")
//                .resourceIds(DEMO_RESOURCE_ID)
//                .authorizedGrantTypes("client_credentials", "refresh_token")
//                .scopes("web")
//                .authorities("oauth2")
//                .secret(finalSecret)
//                .and().withClient("client_2")
//                .resourceIds(DEMO_RESOURCE_ID)
//                .authorizedGrantTypes("password", "refresh_token")
//                .scopes("app")
//                .authorities("oauth2")
//                .secret(finalSecret);

    }

    //检查token的策略
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        //允许表单认证
        security.allowFormAuthenticationForClients();
        security.checkTokenAccess("isAuthenticated()");
    }

    //OAuth2的主配置信息
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints
                .approvalStore(approvalStore())
                .authenticationManager(authenticationManager)
                .authorizationCodeServices(authorizationCodeServices())
                .tokenStore(tokenStore());
    }




}
