package com.example.lzzll.activemq.service.impl;

import com.example.lzzll.activemq.constant.DestinationBean;
import com.example.lzzll.activemq.service.PublishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Service;

/**
 * @Author lf
 * @Date 2020/10/21 16:31
 * @Description:
 */
@Service("newsServiceImpl")
public class NewsServiceImpl implements PublishService{

    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;


    @Override
    public void pushMsg(Object obj, int code) {
        jmsMessagingTemplate.convertAndSend(DestinationBean.getNewsDestination(code),obj);
    }
}
