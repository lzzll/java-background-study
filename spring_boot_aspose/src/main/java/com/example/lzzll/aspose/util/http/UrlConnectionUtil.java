package com.example.lzzll.aspose.util.http;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

/**
 * @Author lf
 * @Date 2023/5/31 17:24
 * @Description:  通过net包下的URLConnection类发送原生的http请求
 */
public class UrlConnectionUtil {

    // get测试地址
    private final static String getTestUrl = "http://39.105.162.131:8080/itembank/ques/quesList";

    public static String sendRequest(String urlParam) {

        URLConnection con = null;

        BufferedReader buffer = null;
        StringBuffer resultBuffer = null;

        try {
            URL url = new URL(urlParam);
            con = url.openConnection();

            //设置请求需要返回的数据类型和字符集类型
            con.setRequestProperty("Content-Type", "application/Json; charset=UTF-8");
            con.setRequestProperty("CANPOINTTOKEN", "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VyR3VpZCI6IlIyWlBNMjE0VUVnNVJHTm5aMEZ1UlRkUGJsRTVVVDA5IiwiZXhwIjoxNjg1NTMwMjIxfQ._F8QjtpgV0ySVAQI0SE1KuCioaljLzGJ4g-K7878lKk");
            //允许写出
            con.setDoOutput(true);
            //允许读入
            con.setDoInput(true);
            //不使用缓存
            con.setUseCaches(false);
            //得到响应流
            InputStream inputStream = con.getInputStream();
            //将响应流转换成字符串
            resultBuffer = new StringBuffer();
            String line;
            buffer = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
            while ((line = buffer.readLine()) != null) {
                resultBuffer.append(line);
            }
            return resultBuffer.toString();

        }catch(Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public static void main(String[] args) {
        System.out.println(sendRequest(getTestUrl));
    }

}
