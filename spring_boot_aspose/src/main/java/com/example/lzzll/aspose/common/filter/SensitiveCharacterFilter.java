package com.example.lzzll.aspose.common.filter;

import com.example.lzzll.aspose.common.overrideClass.MyServletRequest;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.*;
import java.io.*;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author lf
 * @Date 2022/11/10 15:23
 * @Description:
 */
@Slf4j
@WebFilter("/*")   //过滤所有地址
public class SensitiveCharacterFilter implements Filter {

    /**
     * 敏感字符集合
     */
    private List<String> list = new ArrayList<>();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("敏感字符过滤器启动了",this.getClass().getName());
        try {
            //1、加载文件,获取文件真实路径
            ServletContext servletContext = filterConfig.getServletContext();
            InputStream inputStream = servletContext.getClassLoader().getResourceAsStream("config/sensitiveCharacter.txt");
            //2、读取文件
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            //3、将文件中的每一行数据添加到list集合中
            String line = null ;
            while ((line = bufferedReader.readLine()) != null){
                list.add(line);
            }
            bufferedReader.close();
            System.out.println(list);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
        log.info("敏感字符过滤器执行了",this.getClass().getName());
        // 过去请求参数中的敏感字符
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        String contentType = request.getContentType();
        if (contentType != null && contentType.contains("application/x-www-form-urlencoded")) {
            //如果是application/x-www-form-urlencoded, 参数值在request body中以 a=1&b=2&c=3...形式存在，
            //      若直接构造BodyReaderHttpServletRequestWrapper，在将流读取并存到copy字节数组里之后,
            //      httpRequest.getParameterMap()将返回空值！
            //      若运行一下 httpRequest.getParameterMap(), body中的流将为空! 所以两者是互斥的！
            request.getParameterMap();
        }
        MyServletRequest myServletRequest = new MyServletRequest(request, list);
        String value = myServletRequest.getParameter("name");
        System.out.println(value);
        System.out.println(myServletRequest);
        chain.doFilter(myServletRequest,servletResponse);

//        Map<String, String[]> parameterMap = request.getParameterMap();
//        if (parameterMap != null && parameterMap.size() != 0){
//            Set<Map.Entry<String, String[]>> entrySet = parameterMap.entrySet();
//            for (Map.Entry<String, String[]> entry : entrySet) {
//                String[] params = entry.getValue();
//                String key = entry.getKey();
//                List<String> paramList = new ArrayList<>();
//                if (params != null && params.length != 0){
//                    for (String param : params) {
//                        for (String sensiStr : list) {
//                            if(param.contains(sensiStr)){
//                                param = param.replaceAll(sensiStr,"***");
//                                paramList.add(param);
//                            }
//                        }
//                    }
//                }
//                parameterMap.put(key,convert(paramList));
//            }
//        }
//        chain.doFilter(request, servletResponse);
    }

    @Override
    public void destroy() {
        log.info("敏感字符过滤器销毁了",this.getClass().getName());
    }


    public static String[] convert(List<String> setOfString){
        // 创建和字符串集大小相同的一个空字符串数组
        String[] arrayOfString = new String[setOfString.size()];
        // 使用高级for循环，将字符串集合里的每个元素复制到字符串数组中。
        int index = 0;
        for (String str : setOfString){
            arrayOfString[index++] = str;
        }
        // 返回字符串数组
        return arrayOfString;
    }
}
