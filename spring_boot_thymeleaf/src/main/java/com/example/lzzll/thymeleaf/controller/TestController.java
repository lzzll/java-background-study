package com.example.lzzll.thymeleaf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author lf
 * @Date 2022/7/26 17:46
 * @Description:
 */
@Controller
@RequestMapping("test")
public class TestController {

    @RequestMapping
    public String test(Model model){
        model.addAttribute("name","this first thymeleaf demo");
        return "test";
    }
}
