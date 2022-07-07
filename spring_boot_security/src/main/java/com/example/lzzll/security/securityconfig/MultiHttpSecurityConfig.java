package com.example.lzzll.security.securityconfig;

import com.example.lzzll.security.service.SecurityUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

// prePostEnabled=true会解锁@PreAuthorize和@PostAuthorize两个注解，
// 顾名思义，@PreAuthorize注解会在方法执行前进行验证，而@PostAuthorize 注解在方法执行后进行验证。
// securedEnabled=true会解锁@Secured注解。
// 认证规则写在WebSecurityConfigurerAdapter的实现类中，可以同时定义多个实现类，定义不同的规则，通过@Order()注解来指定执行顺序
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true,securedEnabled = true)
public class MultiHttpSecurityConfig{

    @Autowired
    private SecurityUserService securityUserServiceImpl;

    /**
     * 在内存中写入三个用户，用来方便做后续的认证测试
     * @param auth
     * @throws Exception
     */
//    @Autowired
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication()
//            .withUser("root")
//            .password(passwordEncoder().encode("123456"))
//            .roles("ADMIN", "DBA")
//            .and()
//            .withUser("admin")
//            .password(passwordEncoder().encode("123456"))
//            .roles("ADMIN", "USER")
//            .and()
//            .withUser("minmin")
//            .password(passwordEncoder().encode("123456"))
//            .roles("USER");
//
//    }

    /**
     * 直接从数据库获取用户来进行登录
     * @param auth
     * @throws Exception
     */
    @Autowired
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(securityUserServiceImpl).passwordEncoder(passwordEncoder());
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * 第一个认证类
     */
    @Configuration
    @Order(2)
    public static class AdminSecurityConfig extends WebSecurityConfigurerAdapter {

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.antMatcher("/admin/**")
                    .authorizeRequests()           //该类配置url为/admin/
                    .anyRequest()
                    .hasRole("ADMIN");
        }
    }

    /**
     * 其它认证类
     */
    @Configuration
    @Order(1)
    public static class OtherSecurityConfig extends WebSecurityConfigurerAdapter{
        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.authorizeRequests()
                    .anyRequest()
                    .authenticated()
                    .and()
                    .formLogin()
                    .loginProcessingUrl("/login")
                    .permitAll()
                    .and()
                    .csrf()
                    .disable();
        }
    }

    public static void main(String[] args) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encode = passwordEncoder.encode("123456");
        System.out.println(encode);
    }
}
