package com.example.coderlf.activemq.entity;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
public class News implements Serializable {

	private static final long serialVersionUID = 1L;

	private long id;
	
	private String title;
	
	private String content;
	
	private String url;
	
	private String author;

	
}
