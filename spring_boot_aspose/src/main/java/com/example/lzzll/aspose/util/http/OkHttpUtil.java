package com.example.lzzll.aspose.util.http;

import cn.hutool.crypto.SecureUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.apache.commons.lang.exception.ExceptionUtils;

/**
 * @Author lf
 * @Date 2022/11/2 17:09
 * @Description: 使用okHttp调用第三方接口的客户端
 */
@Slf4j
public class OkHttpUtil {


    private final static String testUrl = "http://124.70.50.43:9006/eprint/queryBoxBasicInfo";

    private final static String appId = "123456789";

    private final static String appKey = "654321";

    private final static String LIAN_KE_ENCODE_STR = "6d334e65170f0c1cc3ed239881ca6863b6b72cd0dcda47b7";

    /**
     * 通过表单的形式发送请求。json请求在定义参数的时候需要使用  public R getPdf(@RequestBody JSONObject jsonObject) {} 这种声明形式
     */
    public static void askWithForm(){
        try {
            // 调用Ai的网关接口，获取盒子的唯一识别码`
            long timestamp = System.currentTimeMillis();
            String sign = SecureUtil.md5().digestHex(appId + appKey + timestamp);
            OkHttpClient httpClient = new OkHttpClient();
            FormBody.Builder formBodyBuilder = new FormBody.Builder();
            formBodyBuilder.add("key",LIAN_KE_ENCODE_STR);
            FormBody formBody = formBodyBuilder.build();
            Request request = new Request
                    .Builder()
                    .method("POST",formBody)
                    .url(testUrl)
                    .addHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8")
                    .addHeader("appId", appId)
                    .addHeader("sign", sign)
                    .addHeader("timestamp", String.valueOf(timestamp))
                    .build();
            Response response = httpClient.newCall(request).execute();
            if (!response.isSuccessful()) {
                log.error("接口链接失败");
            }
            String result = response.body().string();
            log.info("打印结果:{}", result);
            JSONObject json = JSONUtil.parseObj(result);
            if (json.containsKey("code") && "200".equals(json.get("code").toString()) && json.containsKey("data")) {
                log.info(json.getStr("data"));
            } else {
                log.error("获取接口数据失败");
            }

        } catch (Exception e) {
            log.error("综合获取盒子打印能力失败:"+e.getMessage(), ExceptionUtils.getFullStackTrace(e));
        }
    }

    /**
     * 通过json的方式发送请求。json请求在定义参数的时候需要使用  public R getPdf(@RequestBody JSONObject jsonObject) {} 这种声明形式
     */
    public static void askWithJson(){
        try {
            // 调用Ai的网关接口，获取盒子的唯一识别码`
            long timestamp = System.currentTimeMillis();
            String sign = SecureUtil.md5().digestHex(appId + appKey + timestamp);
            OkHttpClient httpClient = new OkHttpClient();
            MediaType mediaType = MediaType.parse("application/json");
            JSONObject json = new JSONObject();
            json.put("requestNo", "444");
            json.put("page", "2");
            json.put("errorcode", "200");
            json.put("fileUrl", "这是一个测试的文件地址222");
            RequestBody requestBody = RequestBody.create(mediaType,JSONUtil.toJsonStr(json));
            Request request = new Request
                    .Builder()
                    .post(requestBody)
                    .url("http://39.105.162.131:8080/smart-book/composite/print/getPdf")
                    .addHeader("appId", appId)
                    .addHeader("sign", sign)
                    .addHeader("timestamp", String.valueOf(timestamp))
                    .build();
            Response response = httpClient.newCall(request).execute();
            if (!response.isSuccessful()) {
                log.error("接口链接失败");
            }
            String result = response.body().string();
            log.info("打印结果:{}", result);
            JSONObject resultJson = JSONUtil.parseObj(result);
            if (resultJson.containsKey("code") && "200".equals(resultJson.get("code").toString()) && resultJson.containsKey("data")) {
                log.info(resultJson.getStr("data"));
            } else {
                log.error("获取接口数据失败");
            }

        } catch (Exception e) {
            log.error("综合获取盒子打印能力失败:"+e.getMessage(), ExceptionUtils.getFullStackTrace(e));
        }

    }


    public static void main(String[] args) {
//        askWithForm();
        askWithJson();
    }



}
