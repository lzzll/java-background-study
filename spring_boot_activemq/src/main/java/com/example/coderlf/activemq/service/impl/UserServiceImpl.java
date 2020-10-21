package com.example.coderlf.activemq.service.impl;

import com.example.coderlf.activemq.constant.DestinationBean;
import com.example.coderlf.activemq.service.PublishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Service;

/**
 * @Author lf
 * @Date 2020/10/21 15:07
 * @Description:
 */
@Service("userPublishService")
public class UserServiceImpl implements PublishService {

    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    @Override
    public void pushMsg(Object obj,int code) {
        jmsMessagingTemplate.convertAndSend(DestinationBean.getUserDestination(code),obj);
    }
}
