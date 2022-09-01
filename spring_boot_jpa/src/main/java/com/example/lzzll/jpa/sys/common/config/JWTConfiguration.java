package com.example.lzzll.jpa.sys.common.config;

import com.example.lzzll.jpa.sys.common.interceptor.JwtTokenInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @Author lf
 * @Date 2020/7/6 14:47
 * @Description:
 */
@Configuration
public class JWTConfiguration extends WebMvcConfigurerAdapter {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new JwtTokenInterceptor()).addPathPatterns("/controller/**");
    }
}
