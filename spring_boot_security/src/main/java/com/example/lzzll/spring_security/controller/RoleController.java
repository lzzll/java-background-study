package com.example.lzzll.spring_security.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
// 测试多http配置类MultiHttpSecurityConfig
public class RoleController {

    @RequestMapping("/admin/aaa")
    public String admin(){
        return "admin";
    }

    @RequestMapping("/user/aaa")
    public String user(){
        return "user";
    }

    @RequestMapping("/db/aaa")
    public String dba(){
        return "dba";
    }

}
