package com.example.lzzll.shiro.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author lf
 * @Date 2022/7/11 15:15
 * @Description:
 */
@Data
public class Permission implements Serializable{

    /**
     * 主键id
     */
    private Long id;

    private String available;

    private String name;

    private Long parentId;

    private Long parentIds;

    private String permission;

    private Integer resourceType;

    private String url;


}
