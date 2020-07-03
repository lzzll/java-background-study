package com.example.spring_boot_jpa.controller;

import com.example.spring_boot_jpa.entity.User;
import com.example.spring_boot_jpa.jpa.UserJpa;
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
    private UserJpa userJpa;

    @RequestMapping("/listUser")
    public List<User> listUser(){
        List<User> users = userJpa.findAll();
        return users;
    }

    @RequestMapping("/findUserById")
    public User listUser(@RequestParam Long id){
        Optional<User> user = userJpa.findById(id);
        return user.get();
    }


}
