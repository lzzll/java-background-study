package com.example.lzzll.jpa.sys.jpa.user;

import com.example.lzzll.jpa.sys.dto.SysUserDto;
import com.example.lzzll.jpa.sys.entity.User;

/**
 * @Author lf
 * @Date 2021/1/12 15:00
 * @Description:
 */
// 使用原生的jdbc进行底层查询
public interface UserDao {


    User findByEntity(SysUserDto userDto);

}
