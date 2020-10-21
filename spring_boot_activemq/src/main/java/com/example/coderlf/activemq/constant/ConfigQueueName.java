package com.example.coderlf.activemq.constant;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @Author lf
 * @Date 2020/10/21 15:25
 * @Description:
 */
@Component
public class ConfigQueueName {

    public static String queueName;

    @Value("${config-queue.name}")
    private void setQueueName(String name){
        this.queueName = name;
    }
}
