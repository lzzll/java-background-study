package com.example.lzzll.javastudy.guava.EventBus;


import com.google.common.eventbus.EventBus;

import java.util.concurrent.*;

/**
 * @Author lf
 * @Date 2019/9/29 9:36
 * @Description:
 */
// 测试使用future异步发送信息
public class AsynSendMessage {

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        EventBus eventBus = new EventBus();
        EventListener eventListener = new EventListener();
        eventBus.register(eventListener); // 注册消息
        ExecutorService executorService = Executors.newCachedThreadPool();
        Future<Long> future = executorService.submit(new Callable<Long>() {
            @Override
            public Long call() throws Exception {
                return getTime(eventBus, new Message("测试！"));
            }
        });
        try {
            Long time = future.get();
            System.out.println("异步线程花费时间为："+time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Long end = System.currentTimeMillis();
        System.out.println("主线程总共花费时间为："+(end-start));
    }

//    class Task implements Callable{
//
//
//        @Override
//        public Long call() throws Exception {
//            return getTime();
//        }
//    }


    // 获取发送消息花费的时间
    public static Long getTime(EventBus eventBus,Message message){
        long start = System.currentTimeMillis();
        eventBus.post(message.getMessage());
        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();
        return 10000+(end-start);
    }

}
