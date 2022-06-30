package com.code.lzzll.kafka.controller;

import com.code.lzzll.common.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author lf
 * @Date 2020/9/10 15:54
 * @Description: 生产者控制类
 */
@RestController
@RequestMapping("producer")
public class ProducerController {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @RequestMapping("send")
    public R send(String msg){
        try {
            kafkaTemplate.send("test_topic", msg);
            return R.ok();
        } catch (Exception e) {
            e.printStackTrace();
            return R.fail("发送失败");
        }
    }
}
