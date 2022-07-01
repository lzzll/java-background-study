package com.example.lzzll.socketserver.controller;

import com.example.lzzll.socketserver.service.WebSocketService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author lf
 * @Date 2020/10/19 13:40
 * @Description: webSocket的后端控制器
 */
@Slf4j
@RestController
@RequestMapping("webSocket")
public class WebSocketController {


    @Autowired
    private WebSocketService webSocketService;

    @GetMapping("/sendMessage")
    public String sendMessage(String message){
        webSocketService.groupSending(message);
        return message;
    }






}
