package com.example.lzzll.aspose.common.config;

import com.example.lzzll.aspose.common.interceptor.IpUrlLimitInterceptor;
import com.example.lzzll.aspose.common.interceptor.SignAuthenInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Author lf
 * @Date 2022/11/10 14:04
 * @Description: 过滤器注册，使用的过滤器需要在此配置中进行添加才能生效
 */
//@Configuration
public class WebConfig implements WebMvcConfigurer {

    /**
     * 签名认证拦截器
     * @return
     */
    @Bean
    SignAuthenInterceptor getAuthenInterceptor(){
        return  new SignAuthenInterceptor();
    };

    /**
     * ip访问次数拦截器
     * @return
     */
    @Bean
    IpUrlLimitInterceptor getIpInterceptor(){
        return  new IpUrlLimitInterceptor();
    };

    /**
     * 配置拦截器。哪个拦截器配置在前就先执行哪个拦截器，postHandle方法和afterCompletion方法是后配置的先执行，并且执行顺序是交替执行
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(getIpInterceptor()).addPathPatterns("/**");
        registry.addInterceptor(getAuthenInterceptor()).addPathPatterns("/**");
    }

}
