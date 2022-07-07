package com.example.lzzll;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.lzzll.security.mapper")
public class SecurityOauth2SourceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SecurityOauth2SourceApplication.class, args);
	}

}
