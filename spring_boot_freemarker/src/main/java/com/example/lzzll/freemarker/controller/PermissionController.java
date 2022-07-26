package com.example.lzzll.freemarker.controller;


import com.example.lzzll.freemarker.entity.Permission;
import com.example.lzzll.freemarker.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author lzzll
 * @since 2022-07-26
 */
@Controller
@RequestMapping("/permission")
public class PermissionController {

    @Autowired
    private PermissionService permissionService;

//    @GetMapping("/test")
//    public String getPermission(Model model){
//        Permission permission = new Permission();
//        permission.setId(10);
//        permission.setName("测试权限");
//        permission.setUrl("http://baidu.com.cn");
//        model.addAttribute("id",permission.getId());
//        model.addAttribute("name",permission.getName());
//        model.addAttribute("url",permission.getUrl());
//        return "permission";
//    }

    @GetMapping("/test")
    public String getPermission(Model model){
        Permission permission = permissionService.getById(1);
        model.addAttribute("id",permission.getId());
        model.addAttribute("name",permission.getName());
        model.addAttribute("url",permission.getUrl());
        return "permission";
    }


}
