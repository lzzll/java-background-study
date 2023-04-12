package com.example.lzzll.javastudy.gson.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

/**
 * @Author lf
 * @Date 2019/9/24 11:28
 * @Description:
 */
@Data
@AllArgsConstructor
@ToString
@NoArgsConstructor
public class School {

    private String name;
    private Date buildTime;
    private Integer size;
}
