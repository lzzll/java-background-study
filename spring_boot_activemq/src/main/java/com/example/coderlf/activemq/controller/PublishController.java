package com.example.coderlf.activemq.controller;

import com.example.coderlf.activemq.service.PublishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author lf
 * @Date 2020/10/21 15:04
 * @Description:
 */
@RestController
@RequestMapping("/publish")
public class PublishController {

    /**
     * 同一个接口有多个实现类，不能使用Autowired注解，需要使用Resource注解加以区分
     */
    @Resource(name = "userPublishService")
    private PublishService userPublishService;

    @Autowired()
    @Qualifier("newsServiceImpl")
    private PublishService newsServiceImpl;

    /**
     * 用户试题类发送消息
     * @param message
     * @param msgCode
     * @return
     */
    @RequestMapping("/user")
    public String userPublish(@RequestParam String message, @RequestParam int msgCode){

        userPublishService.pushMsg(message,msgCode);
        return "success";

    }


    /**
     * 消息实体类发送消息
     * @param message
     * @param msgCode
     * @return
     */
    @RequestMapping("/news")
    public String newsPublish(@RequestParam String message, @RequestParam int msgCode){
        newsServiceImpl.pushMsg(message,msgCode);
        return "success";

    }


}
