package com.example.coderlf.sys.controller;

import com.example.coderlf.sys.entity.SysUserEntity;
import com.example.coderlf.sys.jpa.SysUserJpa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

/**
 * @Author lf
 * @Date 2020/7/3 13:31
 * @Description:
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private SysUserJpa sysUserJpa;

    @RequestMapping("/listUser")
    public List<SysUserEntity> listUser(){
        List<SysUserEntity> sysUsers = sysUserJpa.findAll();
        return sysUsers;
    }

    @RequestMapping("/findUserById")
    public SysUserEntity listUser(@RequestParam Long id){
        Optional<SysUserEntity> user = sysUserJpa.findById(id);
        return user.get();
    }


}
