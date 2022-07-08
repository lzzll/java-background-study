//package com.example.lzzll.shiro.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import springfox.documentation.builders.RequestHandlerSelectors;
//import springfox.documentation.service.ApiInfo;
//import springfox.documentation.service.Contact;
//import springfox.documentation.spi.DocumentationType;
//import springfox.documentation.spring.web.plugins.Docket;
//import springfox.documentation.swagger2.annotations.EnableSwagger2;
//
//import java.util.ArrayList;
//
///**
// * @Author lf
// * @Date 2022/7/8 14:54
// * @Description: swagger的配置
// */
//@Configuration
//@EnableSwagger2
//public class SwaggerConfig {
//
//    @Bean
//    public Docket docket()
//    {
//        return new Docket(DocumentationType.SWAGGER_2)
//                .apiInfo(new ApiInfo(
//                        "shiro样例小结",       // swagger页面标题
//                        "这是shiro样例整合的demo",    // swagger页面描述
//                        "1.1",          // 标题右边的版本号
//                        "",      // 留空
//                        new Contact("lzzll", "", ""),   // 作者联系方式
//                        "",
//                        "",
//                        new ArrayList()))
//                .groupName("shiro_demo")  // 分组
//                // 指定扫描接口的包，select和build是成组出现s的
//                .select()
//                .apis(RequestHandlerSelectors.basePackage("com.example.lzzll.shiro.controller"))
//                .build();
//    }
//
//}
