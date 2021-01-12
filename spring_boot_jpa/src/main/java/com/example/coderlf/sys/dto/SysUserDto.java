package com.example.coderlf.sys.dto;

import lombok.Data;

/**
 * @Author lf
 * @Date 2020/7/6 15:48
 * @Description: 用户参数实体类
 */
@Data
public class SysUserDto {

    /**
     * 用户名
     */
    private String userName;

    /**
     * 用户账户
     */
    private String account;


    /**
     * 用户密码
     */
    private String secret;


}
