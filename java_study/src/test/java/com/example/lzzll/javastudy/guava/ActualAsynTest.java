package com.example.lzzll.javastudy.guava;

/**
 * @Author lf
 * @Date 2019/9/27 14:10
 * @Description:
 */

import com.google.common.collect.Lists;
import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;
import org.junit.Test;

import java.util.List;
import java.util.concurrent.*;

/**
 * 异步获取调用方法的时间，如果不采用异步调用的方法，那么主线程花费的时间将是子线程加上主线程花费的时间，
 * 现在直接在主线程执行的同时子线程也在执行，所以花费时间基本只有主线程自己的时间开销
 */
public class ActualAsynTest {

    // 实现异步调用的方式一
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        Callable<Long> callable = new Callable<Long>() {
            @Override
            public Long call() throws Exception {
                // 子线程调用方法
                Long time = new ActualAsynTest().generateList();
                TimeUnit.SECONDS.sleep(3);
                return time+3000;
            }
        };
        try {
            FutureTask<Long> future = new FutureTask<>(callable);
            new Thread(future).start();  // 开启子线程
            TimeUnit.SECONDS.sleep(5);
            Long time = future.get();
            long end = System.currentTimeMillis();
            System.out.println("主程序运行花费时间"+(end-start));
            System.out.println("调用方法花费时间"+time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }

    // 使用guava包下的Futures类完成异步调用，方式二
    @Test
    public void testFutures(){
        ExecutorService executorService = Executors.newCachedThreadPool();
        long start = System.currentTimeMillis();

        Task task = new Task();
        Future<Long> future = executorService.submit(task);
        executorService.shutdown();  // 需要关闭executorService对象
        try {
            TimeUnit.SECONDS.sleep(5);  // 主程序中的休眠和获取时间的顺序如果弄反了，那么时间就会不一样了
            Long time = future.get();
            long end = System.currentTimeMillis();
            System.out.println("主程序运行花费时间"+(end-start));
            System.out.println("调用方法花费时间"+time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    class Task implements Callable<Long> {
        @Override
        public Long call() throws Exception {
            Long time = generateList();
            Thread.sleep(3000);
            return time + 3000;
        }
    }

    // 方式三：使用Futures.submit()来获取异步线程结果
    @Test
    public void testMethodTwo(){
        long start = System.currentTimeMillis();
        ListeningExecutorService listeningExecutorService = MoreExecutors.listeningDecorator(Executors.newCachedThreadPool());
        Futures.addCallback(listeningExecutorService.submit(new Task()), new FutureCallback<Long>() {
            @Override
            public void onSuccess(Long time) {
                System.out.println("子线程运行花费的时间为："+time);
            }

            @Override
            public void onFailure(Throwable throwable) {
                System.out.println(throwable);
            }
        });
        try {
            TimeUnit.SECONDS.sleep(5);
            long end = System.currentTimeMillis();
            System.out.println("主线程花费时间为："+(end-start));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static Long generateList(){
        long start = System.currentTimeMillis();
        List<Object> list = Lists.newArrayList();
        for (int i = 0; i < 10000; i++) {
            list.add(i);
        }
//        list.stream().forEach((s)-> System.out.println(s));
        long end = System.currentTimeMillis();
        return end-start;
    }
}
