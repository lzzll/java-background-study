package com.example.lzzll.thymeleaf.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


/**
 * User
 * <p/>
 * Created in 2018.07.25
 * <p/>
 *
 * @author <a href="https://github.com/liaozihong" style="lzzll: #55a7e3;">Liaozihong</a>
 */
@Entity
@Data
public class User {
    @Id
    @GeneratedValue
    private long id;
    @Column(nullable = false, unique = true)
    private String userName;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private int age;
}
