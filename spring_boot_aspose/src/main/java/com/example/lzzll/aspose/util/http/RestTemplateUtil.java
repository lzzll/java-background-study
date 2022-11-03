package com.example.lzzll.aspose.util.http;

import cn.hutool.crypto.SecureUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

/**
 * @Author lf
 * @Date 2022/11/3 8:58
 * @Description:
 */
@Slf4j
public class RestTemplateUtil {

    private final static String testUrl = "http://124.70.50.43:9006/eprint/queryBoxBasicInfo";

    private final static String appId = "123456789";

    private final static String appKey = "654321";

    private final static String LIAN_KE_ENCODE_STR = "6d334e65170f0c1cc3ed239881ca6863b6b72cd0dcda47b7";

//    @Autowired
//    private RestTemplate restTemplate;

    /**
     * 通过表单的形式发送请求。json请求在定义参数的时候需要使用  public R getPdf(@RequestBody JSONObject jsonObject) {} 这种声明形式
     */
    public static void askWithForm(){
        try {
            long timestamp = System.currentTimeMillis();
            String sign = SecureUtil.md5().digestHex(appId + appKey + timestamp);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
            headers.set("appId", appId);
            headers.set("sign", sign);
            headers.set("timestamp", String.valueOf(timestamp));
            MultiValueMap<String, String> map= new LinkedMultiValueMap<>();
            map.add("key", LIAN_KE_ENCODE_STR);
            HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);
            ResponseEntity<String> response = new RestTemplate().postForEntity(testUrl, request , String.class);
            String body = response.getBody();
            JSONObject json = JSONUtil.parseObj(body);
            if (json != null && json.getInt("code") == 200) {
                log.info(json.getStr("data"));
            } else {
                log.error("获取接口数据失败");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 通过json的方式发送请求。json请求在定义参数的时候需要使用  public R getPdf(@RequestBody JSONObject jsonObject) {} 这种声明形式
     */
    public static void askWithJson(){
        try {
            long timestamp = System.currentTimeMillis();
            String sign = SecureUtil.md5().digestHex(appId + appKey + timestamp);
            MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
            headers.add("Content-Type", "application/json;charset=utf-8");
            headers.add("appId", appId);
            headers.add("sign", sign);
            headers.add("timestamp", String.valueOf(timestamp));
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("requestNo", "444");
            jsonObject.put("page", "2");
            jsonObject.put("errorcode", "200");
            jsonObject.put("fileUrl", "这是一个测试的文件地址111");
            HttpEntity<String> entity = new HttpEntity<>(jsonObject.toString(), headers);
            ResponseEntity<JSONObject> responseEntity = new RestTemplate().postForEntity("http://39.105.162.131:8080/smart-book/composite/print/getPdf", entity, JSONObject.class);
            JSONObject json = responseEntity.getBody();
            if (json != null && json.getInt("code") == 200) {
                log.info(json.getStr("data"));
            } else {
                log.error("获取接口数据失败");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
//        askWithForm();
        askWithJson();
    }


}
