package com.example.lzzll.shiro.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author lf
 * @Date 2022/7/11 15:25
 * @Description:
 */
@Data
public class RolePermission implements Serializable{

    /**
     * 主键id
     */
    private Long id;

    /**
     * 角色id
     */
    private Long roleId;

    /**
     * 权限id
     */
    private Long permissionId;
}
