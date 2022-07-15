package com.example.lzzll.shiro.service.impl;

import com.example.lzzll.shiro.dto.UserRoleDto;
import com.example.lzzll.shiro.entity.User;
import com.example.lzzll.shiro.mapper.UserMapper;
import com.example.lzzll.shiro.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
    @Transactional(rollbackFor = Exception.class)
    public Long insertUser(User user) {
        return userMapper.insertUser(user);
    }

    @Override
    public User findByUsername(String username) {
        return userMapper.findByUsername(username);
    }

    @Override
    public UserRoleDto findByUserId(Long userId) {
        return userMapper.findByUserId(userId);
    }


}
