package com.example.lzzll.activemq.constant;

import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.Properties;

/**
 * @Author lf
 * @Date 2020/10/21 15:15
 * @Description: 从配置文件中读取不同环境下配置的实体类对应的消息队列的名称
 */
public class DestinationConstant implements Serializable{

    /**
     * 配置文件配置的消息名称
     */
    public static Properties apiConstantConfig;

    /**
     * 从配置文件中获取数据
     */
    static{
        Properties properties = new Properties();
        try {
            // 如果只调用main方法，ConfigQueueName.queueName获取的数据为null
            ClassPathResource classPathResource = new ClassPathResource("config/" + ConfigQueueName.queueName);
            properties.load(new InputStreamReader(classPathResource.getInputStream(),"utf-8"));
            apiConstantConfig = properties;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 用户实体类的队列消息类型名称
     */
    public static final String USER_ENTITY_QUEUE_NAME = apiConstantConfig.getProperty("USER_ENTITY_QUEUE_NAME");


    /**
     * 用户实体类的主题消息类型名称
     */
    public static final String USER_ENTITY_TOPIC_NAME = apiConstantConfig.getProperty("USER_ENTITY_TOPIC_NAME");

    /**
     * 消息实体类的队列消息类型名称
     */
    public static final String NEWS_ENTITY_QUEUE_NAME = apiConstantConfig.getProperty("NEWS_ENTITY_QUEUE_NAME");


    /**
     * 消息实体类的主题消息类型名称
     */
    public static final String NEWS_ENTITY_TOPIC_NAME = apiConstantConfig.getProperty("NEWS_ENTITY_TOPIC_NAME");


}
