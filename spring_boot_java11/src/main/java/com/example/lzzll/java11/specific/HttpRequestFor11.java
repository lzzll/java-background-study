package com.example.lzzll.java11.specific;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @Author lf
 * @Date 2023/5/31 15:51
 * @Description: java11中修改了发送http客户端的方式
 */
public class HttpRequestFor11 {

    public static void main(String[] args) {
        HttpRequest request = HttpRequest
                .newBuilder()
                .uri(URI.create("https://www.baidu.com/"))
                .GET()
                .build();
//        try {
//            // 发送同步请求,获取结果之前会阻塞当前线程
//            HttpResponse<String> result = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
//            String body = result.body();
//            System.out.println(body);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }

        try {
            // 异步请求，不影响当前线程执行
            CompletableFuture<String> asyncResult = HttpClient.newHttpClient().sendAsync(request, HttpResponse.BodyHandlers.ofString()).thenApply(HttpResponse::body);
            System.out.println("异步请求执行之后");
            String asyncBody = asyncResult.get();
            System.out.println(asyncBody);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }


}
