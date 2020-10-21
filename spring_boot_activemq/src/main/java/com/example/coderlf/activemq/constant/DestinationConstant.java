package com.example.coderlf.activemq.constant;

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

    //网关接口及apiId配置文件
    public static Properties apiConstantConfig;
    //动态从配置文件里获取ApiId
    static{
        Properties properties = new Properties();
        try {
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
