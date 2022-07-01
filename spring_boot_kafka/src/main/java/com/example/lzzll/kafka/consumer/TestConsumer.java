package com.example.lzzll.kafka.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.stereotype.Component;

/**
 * @Author lf
 * @Date 2020/9/10 15:56
 * @Description: kafka消费者
 */
@Component
public class TestConsumer {

    // 消费单个主题默认分区的消息
//    @KafkaListener(topics = "test_topic",groupId = "test")
    // 可监听单个主题多个分区的消息
    @KafkaListener(containerGroup="test",topicPartitions = {@TopicPartition(topic = "test_topic",partitions = {"0","1","2"})})
    public void listen1 (ConsumerRecord<?, ?> record) throws Exception {
        System.out.printf("topic = %s, offset = %d, value = %s \n", record.topic(), record.offset(), record.value());
    }

    // 消费者二
    @KafkaListener(containerGroup="test",topicPartitions = {@TopicPartition(topic = "test_topic",partitions = {"0","1","2"})})
    public void listen2 (ConsumerRecord<?, ?> record) throws Exception {
        System.out.printf("topic = %s, offset = %d, value = %s \n", record.topic(), record.offset(), record.value());
    }

}
