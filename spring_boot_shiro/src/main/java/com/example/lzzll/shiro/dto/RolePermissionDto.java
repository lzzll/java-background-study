package com.example.lzzll.shiro.dto;

import com.example.lzzll.shiro.entity.Permission;
import com.example.lzzll.shiro.entity.Role;
import lombok.Data;

import java.util.List;

/**
 * @Author lf
 * @Date 2022/7/11 16:43
 * @Description:
 */
@Data
public class RolePermissionDto extends Role {

    private List<Permission> permissions;

}
