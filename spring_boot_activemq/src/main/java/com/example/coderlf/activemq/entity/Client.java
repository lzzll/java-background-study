package com.example.coderlf.activemq.entity;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * 客户实体类
 *
 */
@Data
@ToString
public class Client implements Serializable {
	private static final long serialVersionUID = 1L;

	private long id;
	
	private String name;
	
	private String address;
	
	private String mobile;

	
}
