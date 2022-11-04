package com.example.lzzll.jpa.sys.controller;

import com.example.lzzll.jpa.common.entity.ResponseEntity;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author lf
 * @Date 2022/11/4 14:42
 * @Description: 验证签名的接口测试类
 */
@RestController
@RequestMapping("sign/authen")
@Slf4j
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
    @RequestMapping("getWithNoParam")
    public ResponseEntity getWithNoParam(HttpServletRequest request, HttpServletResponse response){
        if (request.getHeader("appId") == null || request.getHeader("sign") == null || request.getHeader("timestamp") == null){
            return ResponseEntity.fail("请求头不能为空");
        }
        String appId = request.getHeader("appId");
        if (!appSignMap.containsKey(appId) || appSignMap.get(appId) == null){
            return ResponseEntity.fail("该appId未授权");
        }
        String appKey = appSignMap.get(appId);
        String askSign = request.getHeader("sign");
        String timestamp = request.getHeader("timestamp");
        // 验证签名是否有效
//        String sign = SecureUtil.md5().digestHex(appId + appKey + timestamp);
        String sign = DigestUtils.md5Hex((appId + appKey + timestamp).getBytes());
        if (!sign.equals(askSign)){
            return ResponseEntity.fail("验证未通过");
        }
        return ResponseEntity.ok();
    }
}
