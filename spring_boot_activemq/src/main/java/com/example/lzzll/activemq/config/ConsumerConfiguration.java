package com.example.lzzll.activemq.config;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;

import javax.jms.ConnectionFactory;

/**
 * @Author lf
 * @Date 2020/10/21 16:48
 * @Description: Queue为点对点模式，即有一个消息，才能有一个消费，多个消费者不会重复对应一个消息
 */
@Configuration
public class ConsumerConfiguration {

    @Value("${spring.activemq.broker-url}")
    private String host;

    @Bean
    public ConnectionFactory getActiveMqConnection(){
        return new ActiveMQConnectionFactory(host);
    }

    @Bean(name="queueListenerContainerFactory")
    public JmsListenerContainerFactory queueListenerContailerFactory(){
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        factory.setConnectionFactory(getActiveMqConnection());
        factory.setPubSubDomain(false);
        return factory;
    }
    @Bean(name="topicListenerContainerFactory")
    public JmsListenerContainerFactory topicListenerContainerFactory(){
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        factory.setConnectionFactory(getActiveMqConnection());
        factory.setPubSubDomain(true);
        return factory;
    }
}
