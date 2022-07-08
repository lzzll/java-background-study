package com.example.lzzll.shiro.config;

import com.example.lzzll.shiro.entity.User;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.realm.AuthenticatingRealm;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author lf
 * @Date 2022/7/8 15:43
 * @Description:
 */
@Configuration
public class ShiroConfig extends AuthenticatingRealm {


    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher(){
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        hashedCredentialsMatcher.setHashAlgorithmName(Md5Hash.ALGORITHM_NAME);
        hashedCredentialsMatcher.setHashIterations(1024);
        return hashedCredentialsMatcher;
    }



    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        return null;
    }


    public static void main(String[] args) {
        User user = new User();
        user.setUserName("minmin");
        user.setPassword("704");
        SimpleAuthenticationInfo simpleAuthen = new SimpleAuthenticationInfo(user,user.getPassword(),user.getUserName());
        System.out.println(simpleAuthen);
    }
}
