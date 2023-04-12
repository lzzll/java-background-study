package com.example.lzzll.javastudy.gson.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @Author lf
 * @Date 2019/9/24 11:28
 * @Description:
 */
@Data
@AllArgsConstructor
@ToString
@NoArgsConstructor
public class Student {
    private String studentName;
    private Integer age;
    private School school;

}
