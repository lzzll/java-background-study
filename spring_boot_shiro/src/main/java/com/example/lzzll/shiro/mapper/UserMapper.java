package com.example.lzzll.shiro.mapper;

import com.example.lzzll.shiro.dto.UserRoleDto;
import com.example.lzzll.shiro.entity.User;

import java.util.List;

/**
 * @Author lf
 * @Date 2022/7/8 15:13
 * @Description:
 */
public interface UserMapper{

    Long insertUser(User user);

    User findByUsername(String username);

    UserRoleDto findByUserId(Long userId);
}
