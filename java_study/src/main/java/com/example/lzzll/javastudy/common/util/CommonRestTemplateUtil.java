//package com.example.lzzll.javastudy.common.util;
//
//import com.alibaba.fastjson.JSONObject;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.http.client.HttpClient;
//import org.apache.http.impl.client.DefaultHttpClient;
//import org.springframework.http.*;
//import org.springframework.http.client.SimpleClientHttpRequestFactory;
//import org.springframework.util.DigestUtils;
//import org.springframework.util.LinkedMultiValueMap;
//import org.springframework.util.MultiValueMap;
//import org.springframework.web.client.RestTemplate;
//import org.springframework.web.multipart.support.StandardMultipartHttpServletRequest;
//
//import java.util.Enumeration;
//import java.util.HashMap;
//import java.util.Map;
//
///**
// * @Author: li
// * @Date: 2020/3/16 09:14
// * @Description:外部接口访问类
// */
//@Slf4j
//public class CommonRestTemplateUtil extends RestTemplate{
//
//    private RestTemplate restTemplate;
//    private IApiGateway apiGateway;
//
//    public CommonRestTemplateUtil(IApiGateway apiGateway) {
//        this.apiGateway = apiGateway;
//
//        SimpleClientHttpRequestFactory clientHttpRequestFactory
//                = new SimpleClientHttpRequestFactory();
//        clientHttpRequestFactory.setConnectTimeout(160 * 1000);
//        clientHttpRequestFactory.setReadTimeout(160 * 1000);
//        this.restTemplate = new RestTemplate(clientHttpRequestFactory);
////        restTemplate.set
//    }
//
//    /**
//     * 云平台api访问(前端)
//     *
//     * @param apiUrl api地址
//     * @param apiId  apiID
//     * @return
//     */
//    public Map apiGateway(String apiUrl, String apiId, String httpMethod, String paramJson) {
//        long start = System.currentTimeMillis();
//        //打印网关访问参数
//        HashMap<String, Object> erroParam = new HashMap<>();
//        erroParam.put("apiUrl",ApiConstant.HTTP + apiUrl);
//        erroParam.put("apiId",apiId);
//        log.info("apiGatewayParam:" + JSONObject.toJSONString(erroParam));
//        //请求头
//        HttpHeaders header = getHeader(apiId,true);
//        //获取访问结果
//        try {
//            Map exchange = exchange(apiUrl, httpMethod, new HttpEntity<String>(header), paramJson);
//            log.info("网关接口URL:{},请求耗时:{}",ApiConstant.HTTP+apiUrl,System.currentTimeMillis() - start + "ms");
//            return exchange;
//        } catch (Exception e) {
//            log.error("调用网关失败:{},网关接口参数明细:{}" ,e.getMessage(),JSONObject.toJSONString(erroParam));
//            throw new CPException("调用网关失败:" + e.getMessage());
//        }
//    }
//
//    /**
//     * 云平台api访问(后端)
//     *
//     * @param apiUrl api地址
//     * @param apiId  apiID
//     * @param param  参数
//     * @return
//     */
//    public Map apiGateway2(String apiUrl, String apiId, Map<String, Object> param, HttpMethod method) {
//        long start = System.currentTimeMillis();
//        //打印网关访问参数
//        HashMap<String, Object> erroParam = new HashMap<>();
//        erroParam.put("apiUrl",ApiConstant.HTTP + apiUrl);
//        erroParam.put("apiId",apiId);
//        erroParam.put("param",param);
//        log.info("apiGatewayParam:" + JSONObject.toJSONString(erroParam));
//        //请求头
//        HttpHeaders header = getHeader(apiId,false);
//        //获取访问结果
//        try {
//            Map exchange = exchange(apiUrl, method, new HttpEntity(header), param);
//            log.info("网关接口URL:{},请求耗时:{}",ApiConstant.HTTP + apiUrl,System.currentTimeMillis() - start + "ms");
//            if (exchange !=null && "200".equals(exchange.get("code").toString())) {
//                return exchange;
//            }
//            throw new CPException(exchange==null?"网络异常":exchange.get("msg").toString());
//        } catch (Exception e) {
//            log.error("调用网关失败:{},网关接口参数明细:{}" ,e.getMessage(),JSONObject.toJSONString(erroParam));
//            throw new CPException("调用网关失败:" + e.getMessage());
//        }
//    }
//
//    /**
//     * 云平台api访问(后端Body方式)
//     *
//     * @param apiUrl api地址
//     * @param apiId  apiID
//     * @param param  参数
//     * @return
//     */
//    public Map apiGateway2Body(String apiUrl, String apiId, Map<String, Object> param, HttpMethod method) {
//        long start = System.currentTimeMillis();
//        //打印网关访问参数
//        HashMap<String, Object> erroParam = new HashMap<>();
//        erroParam.put("apiUrl",ApiConstant.HTTP + apiUrl);
//        erroParam.put("apiId",apiId);
//        erroParam.put("param",param);
//        log.info("apiGatewayParam:" + JSONObject.toJSONString(erroParam));
//        //请求头
//        HttpHeaders header = getHeader(apiId,false);
//        //获取访问结果
//        try {
//            Map exchange = exchangeBody(apiUrl, method, new HttpEntity(header), param);
//            log.info("网关接口URL:{},请求耗时:{}",ApiConstant.HTTP+apiUrl,System.currentTimeMillis() - start + "ms");
//            if (exchange !=null && "200".equals(exchange.get("code").toString())) {
//                return exchange;
//            }
//            throw new CPException(exchange==null?"网络异常":exchange.get("msg").toString());
//        } catch (Exception e) {
//            log.error("调用网关失败:{},网关接口参数明细:{}" ,e.getMessage(),JSONObject.toJSONString(erroParam));
//            throw new CPException("调用网关失败:" + e.getMessage());
//        }
//    }
//
//    /**
//     * 云平台api访问(后端Body方式)
//     *
//     * @param apiUrl api地址
//     * @param apiId  apiID
//     * @param param  参数
//     * @return
//     */
//    public Map apiGateway2BodyWithHeader(String apiUrl, String apiId, Map<String, Object> param, HttpMethod method,HttpHeaders header) {
//        long start = System.currentTimeMillis();
//        //打印网关访问参数
//        HashMap<String, Object> erroParam = new HashMap<>();
//        erroParam.put("apiUrl", ApiConstant.HTTP + apiUrl);
//        erroParam.put("apiId",apiId);
//        erroParam.put("param",param);
//        log.info("apiGatewayParam:" + JSONObject.toJSONString(erroParam));
//        //请求头
//        HttpHeaders apiHeaders = getHeader(apiId,false);
//        header.addAll(apiHeaders);
//        //获取访问结果
//        try {
//            Map exchange = exchangeBody(apiUrl, method, new HttpEntity(header), param);
//            log.info("网关接口URL:{},请求耗时:{}",ApiConstant.HTTP+apiUrl,System.currentTimeMillis() - start + "ms");
//            if (exchange !=null && "200".equals(exchange.get("code").toString())) {
//                return exchange;
//            }
//            throw new CPException(exchange==null?"网络异常":exchange.get("msg").toString());
//        } catch (Exception e) {
//            log.error("调用网关失败:{},网关接口参数明细:{}" ,e.getMessage(),JSONObject.toJSONString(erroParam));
//            throw new CPException("调用网关失败:" + e.getMessage());
//        }
//    }
//
//    /**
//     * 云平台api访问(自定义header)
//     *
//     * @param apiUrl api地址
//     * @param apiId  apiID
//     * @param param  参数
//     * @return
//     */
//    public Map apiGatewayWithHeader(String apiUrl, String apiId, Map<String, Object> param, HttpMethod method,HttpHeaders header) {
//        long start = System.currentTimeMillis();
//        //打印网关访问参数
//        HashMap<String, Object> erroParam = new HashMap<>();
//        erroParam.put("apiUrl",ApiConstant.HTTP + apiUrl);
//        erroParam.put("apiId",apiId);
//        erroParam.put("param",param);
//        log.info("apiGatewayParam:" + JSONObject.toJSONString(erroParam));
//        //请求头
//        HttpHeaders apiHeaders = getHeader(apiId,false);
//        header.addAll(apiHeaders);
//        //获取访问结果
//        try {
//            Map exchange = exchange(apiUrl, method, new HttpEntity(header), param);
//            log.info("网关接口URL:{},请求耗时:{}",ApiConstant.HTTP+apiUrl,System.currentTimeMillis() - start + "ms");
//            if (exchange !=null && "200".equals(exchange.get("code").toString())) {
//                return exchange;
//            }
//            throw new CPException(exchange==null?"网络异常":exchange.get("msg").toString());
//        } catch (Exception e) {
//            e.printStackTrace();
//            log.error("调用网关失败:{},网关接口参数明细:{}" ,e.getMessage(),JSONObject.toJSONString(erroParam));
//            throw new CPException("调用网关失败:" + e.getMessage());
//        }
//    }
//
//
//    /**
//     * 云平台api访问(后端)修改密码等专用
//     *
//     * @param apiUrl api地址
//     * @param apiId  apiID
//     * @param param  参数
//     * @return
//     */
//    public Map apiGateway3(String apiUrl, String apiId, Map<String, Object> param, HttpMethod method) {
//        long start = System.currentTimeMillis();
//        //打印网关访问参数
//        HashMap<String, Object> erroParam = new HashMap<>();
//        erroParam.put("apiUrl",ApiConstant.HTTP + apiUrl);
//        erroParam.put("apiId",apiId);
//        erroParam.put("param",param);
//        log.info("apiGatewayParam:" + JSONObject.toJSONString(erroParam));
//        //请求头
//        HttpHeaders header = getHeader(apiId,false);
//        header.add("callerReferer",ApiConstant.SYSTEM_DOMAIN);
//        //获取访问结果
//        try {
//            Map exchange = exchange(apiUrl, method, new HttpEntity(header), param);
//            log.info("网关接口URL:{},请求耗时:{}",ApiConstant.HTTP+apiUrl,System.currentTimeMillis() - start + "ms");
//            if (exchange !=null && "200".equals(exchange.get("code").toString())) {
//                return exchange;
//            }
//            throw new CPException(exchange==null?"网络异常":exchange.get("msg").toString());
//        } catch (Exception e) {
//            log.error("调用网关失败:{},网关接口参数明细:{}" ,e.getMessage(),JSONObject.toJSONString(erroParam));
//            throw new CPException("调用网关失败:" + e.getMessage());
//        }
//    }
//
//    /**
//     * 无参请求
//     *
//     * @param url    访问地址
//     * @param method 请求方法
//     * @param header 请求头
//     * @return
//     */
//    public Map exchange(String url, HttpMethod method, HttpHeaders header) {
//        ResponseEntity responseEntity = restTemplate.exchange(ApiConstant.HTTP + url, method, new HttpEntity<String>(header), Map.class);
//        return (Map) responseEntity.getBody();
//    }
//
//    /**
//     * 有参请求(前端)
//     *
//     * @param url        访问地址
//     * @param httpMethod 请求方法类型
//     * @param httpEntity http实体（包含请求头header和body传参（post请求））
//     * @param paramJson  请求参数
//     * @return
//     */
//    public Map exchange(String url, String httpMethod, HttpEntity httpEntity, String paramJson) {
//        //请求类型
//        HttpMethod method;
//        if ("get".equalsIgnoreCase(httpMethod.trim())) {
//            method = HttpMethod.GET;
//        } else if ("post".equalsIgnoreCase(httpMethod.trim())) {
//            method = HttpMethod.POST;
//        } else {
//            throw new CPException("不支持的请求方式");
//        }
//        ResponseEntity responseEntity;
//        //处理url和参数
//        url = ApiConstant.HTTP + url;
//        Map<String, Object> param;
//        if (!StringUtil.isEmpty(paramJson)) {
//            param = JSONObject.parseObject(paramJson).toJavaObject(Map.class);
//            url = url + "?";
//            for (Map.Entry entry : param.entrySet()) {
//                if (entry.getValue() != null && !entry.getValue().toString().equals("null")) {
//                    url = url + entry.getKey() + "={" + entry.getKey() + "}&";
////                    param.put(entry.getKey().toString(), entry.getValue() == null ? null : URIDecoder.decodeURIComponent(entry.getValue().toString()));
//                }
//            }
//            url = url.substring(0, url.length() - 1);
//            responseEntity = restTemplate.exchange(url, method, httpEntity, Map.class, param);
//        }else {
//            responseEntity = restTemplate.exchange(url, method, httpEntity, Map.class);
//        }
//        return (Map) responseEntity.getBody();
//    }
//
//    /**
//     * 有参请求（后端url方式）
//     *
//     * @param url        访问地址
//     * @param method     请求方法类型
//     * @param httpEntity http实体
//     * @param param      请求参数
//     * @return
//     */
//    public Map exchange(String url, HttpMethod method, HttpEntity httpEntity, Map<String, Object> param) {
//        //处理url和参数
//        url += "?";
//        for (String key : param.keySet()) {
//            url = url + key + "={" + key + "}&";
////            param.put(key, param.get(key) == null ? null : URIDecoder.decodeURIComponent(param.get(key).toString()));
//        }
//        url = ApiConstant.HTTP + url.substring(0, url.length() - 1);
////        url = "http://39.105.162.131:8080" + url.substring(0, url.length() - 1);
//        ResponseEntity responseEntity = restTemplate.exchange(url, method, httpEntity, Map.class, param);
//        //请求类型
//        return (Map) responseEntity.getBody();
//    }
//
//    /**
//     * exchangePost有参请求（后端body）
//     *
//     * @param url        访问地址
//     * @param method     请求方法类型
//     * @param httpEntity http实体（包含请求头header和body传参（post请求））
//     * @param param      请求参数
//     * @return
//     */
//    public Map exchangeBody(String url, HttpMethod method, HttpEntity httpEntity, Map<String, Object> param) {
//        HttpEntity<MultiValueMap<String, String>> entity;
//        //处理url和参数
//        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
//        for (Map.Entry<String, Object> entry : param.entrySet()) {
//            map.add(entry.getKey(), entry.getValue().toString());
//        }
//        entity = new HttpEntity<>(map, httpEntity.getHeaders());
//        ResponseEntity responseEntity = restTemplate.exchange(ApiConstant.HTTP + url, method, entity, Map.class);
//        return (Map) responseEntity.getBody();
//    }
//
//
//    /**
//     * 封装api请求头
//     *
//     * @return
//     */
//    public HttpHeaders getHeader(String apiId,boolean isClient) {
//        HttpHeaders header = new HttpHeaders();
//        //当前时间戳 10位
//        String timestamp = String.valueOf(System.currentTimeMillis() / 1000);
//        //加密的签名（不在白名单）
//        String signature = getMD5(apiId + apiGateway.getAccessKeyID() + apiGateway.getAccessKeySecret() + apiGateway.getAppKey() + apiGateway.getAppSecret() + timestamp);
//        //设置请求头
//        header.add("apiId", apiId);
//        header.add("accessKeyID", apiGateway.getAccessKeyID());
//        header.add("appKey", apiGateway.getAppKey());
//        header.add("timestamp", timestamp);
//        header.add("signature", signature);
//        header.add("originIp", isClient ? IPUtil.getIpAddr() : apiGateway.getOriginIp());
//        return header;
//    }
//
//    /**
//     * MD5加密
//     *
//     * @param str
//     * @return
//     */
//    public String getMD5(String str) {
//        String md5 = DigestUtils.md5DigestAsHex(str.getBytes());
//        return md5;
//    }
//
//    public String getData(Map map){
//       boolean isSuccess =  isSuccess(map);
//       if(!isSuccess){
//           return null;
//       }
//        return map.get("data")==null ? null : map.get("data").toString();
//    }
//
//    public boolean isSuccess(Map map){
//        if(null == map){
//            return false;
//        }
//        Object o = map.get("code");
//        if(null == o){
//            return false;
//        }
//        if(!o.toString().equals("200")){
//            return false;
//        }
//        return true;
//    }
//
//    /**
//     * 无参请求
//     *
//     * @return
//     */
//    public Map exchange(StandardMultipartHttpServletRequest request, Map<String,Object> params) {
//        HttpHeaders headers = getHeader(VideosApiConstant.UPLOAD_VIDEOS_APIID,false);
//        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
//        MultiValueMap<String, Object> parts = new LinkedMultiValueMap<>();
//        parts.add("file",request.getFile("file").getResource());
//        Enumeration<String> enumeration= request.getParameterNames();
//        while(enumeration.hasMoreElements()){
//            String key = enumeration.nextElement();
//            parts.add(key,request.getParameter(key));
//        }
//
//        HttpEntity<MultiValueMap<String, Object>> httpEntity = new HttpEntity<>(parts, headers);
//        return exchange2(VideosApiConstant.UPLOAD_VIDEOS_URL,HttpMethod.POST,httpEntity,params);
//    }
//
//    /**
//     * 有参请求（后端url方式）
//     *
//     * @param url        访问地址
//     * @param method     请求方法类型
//     * @param httpEntity http实体
//     * @param param      请求参数
//     * @return
//     */
//    public Map exchange2(String url, HttpMethod method, HttpEntity httpEntity, Map<String, Object> param) {
//        //处理url和参数
//        url += "?";
//        for (String key : param.keySet()) {
//            url = url + key + "={" + key + "}&";
//        }
//        url = ApiConstant.HTTP + url.substring(0, url.length() - 1);
////        url = "http://192.168.1.156:8081" + url.substring(0, url.length() - 1);
////        url = "http://39.105.162.131:8080" + url.substring(0, url.length() - 1);
////        url = "http://192.168.1.107:9001" + url.substring(0, url.length() - 1);
////        url = "http://39.105.153.115:9001" + url.substring(0, url.length() - 1);
//
//
//        ResponseEntity responseEntity = restTemplate.exchange(url, method, httpEntity, Map.class, param);
//
//        HttpClient httpClient = new DefaultHttpClient() ;
//
//
//        System.out.println("请求结果："+responseEntity);
//        //请求类型
//        return (Map) responseEntity.getBody();
//    }
//
////    /**
////     * 云平台api访问(后端)
////     *
////     * @param apiUrl api地址
////     * @param apiId  apiID
////     * @param param  参数
////     * @return
////     */
////    public Map apiGateway6(String apiUrl, String apiId, Map<String, Object> param, HttpMethod method) {
////        long start = System.currentTimeMillis();
////        //打印网关访问参数
////        HashMap<String, Object> erroParam = new HashMap<>();
////        erroParam.put("apiUrl", apiUrl);
////        erroParam.put("apiId",apiId);
////        erroParam.put("param",param);
////        log.info("apiGatewayParam:" + JSONObject.toJSONString(erroParam));
////        //请求头
////        HttpHeaders header = getHeader(apiId);
////        //获取访问结果
////            Map exchange = exchange6(apiUrl, method, new HttpEntity(header), param);
////            //处理url和参数
////            apiUrl += "?";
////            for (String key : param.keySet()) {
////                apiUrl = apiUrl + key + "={" + key + "}&";
////            }
////        url = ApiConstant.HTTP + url.substring(0, url.length() - 1);
//////        url = "http://192.168.1.156:8081" + url.substring(0, url.length() - 1);
//////        url = "http://39.105.162.131:8080" + url.substring(0, url.length() - 1);
//////            apiUrl = "http://192.168.1.107:9001" + apiUrl.substring(0, apiUrl.length() - 1);
////            apiUrl = "http://39.105.153.115:9001" + apiUrl.substring(0, apiUrl.length() - 1);
////
//////        ResponseEntity responseEntity = restTemplate.exchange(url, method, httpEntity, Map.class, param);
////            ResponseEntity responseEntity = restTemplate.exchange(apiUrl, method, new HttpEntity(header), Map.class, param);
////            System.out.println("请求结果："+responseEntity);
////            //请求类型
////            return (Map) responseEntity.getBody();
////    }
//
//}
