package com.example.spring_boot_jpa.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * @Author lf
 * @Date 2020/7/3 13:22
 * @Description:
 */
@Entity
@Table(name = "user")
@Data
public class User {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "user_age")
    private int userAge;


}
