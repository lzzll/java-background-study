package com.example.lzzll.javastudy.guava.EventBus;

import com.google.common.eventbus.Subscribe;

/**
 * @Author lf
 * @Date 2019/9/29 8:42
 * @Description:
 */
public class MultiListener {

    //@Subscribe保证有且只有一个输入参数,如果你需要订阅某种类型的消息,只需要在指定的方法上加上@Subscribe注解即可
    @Subscribe
    public void getMessage(Message message){
        System.out.println("第二个订阅者接收到消息："+message.getMessage());
    }

    /**
     * 一个订阅者可以订阅多个消息，guava会通过时间类型和形参来决定调用哪个方法
     * @param message
     */
    @Subscribe
    public void getMessage(String message){
        System.out.println("第二个订阅者的第二条消息："+message);
    }
}
