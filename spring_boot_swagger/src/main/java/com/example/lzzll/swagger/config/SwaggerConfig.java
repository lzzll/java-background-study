package com.example.lzzll.swagger.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

/**
 * @Author lf
 * @Date 2022/7/8 11:56
 * @Description: swagger配置类
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    // 新的组，修改了页面的标题
    @Bean
    public Docket docket1()
    {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(new ApiInfo(
                        "第二个接口文档",       				// swagger页面标题
                        "该文档描述了Hello Controller",   	// swagger页面描述
                        "1.1",          					// 标题右边的版本号
                        "",      							// 留空
                        new Contact("", "", ""),   			// 作者联系方式
                        "",									// license
                        "",									// license的url
                        new ArrayList()))
                .groupName("my1")  // 分组名称
                // 指定扫描接口的包，select和build成组出现
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.example.lzzll.swagger.controller"))
                .build();
    }
    @Bean
    public Docket docket()
    {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(new ApiInfo(
                        "接口文档",       // swagger页面标题
                        "该文档描述了Hello Controller",    // swagger页面描述
                        "1.1",          // 标题右边的版本号
                        "",      // 留空
                        new Contact("", "", ""),   // 作者联系方式
                        "",
                        "",
                        new ArrayList()))
                .groupName("my")  // 分组
                // 指定扫描接口的包，select和build是成组出现s的
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.example.lzzll.swagger.controller"))
                .build();
    }

}
