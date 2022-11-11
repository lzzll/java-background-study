package com.example.lzzll.aspose.common.filter;

import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author lf
 * @Date 2022/11/9 16:52
 * @Description:
 */
@Slf4j
@WebFilter("/*")   //过滤所有地址
public class AuthenSecurityFilter implements Filter {

    // 内存中放入多组授权组，模拟数据库拆查询授权
    private static Map<String,String> appSignMap;

//    static {
//        appSignMap = new HashMap<>();
//        appSignMap.put("123456789","987654321");
//        appSignMap.put("12345678","87654321");
//        appSignMap.put("123456","654321");
//    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("验签过滤器启动了",this.getClass().getName());
        appSignMap = new HashMap<>();
        appSignMap.put("123456789","987654321");
        appSignMap.put("12345678","87654321");
        appSignMap.put("123456","654321");
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        log.info("验签过滤器拦截：{}",request.getServletPath());

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
            return;
        }
        String appId = request.getHeader("appId");
        if (!appSignMap.containsKey(appId) || appSignMap.get(appId) == null){
            resultMap.put("code",402);
            resultMap.put("msg","该appId未授权");
            //将json串输出到浏览器(通过流)
            response.setContentType("application/json;charset=UTF-8");//设置响应数据格式和编码
            PrintWriter out = response.getWriter();
            out.print(JSONUtil.toJsonStr(resultMap));
            out.flush();
            out.close();
            return;
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
            //将json串输出到浏览器(通过流)
            response.setContentType("application/json;charset=UTF-8");//设置响应数据格式和编码
            PrintWriter out = response.getWriter();
            out.print(JSONUtil.toJsonStr(resultMap));
            out.flush();
            out.close();
            return;
        }
        filterChain.doFilter(request,response);
    }

    @Override
    public void destroy() {
        log.info("验签过滤器销毁了",this.getClass().getName());
    }
}
