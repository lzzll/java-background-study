package com.example.lzzll.shiro.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @Author lf
 * @Date 2022/7/11 15:15
 * @Description:
 */
@Data
public class Role implements Serializable{

    /**
     * 主键id
     */
    private Long id;

    private String roleName;

    private String description;

    private Integer avaliable;

    private List<Permission> permissions;

}
