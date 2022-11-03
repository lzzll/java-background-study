package com.example.lzzll.aspose.util.http;

import cn.hutool.crypto.SecureUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;

import java.io.IOException;
import java.util.ArrayList;

/**
 * @Author lf
 * @Date 2022/11/2 17:08
 * @Description: 使用http调用外部接口的工具类
 */
@Slf4j
public class HttpClientUtil {

    private final static String testUrl = "http://124.70.50.43:9006/eprint/queryBoxBasicInfo";

    private final static String appId = "123456789";

    private final static String appKey = "654321";

    private final static String LIAN_KE_ENCODE_STR = "6d334e65170f0c1cc3ed239881ca6863b6b72cd0dcda47b7";


    /**
     * 通过表单的形式发送请求。表单请求定时参数的方式public String callback(@RequestParam("recordId") Long recordId){}
     */
    public static void askWithForm(){
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
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 通过json的形式发送请求。json请求在定义参数的时候需要使用  public R getPdf(@RequestBody JSONObject jsonObject) {} 这种声明形式
     */
    public static void askWithJson(){
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
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
//        askWithForm();
        askWithJson();
    }



}
