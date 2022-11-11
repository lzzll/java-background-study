package com.example.lzzll.aspose.common.overrideClass;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author lf
 * @Date 2022/11/11 9:07
 * @Description:
 */
@Slf4j
public class MyServletRequest extends HttpServletRequestWrapper {

    private HttpServletRequest request;

    private List<String>  auditWords;

    public MyServletRequest(HttpServletRequest request,List<String>  auditWords) {
        super(request);
        this.request = request;
        this.auditWords = auditWords;
    }

    @Override
    public String getParameter(String name) {
        log.info("复写的getParameter方法执行了");
        String data = this.request.getParameter(name);
        if (data == null) {
            return null;
        }
        for (String regex : auditWords) {
            Pattern p = Pattern.compile(regex);
            Matcher m = p.matcher(data);
            if (m.find()) {
                String value = m.group();
//                data = data.replaceAll(regex, "<font color='red'>" + value + "</font>");
                data = data.replaceAll(regex, "*"+value+"*");
            }
        }
        return data;
    }


}
