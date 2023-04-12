package com.example.lzzll.javastudy.jdbctemplate.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 创建一个学生类,对应数据库中的学生表
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {

    // 声明成员变量
    private Integer id;
    private String name;
    private Float chinese;
    private Double math;
    private Float english;

}
