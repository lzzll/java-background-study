package com.example.lzzll.aspose.util.http;

import cn.hutool.crypto.SecureUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;

import java.io.IOException;
import java.util.*;

/**
 * @Author lf
 * @Date 2022/11/2 17:08
 * @Description: 使用http调用外部接口的工具类，需要引入依赖包 commons-httpclient
 */
@Slf4j
public class HttpClientUtil {

    private final static String testUrl = "http://39.105.162.131:8080/eprint-web/eprint/queryBoxBasicInfo";

    private final static String appId = "123456789";

    private final static String appKey = "654321";

    private final static String LIAN_KE_ENCODE_STR = "6d334e65170f0c1cc3ed239881ca6863b6b72cd0dcda47b7";


    /**
     * 通过表单的形式发送请求。表单请求定时参数的方式public String callback(@RequestParam("recordId") Long recordId){}
     */
    public static JSONObject postWithForm(){
        try {
            long timestamp = System.currentTimeMillis();
            String sign = SecureUtil.md5().digestHex(appId + appKey + timestamp);
            PostMethod postMethod = new PostMethod(testUrl);
            postMethod.setRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
            postMethod.setRequestHeader("appId", appId);
            postMethod.setRequestHeader("sign", sign);
            postMethod.setRequestHeader("timestamp", String.valueOf(timestamp));
            ArrayList<NameValuePair> paramList = new ArrayList<>();
            // 公司信息
            paramList.add(new NameValuePair("key", LIAN_KE_ENCODE_STR));
            // 封装参数
            postMethod.setRequestBody(paramList.toArray(new NameValuePair[]{}));
            String responseCharSet = postMethod.getResponseCharSet();
            System.out.println(responseCharSet);
            HttpClient httpClient = new HttpClient();
            // 执行POST方法
            int response = httpClient.executeMethod(postMethod);
            if (response != 200) {
                log.error("接口链接失败");
            }
            String result = new String(postMethod.getResponseBody(),"UTF-8");
//            log.info("打印结果:{}", result);
            JSONObject json = JSONUtil.parseObj(result);
            if (json.containsKey("code") && "200".equals(json.get("code").toString()) && json.containsKey("data")) {
                log.info(json.getStr("data"));
            } else {
                log.error("获取接口数据失败");
            }
            return json;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new JSONObject();
    }

    /**
     * 通过json的形式发送请求。json请求在定义参数的时候需要使用  public R getPdf(@RequestBody JSONObject jsonObject) {} 这种声明形式
     */
    public static JSONObject postWithJson(){
        try {
            long timestamp = System.currentTimeMillis();
            String sign = SecureUtil.md5().digestHex(appId + appKey + timestamp);
            PostMethod postMethod = new PostMethod("http://39.105.162.131:8080/smart-book/composite/print/getPdf");
            postMethod.setRequestHeader("appId", appId);
            postMethod.setRequestHeader("sign", sign);
            postMethod.setRequestHeader("timestamp", String.valueOf(timestamp));
            // 封装json参数
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("requestNo", "444");
            jsonObject.put("page", "2");
            jsonObject.put("errorcode", "200");
            jsonObject.put("fileUrl", "这是一个测试的文件地址");
            RequestEntity requestEntity = new StringRequestEntity(jsonObject.toString(), "application/json", "utf-8");
            postMethod.setRequestEntity(requestEntity);
            HttpClient httpClient = new HttpClient();
            // 执行POST方法
            int response = httpClient.executeMethod(postMethod);
            if (response != 200) {
                log.error("接口链接失败");
            }
            String result = new String(postMethod.getResponseBody(),"UTF-8");
//            log.info("打印结果:{}", result);
            JSONObject json = JSONUtil.parseObj(result);
            if (json.containsKey("code") && "200".equals(json.get("code").toString()) && json.containsKey("data")) {
                log.info(json.getStr("data"));
            } else {
                log.error("获取接口数据失败");
            }
            return json;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new JSONObject();
    }

    /**
     * 发送get请求
     */
    public static JSONObject getWithParam(String url, Map<String,Object> param, List<Header> headers){
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
            GetMethod getMethod = new GetMethod(url);
            if (headers != null && headers.size() != 0){
                for (Header header : headers) {
                    getMethod.addRequestHeader(header);
                }
            }
            HttpClient httpClient = new HttpClient();
            // 执行POST方法
            int response = httpClient.executeMethod(getMethod);
            // 请求取消
//            getMethod.abort();

            if (response != 200) {
                log.error("接口链接失败");
            }
            String result = new String(getMethod.getResponseBody(),"UTF-8");
//            log.info("打印结果:{}", result);
            JSONObject json = JSONUtil.parseObj(result);
            if (json.containsKey("code") && "200".equals(json.get("code").toString()) && json.containsKey("data")) {
                log.info(json.getStr("data"));
            } else {
                log.error("获取接口数据失败");
            }
            return json;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new JSONObject();
    }


    public static void main(String[] args) {
        postWithForm();
//        postWithJson();

        // get有参请求测试
//        HashMap<String, Object> map = new HashMap<>();
//        map.put("pageNum",1);
//        map.put("pageSize",10);
//        Header header = new Header();
//        header.setName("CANPOINTTOKEN");
//        header.setValue("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VyR3VpZCI6IlIyWlBNMjE0VUVnNVJHTm5aMEZ1UlRkUGJsRTVVVDA5IiwiZXhwIjoxNjY3NTMxNTkwfQ.TKmlfhye7kLk-ZdbuEc3TMR4lym2qPkR3fvNMw-jLYw");
//        JSONObject json = getWithParam("http://39.105.162.131:8080/itembank-factory/tikuPreBatch/myPreBatchs", map, Collections.singletonList(header));
//        System.out.println(json);

        // get无参请求测试
//        HashMap<String, Object> map = new HashMap<>();
//        Header header = new Header();
//        header.setName("CANPOINTTOKEN");
//        header.setValue("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VyR3VpZCI6IlIyWlBNMjE0VUVnNVJHTm5aMEZ1UlRkUGJsRTVVVDA5IiwiZXhwIjoxNjY3NTMxNTkwfQ.TKmlfhye7kLk-ZdbuEc3TMR4lym2qPkR3fvNMw-jLYw");
//        JSONObject json = getWithParam("http://39.105.162.131:8080/itembank-factory/tikuPreBatch/queryPublishDepts", map, Collections.singletonList(header));
//        System.out.println(json);

    }



}
