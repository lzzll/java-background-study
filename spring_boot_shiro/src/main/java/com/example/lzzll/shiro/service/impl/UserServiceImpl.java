package com.example.lzzll.shiro.service.impl;

import com.example.lzzll.shiro.entity.User;
import com.example.lzzll.shiro.mapper.UserMapper;
import com.example.lzzll.shiro.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author lf
 * @Date 2022/7/8 15:04
 * @Description:
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public Long insertUser(User user) {
        return userMapper.insertUser(user);
    }


}
