package com.example.coderlf.activemq.entity;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * 用户测试类
 * @author Administrator
 *
 */
@Data
@ToString
public class User implements Serializable{

	private static final long serialVersionUID = 1L;

	private long id;
	
	private String username;
	
	private String password;
	
	private String sex;
	
	private int age;

}
