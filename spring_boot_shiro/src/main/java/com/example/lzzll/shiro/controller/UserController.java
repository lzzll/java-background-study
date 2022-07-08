package com.example.lzzll.shiro.controller;

import com.example.lzzll.shiro.common.R;
import com.example.lzzll.shiro.entity.User;
import com.example.lzzll.shiro.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author lf
 * @Date 2022/7/8 15:01
 * @Description: 用户相关操作
 */
@RestController
@RequestMapping("user")
public class UserController {

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
            String encodePassword = "";
            user.setPassword(encodePassword);
            Long userId = userService.insertUser(user);
            R result = R.ok();
            result.setData(userId);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return R.fail("注册失败");
        }
    }


}
