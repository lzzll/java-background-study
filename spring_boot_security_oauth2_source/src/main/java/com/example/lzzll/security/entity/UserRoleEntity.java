package com.example.lzzll.security.entity;

import lombok.Data;

import java.util.Date;

/**
 * @Author lf
 * @Date 2022/7/6 17:13
 * @Description:
 */
@Data
public class UserRoleEntity extends RoleEntity{

    /**
     * 主键id
     */
    private Long id;

    /**
     * 用户id
     */
    private Long userGuid;

    /**
     * 角色id
     */
    private Long roleId;


    private Date createTime;


    private Date updateTime;

    /**
     * 是否可用 0 不可用 1 可用
     */
    private Integer isUse;


}
