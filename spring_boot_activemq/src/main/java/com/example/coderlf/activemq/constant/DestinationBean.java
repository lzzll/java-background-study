package com.example.coderlf.activemq.constant;

import lombok.Data;
import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;

import javax.jms.Destination;
import java.io.Serializable;

/**
 * @Author lf
 * @Date 2020/10/21 16:02
 * @Description:
 */
@Data
public class DestinationBean implements Serializable{

    /**
     * 获取用户实体类对应的消息类型对象
     * @return Destination
     */
    public static Destination getUserDestination(int code){
        if (code == 1){
            return new ActiveMQQueue(DestinationConstant.USER_ENTITY_QUEUE_NAME);
        }else {
            return new ActiveMQTopic(DestinationConstant.USER_ENTITY_TOPIC_NAME);
        }
    }


    /**
     * 获取消息实体类对应的消息类型对象
     * @return Destination
     */
    public static Destination getNewsDestination(int code){
        if (code == 1){
            return new ActiveMQQueue(DestinationConstant.NEWS_ENTITY_QUEUE_NAME);
        }else {
            return new ActiveMQTopic(DestinationConstant.NEWS_ENTITY_TOPIC_NAME);
        }
    }


}
