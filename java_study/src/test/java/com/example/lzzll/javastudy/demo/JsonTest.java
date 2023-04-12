package com.example.lzzll.javastudy.demo;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONUtil;
import org.junit.Test;

import java.util.List;

/**
 * @Author lf
 * @Date 2020/1/7 16:56
 * @Description:
 */
public class JsonTest {

    @Test
    public void test(){
        String abc = "['1']";
        JSONArray aaa = JSONUtil.parseArray(abc);
        System.out.println(aaa);
        List<String> strings = JSONUtil.toList(JSONUtil.parseArray(abc), String.class);
        System.out.println(strings);
    }



}
