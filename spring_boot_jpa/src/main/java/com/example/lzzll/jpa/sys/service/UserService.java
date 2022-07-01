package com.example.lzzll.jpa.sys.service;

import com.example.lzzll.jpa.sys.dto.SysUserDto;
import com.example.lzzll.jpa.sys.entity.User;

import java.util.List;

/**
 * @Author lf
 * @Date 2021/1/11 16:41
 * @Description:
 */
public interface UserService {

    /**
     * Gets user list.
     *
     * @return the user list
     */
    List<User> getUserList();

    /**
     * Find user by id user.
     *
     * @param id the id
     * @return the user
     */
    User findUserById(long id);

    /**
     * Save.
     *
     * @param user the user
     */
    void save(User user);

    /**
     * Edit.
     *
     * @param user the user
     */
    void edit(User user);

    /**
     * Delete.
     *
     * @param id the id
     */
    void delete(long id);

    /**
     * 自己封装方法查询数据
     * @param sysUserDto
     * @return
     */
    User getUserByEntity(SysUserDto sysUserDto);
}
