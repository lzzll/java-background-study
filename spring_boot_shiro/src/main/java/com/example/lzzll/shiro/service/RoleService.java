package com.example.lzzll.shiro.service;

import com.example.lzzll.shiro.dto.RolePermissionDto;
import com.example.lzzll.shiro.entity.Role;

import java.util.List;

/**
 * @Author lf
 * @Date 2022/7/11 15:34
 * @Description:
 */
public interface RoleService {

    /**
     * 通过角色id列表查询用户角色和角色对应的权限
     * @param roleIds
     * @return
     */
    RolePermissionDto findByRoleIds(List<Long> roleIds);
}
