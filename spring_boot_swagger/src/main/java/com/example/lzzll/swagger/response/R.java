package com.example.lzzll.swagger.response;

import io.swagger.annotations.ApiModel;

import java.util.HashMap;

/**
 * @Author lf
 * @Date 2022/7/8 10:03
 * @Description:
 */
//@ApiModel(value = "统一返回结果")
public class R extends HashMap<String,Object>{

    public R() {
        put("code",200);
        put("message","success");
    }

    public R(String message) {
        put("code",200);
        put("message",message);
    }

    public static R ok(){
        R r = new R();
        r.put("code",200);
        r.put("message","success");
        return r;
    }

    public static R ok(Object object){
        R r = new R();
        r.put("code",200);
        r.put("message","success");
        r.put("data",object);
        return r;
    }

    public static R fail(int code){
        R r = new R();
        r.put("code",code);
        r.put("message","fail");
        return r;
    }

    public static R fail(String message){
        R r = new R();
        r.put("code","500");
        r.put("message",message);
        return r;
    }

}
