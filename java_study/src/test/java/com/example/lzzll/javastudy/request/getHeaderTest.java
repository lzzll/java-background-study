package com.example.lzzll.javastudy.request;

import com.alibaba.druid.support.http.util.IPAddress;
import com.example.lzzll.javastudy.common.util.IPUtil;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpRequest;
import sun.net.util.IPAddressUtil;

/**
 * @Author lf
 * @Date 2019/9/17 17:26
 * @Description:
 */
public class getHeaderTest {


    // 获取用户ip
    @Test
    public void getIp(){
        System.out.println(IPUtil.getIpAddr());
    }

}
