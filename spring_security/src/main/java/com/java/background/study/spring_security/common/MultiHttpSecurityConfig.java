package com.java.background.study.spring_security.common;

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

// 多个HttpSecurity，并且加密，方法安全
//prePostEnabled=true会解锁@PreAuthorize和@PostAuthorize两个注解，
// 顾名思义，@PreAuthorize注解会在方法执行前进行验证，而@PostAuthorize 注解在方法执行后进行验证。
//securedEnabled=true会解锁@Secured注解。
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true,securedEnabled = true)
public class MultiHttpSecurityConfig{

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    // 新增三个角色密码都是123456
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("root")
                .password("$2a$10$pywhG4sDLWGnyDnEiUDhjOnaEsbw4xx3hUCUqLKI5ZsnHCnyLMmIe")
                .roles("ADMIN", "DBA")
                .and()
                .withUser("admin")
                .password("$2a$10$pywhG4sDLWGnyDnEiUDhjOnaEsbw4xx3hUCUqLKI5ZsnHCnyLMmIe")
                .roles("ADMIN", "USER")
                .and()
                .withUser("sang")
                .password("$2a$10$pywhG4sDLWGnyDnEiUDhjOnaEsbw4xx3hUCUqLKI5ZsnHCnyLMmIe")
                .roles("USER");
    }


    @Configuration
    @Order(1)
    public static class AdminSecurityConfig extends WebSecurityConfigurerAdapter {
        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.antMatcher("/admin/**")
                    .authorizeRequests()           //该类配置url为/admin/
                    .anyRequest()
                    .hasRole("ADMIN");
        }
    }


    @Configuration
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
