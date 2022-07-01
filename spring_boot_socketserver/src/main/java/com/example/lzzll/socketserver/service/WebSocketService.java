package com.example.lzzll.socketserver.service;

/**
 * @Author lf
 * @Date 2020/10/19 13:43
 * @Description:
 */
public interface WebSocketService {

    /**
     * 消息群发
     * @param message
     */
    void groupSending(String message);


    /**
     * 指定发送
     * @param name
     * @param message
     */
    void appointSending(String name,String message);

}
