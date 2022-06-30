package com.example.coderlf.common.util;


import com.sun.tools.javac.util.StringUtils;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author: zfan
 * @Date: 2019/6/24 15:21
 * @Description:
 */

public class StringUtil {

    /**
     * 验证字符串是否为空
     *
     * @param str
     * @return false不为空 true为空
     */
    public static boolean isEmpty(String str) {
        if (null == str) {
            return true;
        }
        if ("".equals(str.trim())) {
            return true;
        }
        return false;
    }

    public static String humpToLine(String hump) {
        Pattern humpPattern = Pattern.compile("[A-Z]");
        if (!isEmpty(hump)) {
            Matcher matcher = humpPattern.matcher(hump);
            StringBuffer sb = new StringBuffer();
            while (matcher.find()) {
                matcher.appendReplacement(sb, "_" + matcher.group(0).toLowerCase());
            }
            matcher.appendTail(sb);
            return sb.toString();
        }
        return null;
    }

    /**
     * 拼接where或者and
     * @param sql
     * @param hasWhere
     * @return
     */
    public static boolean appendWhereOrAnd(StringBuilder sql, boolean hasWhere) {
        if (hasWhere == false) {
            sql.append(" WHERE");
        } else {
            sql.append(" AND");
        }
        return true;
    }


    /**
     * 拼接having或者and
     * @param sql
     * @param hasWhere
     * @return
     */
    public static boolean appendhavingOrAnd(StringBuilder sql, boolean hasWhere) {
        if (hasWhere == false) {
            sql.append(" having ");
        } else {
            sql.append(" AND");
        }
        return true;
    }

    /**
     * 下载中将解析中为空或者无效的字符串替换为"略"
     *
     * @param str
     * @return false不为空 true为空
     */
    public static String replaceInvalidChar(String str) {
        if (null == str) {
            return "略";
        }
        if ("".equals(str.trim())) {
            return "略";
        }
        if ("null".equals(str.trim().toLowerCase())){
            return "略";
        }
        return str;
    }

    public static List<String> covertqid (List<String> qidList){
        List<String> list = new ArrayList<>();
        qidList.forEach(t->{
            String temp = "'"+ t +"'";
            list.add(temp);
        });
        return list;
    }

    /**
     * 截取字符串str中指定字符 strStart、strEnd之间的字符串
     *
     * @param str
     * @param strStart
     * @param strEnd
     * @return
     */
    public static String subString(String str, String strStart, String strEnd) {

        /* 找出指定的2个字符在 该字符串里面的 位置 */
        int strStartIndex = str.indexOf(strStart);
        int strEndIndex = str.indexOf(strEnd);

        /* index 为负数 即表示该字符串中 没有该字符 */
        if (strStartIndex < 0) {
            return "字符串 :---->" + str + "<---- 中不存在 " + strStart + ", 无法截取目标字符串";
        }
        if (strEndIndex < 0) {
            return "字符串 :---->" + str + "<---- 中不存在 " + strEnd + ", 无法截取目标字符串";
        }
        /* 开始截取 */
        String result = str.substring(strStartIndex, strEndIndex).substring(strStart.length());
        return result;
    }

    /**
     * 拼接where和and
     * @param sql
     * @param flag
     * @return
     */
    public static boolean concatSql(StringBuilder sql, boolean flag) {
        if (flag){
            sql.append(" and ");
        }else {
            sql.append(" where ");
        }
        return true;
    }
}
