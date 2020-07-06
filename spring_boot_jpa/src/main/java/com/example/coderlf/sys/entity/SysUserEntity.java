package com.example.coderlf.sys.entity;

import com.sun.istack.NotNull;
import com.sun.org.glassfish.gmbal.Description;
import lombok.Data;

import javax.persistence.*;

/**
 * @Author lf
 * @Date 2020/7/3 13:22
 * @Description:
 */
@Entity
@Table(name = "sys_user")
@Data
public class SysUserEntity {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Column(name = "user_name")
    @NotNull
    private String userName;

    @Column(name = "user_age")
    private int userAge;

    @Column(name = "phone")
    private String phone;

    @Column(name = "email")
    private String email;

    @Column(name = "account")
    @Description("用户账户")
    private String account;

    @Column(name = "secret")
    @Description("用户密码，采用MD5进行加密")
    private String secret;

    @Column(name = "status")
    @Description("用户状态,1：正常，0：无效")
    private Integer status;

    @Column(name = "mark")
    private String mark;
}
