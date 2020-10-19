package com.example.coderlf.demo.common;

import io.goeasy.GoEasy;
import lombok.extern.slf4j.Slf4j;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft_6455;
import org.java_websocket.handshake.ServerHandshake;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

import java.net.URI;

/**
 * @Author lf
 * @Date 2020/10/19 13:22
 * @Description: webSocket的配置类
 */
@Component
@Slf4j
public class WebSocketConfig {

    /**
     * ServerEndpointExporter 作用
     *
     * 这个Bean会自动注册使用@ServerEndpoint注解声明的websocket endpoint
     *
     * @return
     */
    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }


    public static void main(String[] args) {
        GoEasy goEasy = new GoEasy("http://rest-hangzhou.goeasy.io","BC-945d777056f94cbfa6c5d098c54c90d4");
        goEasy.publish("test_application", "Hello, GoEasy!");
    }

}
