package com.example.coderlf.activemq.consumer;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 * @Author lf
 * @Date 2020/10/21 17:03
 * @Description:
 */
@Component
public class NewsMsgConsumer {

    private final static String newsQueue = "news_queue";
    private final static String newsTopic = "news_topic";


    //接收queue消息
    @JmsListener(destination = newsQueue,containerFactory = "queueListenerContainerFactory")
    public void handler(String message){
        System.out.println(message);
    }

    //接收topic消息
    @JmsListener(destination = newsTopic,containerFactory = "topicListenerContainerFactory")
    public void handlerTopic(String msessage){
        System.out.println(msessage);
    }


}
