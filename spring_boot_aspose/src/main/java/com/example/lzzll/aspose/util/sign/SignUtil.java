package com.example.lzzll.aspose.util.sign;

import cn.hutool.crypto.SecureUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.apache.commons.codec.digest.DigestUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @Author lf
 * @Date 2022/11/4 14:48
 * @Description: 接口签名工具类
 */
@Slf4j
public class SignUtil {

    private final static String appId = "123456789";

    private final static String appKey = "987654321";

    // 单例的请求客户端
    private static OkHttpClient httpClient;

    static {
        httpClient = new OkHttpClient();
        httpClient.newBuilder().connectTimeout(2,TimeUnit.SECONDS);
    }

    /**
     * 添加签名，两种加密方式结果一样
     */
    public static Headers addSign(){
        long timestamp = System.currentTimeMillis();
        // 使用md5进行加密方式一
//        String sign = SecureUtil.md5().digestHex(appId + appKey + timestamp);
        // 加密方式二
        String sign = DigestUtils.md5Hex((appId + appKey + timestamp).getBytes());
        Map<String, String> headers = new HashMap<>();
        headers.put("appId",appId);
        headers.put("sign",sign);
        headers.put("timestamp",String.valueOf(timestamp));
        Headers header = Headers.of(headers);
        return header;
    }



    public static void main(String[] args) {
        long start = 0;
        try {
            OkHttpClient httpClient = new OkHttpClient();
//            httpClient.newBuilder().connectTimeout(10, TimeUnit.SECONDS);
            Request request = new Request
                    .Builder()
                    .get()
                    .url("http://localhost:8080/sign/authen/getWithNoParam")
                    .headers(addSign())
                    .build();
            final Call call = httpClient.newCall(request);
            start = System.currentTimeMillis();
            Response response = call.execute();
            if (!response.isSuccessful()) {
                log.error("接口链接失败");
            }
            String result = response.body().string();
            log.info("打印结果:{}", result);
            JSONObject resultJson = JSONUtil.parseObj(result);
            System.out.println(resultJson);
            long end = System.currentTimeMillis();
            System.out.println("正常执行花费时间:{"+(end-start)+"}");
        } catch (IOException e) {
            e.printStackTrace();
            long end = System.currentTimeMillis();
            System.out.println("超时花费时间:{"+(end-start)+"}");
        }
    }

//    public static void main(String[] args) {
//        // 单例http客户端验证
//        for (int i = 0; i < 10; i++) {
//            long start = 0;
//            try {
//                Request request = new Request
//                        .Builder()
//                        .get()
//                        .url("http://localhost:8080/sign/authen/getWithNoParam")
//                        .headers(addSign())
//                        .build();
//                System.out.println("httpClient地址值："+httpClient.toString());
//                final Call call = httpClient.newCall(request);
//                Response response = call.execute();
//                if (!response.isSuccessful()) {
//                    log.error("接口链接失败");
//                }
//                String result = response.body().string();
//                log.info("打印结果:{}", result);
//                JSONObject resultJson = JSONUtil.parseObj(result);
//                System.out.println(resultJson);
//                long end = System.currentTimeMillis();
//                System.out.println("正常执行花费时间:{"+(end-start)+"}");
//            } catch (IOException e) {
//                e.printStackTrace();
//                long end = System.currentTimeMillis();
//                System.out.println("超时花费时间:{"+(end-start)+"}");
//            }
//        }
//    }


}
