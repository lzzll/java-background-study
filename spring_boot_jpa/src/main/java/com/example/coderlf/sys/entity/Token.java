package com.example.coderlf.sys.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * @Author lf
 * @Date 2021/1/11 16:39
 * @Description:
 */
@Data
@Entity
@Table(name = "api_token_infos")
public class Token {
    @Id  // 使用jpa时对于id必须要使用注解指定，否则会报无法创建jpa对应的repository的异常
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // 新建数据时需要指定id的增长策略，不指定时会和数据库约束发生冲突
    private Long tokenId;

    private Long userId;

    private byte[] userToken;

    @Column(name = "token_build_time")  // 数据库字段和试题类字段名不一致时需要指定
    private String buildTime;

}
