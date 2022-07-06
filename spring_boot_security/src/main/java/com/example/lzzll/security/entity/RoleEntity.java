package com.example.lzzll.security.entity;

import lombok.Data;

/**
 * @Author lf
 * @Date 2022/7/6 17:16
 * @Description:
 */
@Data
public class RoleEntity {

    /**
     * 主键id
     */
    private Long id;

    /**
     * 角色名字
     */
    private String roleName;

    /**
     * 角色可访问的权限
     */
    private String rolePath;

}
