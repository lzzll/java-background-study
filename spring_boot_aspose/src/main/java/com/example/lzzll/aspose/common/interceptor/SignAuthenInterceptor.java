package com.example.lzzll.aspose.common.interceptor;

import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author lf
 * @Date 2022/11/9 17:18
 * @Description:
 */
@Slf4j
public class SignAuthenInterceptor implements HandlerInterceptor {

    // 内存中放入多组授权组，模拟数据库拆查询授权
    private static Map<String,String> appSignMap;

    static {
        appSignMap = new HashMap<>();
        appSignMap.put("123456789","987654321");
        appSignMap.put("12345678","87654321");
        appSignMap.put("123456","654321");
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("认证拦截器执行了：{}",request.getServletPath());
        HashMap<String, Object> resultMap = new HashMap<>();
        if (request.getHeader("appId") == null || request.getHeader("sign") == null || request.getHeader("timestamp") == null){
            resultMap.put("code",401);
            resultMap.put("msg","请求头不能为空");
            //将json串输出到浏览器(通过流)
            response.setContentType("application/json;charset=UTF-8");//设置响应数据格式和编码
            PrintWriter out = response.getWriter();
            out.print(JSONUtil.toJsonStr(resultMap));
            out.flush();
            out.close();
            return false;
        }
        String appId = request.getHeader("appId");
        if (!appSignMap.containsKey(appId) || appSignMap.get(appId) == null){
            resultMap.put("code",402);
            resultMap.put("msg","该appId未授权");
            //将json串输出到浏览器(通过流)
            response.setContentType("application/json;charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.print(JSONUtil.toJsonStr(resultMap));
            out.flush();
            out.close();
            return false;
        }
        String appKey = appSignMap.get(appId);
        String askSign = request.getHeader("sign");
        String timestamp = request.getHeader("timestamp");
        // todo 可增加逻辑校验请求是否过期

        // 验证签名是否有效
//        String sign = SecureUtil.md5().digestHex(appId + appKey + timestamp);
        String sign = DigestUtils.md5Hex((appId + appKey + timestamp).getBytes());
        if (!sign.equals(askSign)){
            resultMap.put("code",405);
            resultMap.put("msg","验证未通过");
            //将json串输出到浏览器(通过流)
            response.setContentType("application/json;charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.print(JSONUtil.toJsonStr(resultMap));
            out.flush();
            out.close();
            return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        log.info("认证拦截器在controller方法执行完之后执行了：{}",request.getServletPath());
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        log.info("认证拦截器在渲染视图之后执行了：{}",request.getServletPath());
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }

}
