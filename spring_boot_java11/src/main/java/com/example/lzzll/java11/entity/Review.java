package com.example.lzzll.java11.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author lf
 * @Date 2023/6/2 10:32
 * @Description:  评分类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Review {
    private Double point;
    private String review;
}
