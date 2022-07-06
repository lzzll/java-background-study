package com.example.lzzll.security.mapper;

import com.example.lzzll.security.entity.UserRoleEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author lf
 * @Date 2022/7/6 17:13
 * @Description:
 */
public interface UserRoleMapper {

    List<UserRoleEntity> findByUserId(@Param("userGuid")Long userGuid);
}
