package com.example.lzzll.aspose.util.http;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Future;
import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.concurrent.FutureCallback;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClients;
import java.util.concurrent.TimeUnit;

import java.util.concurrent.Future;

/**
 * @Author lf
 * @Date 2023/6/1 13:28
 * @Description: 第三方jar包org.apache.httpcomponents httpasyncclient 发送异步请求
 */
public class AysncHttpClientUtil {

    /**
     * 异步请求，只执行一个请求
     * This example demonstrates a basic asynchronous HTTP request / response
     * exchange. Response content is buffered in memory for simplicity.
     */
//    public static void main(final String[] args) throws Exception {
//        CloseableHttpAsyncClient httpclient = HttpAsyncClients.createDefault();// 默认的配置
//        try {
//            httpclient.start();
//            HttpGet request = new HttpGet("http://www.apache.org/");
//            Future<HttpResponse> future = httpclient.execute(request, null);
//            HttpResponse response = future.get();// 获取结果
//            System.out.println("Response: " + response.getStatusLine());
//            System.out.println("Shutting down");
//        } finally {
//            httpclient.close();
//        }
//        System.out.println("Done");
//    }

    /**
     * 异步执行多个请求
     * @param args
     * @throws Exception
     */
    public static void main(final String[] args) throws Exception {
        RequestConfig requestConfig = RequestConfig.custom()
                .setSocketTimeout(3000).setConnectTimeout(3000).build();
        CloseableHttpAsyncClient httpclient = HttpAsyncClients.custom()
                .setDefaultRequestConfig(requestConfig).build();
        try {
            httpclient.start();
            final HttpGet[] requests = new HttpGet[] {
                    new HttpGet("http://www.apache.org/"),
                    new HttpGet("https://www.verisign.com/"),
                    new HttpGet("http://www.google.com/"),
                    new HttpGet("http://www.baidu.com/") };
            final CountDownLatch latch = new CountDownLatch(requests.length);
            for (final HttpGet request : requests) {
                httpclient.execute(request, new FutureCallback<HttpResponse>() {
                    //无论完成还是失败都调用countDown()
                    @Override
                    public void completed(final HttpResponse response) {
                        latch.countDown();
                        System.out.println(">>>>>>>>>>"+request.getRequestLine() + "->"
                                + response.getStatusLine());
                    }
                    @Override
                    public void failed(final Exception ex) {
                        latch.countDown();
                        System.out.println(">>>>>>>>>>"+request.getRequestLine() + "->" + ex);
                    }
                    @Override
                    public void cancelled() {
                        latch.countDown();
                        System.out.println(">>>>>>>>>>"+request.getRequestLine()
                                + " cancelled");
                    }
                });
            }
            // CountDownLatch是一个同步辅助类，在完成一组正在其他线程中执行的操作之前，它允许一个或多个线程一直等待。用给定的计数 初始化 CountDownLatch。由于调用了 countDown() 方法，所以在当前计数到达零之前，await 方法会一直受阻塞。
            // 之后，会释放所有等待的线程，await 的所有后续调用都将立即返回。这种现象只出现一次——计数无法被重置
            latch.await();

            // 等待固定时间不管线程是否执行完都直接释放线程
//            latch.await(100L.TimeUnit.SECONDS);
            System.out.println("Shutting down");
        } finally {
            httpclient.close();
        }
        System.out.println("Done");
    }

}
