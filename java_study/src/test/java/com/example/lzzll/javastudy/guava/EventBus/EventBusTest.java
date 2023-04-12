package com.example.lzzll.javastudy.guava.EventBus;

import com.google.common.eventbus.EventBus;

import java.util.concurrent.TimeUnit;

/**
 * @Author lf
 * @Date 2019/9/29 8:37
 * @Description:
 */
public class EventBusTest {

    private static EventBus eventBus = null;

    static {
        // 使用同步线程注册
        eventBus = new EventBus("eventbus");
    }

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        // 使用异步线程注册，貌似没有异步的效果
//        AsyncEventBus asyncEventBus = new AsyncEventBus(Executors.newCachedThreadPool());
//        asyncEventBus.register(new EventListener());   // 注册第一个订阅者
//        asyncEventBus.register(new MultiListener());   // 注册第二个订阅者
//        getmethodTime(asyncEventBus,new Message("测试异步发送消息！"));

        // 使用同步线程注册
        EventListener eventListener = new EventListener();
        MultiListener multiListener = new MultiListener();
        eventBus.register(eventListener);  // 注册订阅
        eventBus.register(multiListener);
        getmethodTime(eventBus,new Message("测试同步发送消息！"));
        eventBus.unregister(eventListener);  // 取消订阅
        eventBus.unregister(multiListener);
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();
        System.out.println("主线程运行花费的时间为："+(end-start));


//        eventBus.post("今天天气不错！");  // 匹配第一个订阅者的第二个订阅方法
//        eventBus.post(new Message("心情也不错！"));  // 匹配第一个订阅者的第一个订阅方法

    }

    // 获取方法的执行时间
    private static Long getmethodTime(EventBus eventBus,Message message){
        long start = System.currentTimeMillis();
        eventBus.post(message);
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();
//        System.out.println("处理消息花费的时间为："+(end-start));
        return (end-start)+3000;
    }

}
