package com.example.lzzll.jpa.sys.jpa.user.impl;

import com.example.lzzll.jpa.common.util.StringUtil;
import com.example.lzzll.jpa.sys.dto.SysUserDto;
import com.example.lzzll.jpa.sys.entity.User;
import com.example.lzzll.jpa.sys.jpa.user.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author lf
 * @Date 2021/1/12 15:01
 * @Description:
 */
@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;


    @Override
    public User findByEntity(SysUserDto userDto) {
        StringBuilder sql = new StringBuilder(" SELECT * from sys_user ");
        boolean flag = false;
        List<Object> params = new ArrayList<>();
        if (userDto.getAccount() != null){
            flag = StringUtil.concatSql(sql, flag);
            sql.append(" account = ? ");
            params.add(userDto.getAccount());
        }
        if (userDto.getSecret() != null){
            flag = StringUtil.concatSql(sql, flag);
            sql.append(" secret = ? ");
            params.add(userDto.getSecret());
        }
        List<User> users = jdbcTemplate.query(sql.toString(), params.toArray(), new BeanPropertyRowMapper<>(User.class));
        return users == null || users.size() == 0? null:users.get(0);
    }
}
