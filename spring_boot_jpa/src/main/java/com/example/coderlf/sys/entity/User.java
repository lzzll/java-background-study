package com.example.coderlf.sys.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @Author lf
 * @Date 2021/1/11 16:37
 * @Description:
 */
@Data
@Entity
@NoArgsConstructor
// 不指定tableName的时候，默认表名和试题类名一致
@Table(name = "sys_user")
public class User {

    @Id
    @GeneratedValue
    private Long id;

    private String userName;

    private Integer userAge;


    private String secret;

}
