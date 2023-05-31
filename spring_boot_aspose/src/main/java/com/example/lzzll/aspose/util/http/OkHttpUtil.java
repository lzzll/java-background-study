package com.example.lzzll.aspose.util.http;

import cn.hutool.crypto.SecureUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.apache.commons.lang.exception.ExceptionUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author lf
 * @Date 2022/11/2 17:09
 * @Description: 使用okHttp调用第三方接口的客户端，需要额外引入jar包 com.squareup.okhttp3
 */
@Slf4j
public class OkHttpUtil {


    private final static String testUrl = "http://39.105.162.131:8080/eprint-web/eprint/queryBoxBasicInfo";

    private final static String appId = "123456789";

    private final static String appKey = "654321";

    private final static String LIAN_KE_ENCODE_STR = "6d334e65170f0c1cc3ed239881ca6863b6b72cd0dcda47b7";

    /**
     * 通过表单的形式发送请求。json请求在定义参数的时候需要使用  public R getPdf(@RequestBody JSONObject jsonObject) {} 这种声明形式
     */
    public static void postWithForm(){
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
    public static void postWithJson(){
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

    /**
     * 发送get请求
     */
    public static JSONObject getWithParam(String url, Map<String,Object> param, Headers headers){
        try {
            if (param != null && param.size() != 0){
                url += "?";
                for (Map.Entry<String, Object> entry : param.entrySet()) {
                    String key = entry.getKey();
                    Object value = entry.getValue();
                    if (value != null){
                        url += key + "=" + value + "&";
                    }
                }
                url = url.substring(0,url.length()-1);
            }
            OkHttpClient httpClient = new OkHttpClient();
            // 设置请求时的各种不同参数
//            httpClient.newBuilder().addInterceptor().connectTimeout()

            Request request = new Request
                    .Builder()
                    .get()
                    .url(url)
                    .headers(headers)
                    .build();
            final Call call = httpClient.newCall(request);
            Response response = call.execute();
            // 取消请求
//            call.cancel();
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
            return resultJson;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new JSONObject();
    }


    public static void main(String[] args) {
//        postWithForm();

//        postWithJson();

        // get有参请求测试
//        HashMap<String, Object> map = new HashMap<>();
//        map.put("pageNum",1);
//        map.put("pageSize",1);
//        Map<String, String> headers = new HashMap<>();
//        headers.put("CANPOINTTOKEN","eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VyR3VpZCI6IlIyWlBNMjE0VUVnNVJHTm5aMEZ1UlRkUGJsRTVVVDA5IiwiZXhwIjoxNjY3NTMxNTkwfQ.TKmlfhye7kLk-ZdbuEc3TMR4lym2qPkR3fvNMw-jLYw");
//        Headers header = Headers.of(headers);
//        JSONObject json = getWithParam("http://39.105.162.131:8080/itembank-factory/tikuPreBatch/myPreBatchs", map,header);
//        System.out.println(json);

        // get无参请求测试
        HashMap<String, Object> map = new HashMap<>();
        Map<String, String> headers = new HashMap<>();
        headers.put("CANPOINTTOKEN","eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VyR3VpZCI6IlIyWlBNMjE0VUVnNVJHTm5aMEZ1UlRkUGJsRTVVVDA5IiwiZXhwIjoxNjY3NTMxNTkwfQ.TKmlfhye7kLk-ZdbuEc3TMR4lym2qPkR3fvNMw-jLYw");
        Headers header = Headers.of(headers);
        JSONObject json = getWithParam("http://39.105.162.131:8080/itembank-factory/tikuPreBatch/queryPublishDepts", map, header);
        System.out.println(json);

    }

}
