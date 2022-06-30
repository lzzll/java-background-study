package com.code.lzzll.mongodb.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * @Author lf
 * @Date 2020/9/10 12:00
 * @Description:`
 */
@Getter
@Setter
@Document(collection = "studyTest")
public class StudyTest {

    /**
     * id
     */
    @Field("_id")
    private String id;

    /**
     * 用户信息
     */
    @Field("userInfo")
    private String userInfo;

    /**
     * 学习科目
     */
    @Field("study")
    private String study;
}
