package com.example.lzzll.shiro.service.impl;

import com.example.lzzll.shiro.dto.RolePermissionDto;
import com.example.lzzll.shiro.entity.Role;
import com.example.lzzll.shiro.mapper.RoleMapper;
import com.example.lzzll.shiro.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author lf
 * @Date 2022/7/11 15:34
 * @Description:
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public RolePermissionDto findByRoleIds(List<Long> roleIds) {
        return roleMapper.findByRoleIds(roleIds);
    }
}
