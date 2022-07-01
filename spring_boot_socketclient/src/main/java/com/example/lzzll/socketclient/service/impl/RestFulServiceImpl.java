package com.example.lzzll.socketclient.service.impl;

import com.example.lzzll.socketclient.service.RestFulService;
import org.springframework.stereotype.Service;

/**
 * Created by hui.yunfei@qq.com on 2019/5/31
 */
@Service
public class RestFulServiceImpl implements RestFulService {
    @Override
    public void insert(String userId) {
        System.out.println("serviceimpl get userId:"+userId);
    }

    @Override
    public String getMessage() {
        return "hello";
    }
}
