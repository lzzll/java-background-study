package com.example.lzzll.activemq.consumer;

import com.example.lzzll.activemq.constant.DestinationConstant;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 * @Author lf
 * @Date 2020/10/21 17:03
 * @Description:
 */
@Component
public class NewsMsgConsumer {

    /**
     * 从配置文件中获取数据，转了一道之后不能算常量
     */
    private final static String newsQueue1 = DestinationConstant.NEWS_ENTITY_QUEUE_NAME;
    private final static String newsTopic1 = DestinationConstant.NEWS_ENTITY_TOPIC_NAME;

    /**
     * 通过静态代码块赋值的也不能算常量
      */
    private static String newsQueue2;
    private static String newsTopic2;

    static {
        newsQueue2 = DestinationConstant.NEWS_ENTITY_QUEUE_NAME;
        newsTopic2 = DestinationConstant.NEWS_ENTITY_TOPIC_NAME;
    }

    /**
     * 直接定义的才算常量
     */
    private final static String newsQueue = "news_queue";
    private final static String newsTopic = "news_topic";

    /**
     * news下的队列消息：消费者一
     */
    @JmsListener(destination = newsQueue,containerFactory = "queueListenerContainerFactory")
    public void handlerQueue1() {
    }

    /**
     * news下的队列消息：消费者一
     * @param message
     */
    @JmsListener(destination = newsQueue,containerFactory = "queueListenerContainerFactory")
    public void handlerQueue1(String message){
        System.out.println(String.format("news下的队列消息,消费者一：%s",message));
    }

    /**
     * news下的队列消息：消费者二
     * @param message
     */
    @JmsListener(destination = newsTopic,containerFactory = "queueListenerContainerFactory")
    public void handlerQueue2(String message){
        System.out.println(String.format("news下的队列消息,消费者二：%s",message));
    }


    //接收topic消息
    @JmsListener(destination = newsTopic,containerFactory = "topicListenerContainerFactory")
    public void handlerTopic1(String msessage){
        System.out.println(String.format("news下的主题消息,消费者一：%s",msessage));
    }

    //接收topic消息
    @JmsListener(destination = newsTopic,containerFactory = "topicListenerContainerFactory")
    public void handlerTopic2(String msessage){
        System.out.println(String.format("news下的主题消息,消费者二：%s",msessage));
    }

    public static void main(String[] args) {
        System.out.println(newsQueue1);
        System.out.println(newsQueue2);
        System.out.println(newsQueue);
    }

}
