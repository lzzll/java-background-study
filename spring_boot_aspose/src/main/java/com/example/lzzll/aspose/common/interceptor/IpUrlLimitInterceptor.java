package com.example.lzzll.aspose.common.interceptor;

import cn.hutool.json.JSONUtil;
import com.example.lzzll.aspose.util.http.IpUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author lf
 * @Date 2022/11/10 14:26
 * @Description:
 */
@Slf4j
public class IpUrlLimitInterceptor implements HandlerInterceptor {

    /**
     * 内存中创建一个模拟访问次数的map，如果访问次数超过5就不让访问。实际上应使用redis实现，redis中可设置超时时间
     */
    private static Map<String,Integer> ipAskNumMap = new HashMap<>();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("ip访问次数拦截器执行了：{}",request.getServletPath());
        HashMap<String, Object> resultMap = new HashMap<>();
        boolean canAsk = true;
        // 可通过请求头来实现访问次数的限制
        if (request.getHeader("referer") == null){
            resultMap.put("code",401);
            resultMap.put("msg","请求来源不能为空");
            canAsk = false;
        }
        // 也可通过ip地址来实现访问次数的限制,看具体需求
//        String referer = IpUtils.getRequestIP(request);
//        if (StringUtils.isEmpty(referer)){
//            resultMap.put("code",401);
//            resultMap.put("msg","请求来源不能为空");
//            canAsk = false;
//        }
        String referer = request.getHeader("referer");
        if (ipAskNumMap.containsKey(referer)){
            Integer number = ipAskNumMap.get(referer);
            if (number >= 5){
                resultMap.put("code",402);
                resultMap.put("msg","请求太频繁，请稍后重试");
                canAsk = false;
            }else {
                ipAskNumMap.put(referer,++number);
            }
        }else {
            ipAskNumMap.put(referer,1);
        }
        if (!canAsk){
            //将json串输出到浏览器(通过流)
            response.setContentType("application/json;charset=UTF-8");//设置响应数据格式和编码
            PrintWriter out = response.getWriter();
            out.print(JSONUtil.toJsonStr(resultMap));
            out.flush();
            out.close();
        }
        return canAsk;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        log.info("ip访问次数拦截器在controller方法执行完之后执行了：{}",request.getServletPath());
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        log.info("ip访问次数拦截器在渲染视图之后执行了：{}",request.getServletPath());
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }




}
