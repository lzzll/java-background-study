package com.example.lzzll.swagger.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;

/**
 * @Author lf
 * @Date 2023/6/14 16:54
 * @Description:
 */
@Slf4j
@RestController()
@RequestMapping("/test")
public class StreamReturnController {

    @GetMapping(path="/events", produces= MediaType.TEXT_EVENT_STREAM_VALUE)
    public SseEmitter handle() {
        try {
            SseEmitter emitter = new SseEmitter();
            emitter.send("Hello once");
            emitter.send("Hello again");
            emitter.complete();
            return emitter;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
