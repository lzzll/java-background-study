package com.example.lzzll.jpa.sys.controller;

import com.example.lzzll.jpa.common.entity.ResponseEntity;
import com.example.lzzll.jpa.sys.dto.SysUserDto;
import com.example.lzzll.jpa.sys.entity.User;
import com.example.lzzll.jpa.sys.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    /**
     * 通过jpa自己封装的方法查询数据
     * @return
     */
    @RequestMapping("/getUserList")
    public ResponseEntity getUserList(){
        List<User> userList = userService.getUserList();
        return ResponseEntity.ok().put("data",userList);
    }

    /**
     * 运用jpa，使用自己写的底层方法查询数据
     * @param account
     * @param userName
     * @return
     */
    @RequestMapping("/getUserByEntity")
    public ResponseEntity getUserByEntity(@RequestParam(required = false) String account,@RequestParam(required = false) String userName){
        SysUserDto sysUserDto = new SysUserDto();
        sysUserDto.setAccount(account);
        sysUserDto.setUserName(userName);
        User user = userService.getUserByEntity(sysUserDto);
        return ResponseEntity.ok().put("data",user);
    }


}
