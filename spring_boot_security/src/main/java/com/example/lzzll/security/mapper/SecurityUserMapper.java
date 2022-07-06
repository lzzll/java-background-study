package com.example.lzzll.security.mapper;

import com.example.lzzll.security.entity.SecurityUser;
import org.apache.ibatis.annotations.Param;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * @Author lf
 * @Date 2022/7/6 15:56
 * @Description:
 */
public interface SecurityUserMapper {

    /**
     * 通过用户名称查询用户详情
     * @param userName
     * @return
     */
    SecurityUser queryByUserName(@Param("userName") String userName);
}
