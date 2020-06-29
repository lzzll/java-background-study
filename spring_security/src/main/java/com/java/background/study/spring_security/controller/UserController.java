package com.java.background.study.spring_security.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
// 测试单http配置MyWebSecurityConfig
public class UserController {

    @RequestMapping("hello")
    public String helloSpring(){
        return "hello spring security";
    }

    @RequestMapping("/admin/hello")
    public String admin(){
        return "admin";
    }

    @RequestMapping("/user/hello")
    public String user(){
        return "user";
    }

    @RequestMapping("/db/hello")
    public String dba(){
        return "dba";
    }
}
