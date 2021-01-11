package com.example.coderlf.sys.controller;

import com.example.coderlf.common.entity.ResponseEntity;
import com.example.coderlf.sys.entity.User;
import com.example.coderlf.sys.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author lf
 * @Date 2021/1/11 16:46
 * @Description:
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/getUserList")
    public ResponseEntity getUserList(){
        List<User> userList = userService.getUserList();
        return ResponseEntity.ok().put("data",userList);
    }



}
