package com.example.lzzll.javastudy.guava.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @Author lf
 * @Date 2019/9/25 14:18
 * @Description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Student {
    private String name;
    private Integer age;
}
