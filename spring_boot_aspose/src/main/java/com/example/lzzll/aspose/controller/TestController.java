package com.example.lzzll.aspose.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author lf
 * @Date 2022/11/9 17:01
 * @Description:
 */
@RestController()
@RequestMapping("test")
@Slf4j
public class TestController {

    @RequestMapping("testFilter")
    public String testFilter(@RequestParam String name){
        log.info("方法执行完成:"+name);
        return name;
    }

}
