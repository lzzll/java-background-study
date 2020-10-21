package com.example.coderlf.activemq.consumer;

import com.example.coderlf.activemq.constant.DestinationConstant;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 * @Author lf
 * @Date 2020/10/21 16:45
 * @Description:
 */
@Component
public class UserMsgConsumer {


    private final static String userQueue = "user_queue";
    private final static String userTopic = "user_topic";


    //接收queue消息
    @JmsListener(destination = userQueue,containerFactory = "queueListenerContainerFactory")
    public void handler(String message){
        System.out.println(message);
    }

    //接收topic消息
    @JmsListener(destination = userTopic,containerFactory = "topicListenerContainerFactory")
    public void handlerTopic(String msessage){
        System.out.println(msessage);
    }
}
