package com.example.lzzll.shiro.controller;

import com.example.lzzll.shiro.common.R;
import com.example.lzzll.shiro.common.exception.CpException;
import com.example.lzzll.shiro.contant.UserConstant;
import com.example.lzzll.shiro.entity.User;
import com.example.lzzll.shiro.service.UserService;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @Author lf
 * @Date 2022/7/8 15:01
 * @Description: 用户相关操作
 */
@RestController
@RequestMapping("user")
public class UserController {

    /**
     * shiro中密码加密用的盐，写死为一个固定值
     */
    public final static String PASSWORD_SALT = "31a09983aa16d814430068669fa95b00";

    @Autowired
    private UserService userService;

    /**
     * 用户注册。填写post请求时，postMan中需要设置的content_type默认应为"application/json"
     * @param user
     * @return
     */
    @RequestMapping(value = "register",method = RequestMethod.POST)
    public R register(@RequestBody User user){
        try {
            if (StringUtils.isEmpty(user.getUserName()) || StringUtils.isEmpty(user.getPassword())){
                return R.fail("参数有误");
            }
            String encodePassword = new SimpleHash("MD5", user.getPassword(), PASSWORD_SALT, 2).toHex();
            user.setPassword(encodePassword);
            user.setSalt(PASSWORD_SALT);
            user.setState(UserConstant.StateEnum.NOT_USE.code);
            userService.insertUser(user);
            R result = R.ok();
            // 获取新增数据主键
            result.setData(user.getId());
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return R.fail("注册失败");
        }
    }


}
