package com.code.example.lzzll.mongodb.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * @Author lf
 * @Date 2020/9/10 12:00
 * @Description:`
 */
@Getter
@Setter
@Document(collection = "studyTest")  //指定要对应的文档名(表名）
public class StudyTest {

    /*** 自定义mongo主键 加此注解可自定义主键类型以及自定义自增规则
     *  若不加 插入数据数会默认生成 ObjectId 类型的_id 字段
     *  org.springframework.data.annotation.Id 包下
     *  mongo库主键字段还是为_id 。不必细究(本文实体类中为id）
     */
    @Id
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
