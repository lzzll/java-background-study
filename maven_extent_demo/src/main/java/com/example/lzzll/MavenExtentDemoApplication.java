package com.example.lzzll;

import cn.canpoint.qcenter.framework.log.annotation.EnableGlobalHandler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
// 使用父目录中封装的日志框架
@EnableGlobalHandler(module = "testDemo")
public class MavenExtentDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(MavenExtentDemoApplication.class, args);
    }

}
