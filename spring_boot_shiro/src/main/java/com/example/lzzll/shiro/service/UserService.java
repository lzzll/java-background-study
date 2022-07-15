package com.example.lzzll.shiro.service;

import com.example.lzzll.shiro.dto.UserRoleDto;
import com.example.lzzll.shiro.entity.User; /**
 * @Author lf
 * @Date 2022/7/8 15:04
 * @Description:
 */
public interface UserService {

    /**
     * 写入用户
     * @param user
     * @return
     */
    Long insertUser(User user);

    /**
     * 根据用户名查询用户
     * @param username
     * @return
     */
    User findByUsername(String username);

    /**
     * 根据用户id查询用户和角色
     * @param userId
     * @return
     */
    UserRoleDto findByUserId(Long userId);
}
