package com.example.coderlf.sys.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @Author lf
 * @Date 2021/1/11 16:39
 * @Description:
 */
@Data
@Entity
@Table(name = "api_token_infos")
public class Token {
    @Id
    // 使用jpa时对于id必须要使用注解指定，否则会报无法创建jpa对应的repository的异常
    private Long tokenId;

    private Long userId;

    private byte[] userToken;

    private String buildTime;

}
