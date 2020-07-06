package com.example.coderlf.sys.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * @Author lf
 * @Date 2020/7/6 10:18
 * @Description:
 */
@Entity
@Data
@Table(name = "api_token_infos",schema = "jwt")
public class TokenInfoEntity {

    @Id
    @GeneratedValue
    @Column(name = "token_id")
    private Long id;
    @Column(name = "user_id")
    private Long userId;
    @Column(name = "user_token")
    private byte[] userToken;
    @Column(name = "token_build_time")
    private String buildTime;
}
