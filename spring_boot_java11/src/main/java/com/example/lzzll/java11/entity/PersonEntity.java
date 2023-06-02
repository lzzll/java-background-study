package com.example.lzzll.java11.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author lf
 * @Date 2023/6/1 15:11
 * @Description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonEntity {

    private String name;

    private Integer age;

    public PersonEntity(String name,Integer age){
        this.name = name;
        this.age = age;
    }

    private CalculatePoint calculatePoint = new CalculatePoint();
}
