package com.example.lzzll.shiro.mapper;

import com.example.lzzll.shiro.dto.RolePermissionDto;
import com.example.lzzll.shiro.entity.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author lf
 * @Date 2022/7/11 15:39
 * @Description:
 */
public interface RoleMapper {

    /**
     * 通过用户id查询用户角色和权限
     * @param roleIds
     * @return
     */
    RolePermissionDto findByRoleIds(@Param("roleIds") List<Long> roleIds);
}
