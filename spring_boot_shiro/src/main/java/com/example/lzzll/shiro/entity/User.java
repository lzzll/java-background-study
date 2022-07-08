package com.example.lzzll.shiro.entity;

import lombok.Data;

/**
 * @Author lf
 * @Date 2022/7/6 15:53
 * @Description:
 */
@Data
public class User {

    /**
     * 主键id
     */
    private Long id;

    private String userName;

    private String password;

    private String role;

}
