package com.example.lzzll.aspose.controller;

import com.fasterxml.jackson.databind.ser.std.MapSerializer;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author lf
 * @Date 2022/11/9 16:20
 * @Description: 签名验证的测试访问类，一般使用拦截器实现该功能
 */
@RestController()
@RequestMapping("sign")
public class SignAuthenController {

    // 内存中放入多组授权组，模拟数据库拆查询授权
    private static Map<String,String> appSignMap;

    static {
        appSignMap = new HashMap<>();
        appSignMap.put("123456789","987654321");
        appSignMap.put("12345678","87654321");
        appSignMap.put("123456","654321");
    }

    /**
     * 测试签名的验证
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("authen")
    public Map<String,Object> getWithNoParam(HttpServletRequest request, HttpServletResponse response){
        HashMap<String, Object> resultMap = new HashMap<>();
        if (request.getHeader("appId") == null || request.getHeader("sign") == null || request.getHeader("timestamp") == null){
            resultMap.put("code",401);
            resultMap.put("msg","请求头不能为空");
            return resultMap;
        }
        String appId = request.getHeader("appId");
        if (!appSignMap.containsKey(appId) || appSignMap.get(appId) == null){
            resultMap.put("code",402);
            resultMap.put("msg","该appId未授权");
            return resultMap;
        }
        String appKey = appSignMap.get(appId);
        String askSign = request.getHeader("sign");
        String timestamp = request.getHeader("timestamp");
        // 验证签名是否有效
//        String sign = SecureUtil.md5().digestHex(appId + appKey + timestamp);
        String sign = DigestUtils.md5Hex((appId + appKey + timestamp).getBytes());
        if (!sign.equals(askSign)){
            resultMap.put("code",405);
            resultMap.put("msg","验证未通过");
            return resultMap;
        }
        resultMap.put("code",200);
        resultMap.put("msg","success");
        return resultMap;
    }
}
