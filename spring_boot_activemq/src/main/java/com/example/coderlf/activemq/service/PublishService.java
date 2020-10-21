package com.example.coderlf.activemq.service;

/**
 * @Author lf
 * @Date 2020/10/21 15:05
 * @Description:
 */
public interface PublishService {


    /**
     * 发送消息的通用接口
     * @param obj
     * @param code
     */
    public void pushMsg(Object obj ,int code);

}
