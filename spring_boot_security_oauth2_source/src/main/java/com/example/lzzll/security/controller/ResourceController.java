package com.example.lzzll.security.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author lf
 * @Date 2022/7/7 16:05
 * @Description:
 */
@RestController
@RequestMapping("resource")
public class ResourceController {

    @RequestMapping("findAll")
    public String findAllResources(){
        return "all resources";
    }


}
