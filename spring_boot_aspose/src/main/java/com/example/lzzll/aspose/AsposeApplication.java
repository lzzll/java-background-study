package com.example.lzzll.aspose;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan("com.example.lzzll.aspose.common.filter")  //扫描过滤器
public class AsposeApplication {

	public static void main(String[] args) {
		SpringApplication.run(AsposeApplication.class, args);
	}

}
