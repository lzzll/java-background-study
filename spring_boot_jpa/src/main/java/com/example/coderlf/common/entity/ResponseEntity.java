package com.example.coderlf.common.entity;


import org.springframework.http.HttpStatus;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author lf
 * @Date 2020/7/6 15:04
 * @Description: 数据交互返回试题类
 */
public class ResponseEntity extends HashMap<String,Object> implements Serializable {

    private static final long serialVersionUID = 1L;

    public ResponseEntity() {
        put("code", 200);
        put("msg", "success");
    }

    /**
     * code 500
     * @return
     */
    public static ResponseEntity error() {
        return error(HttpStatus.INTERNAL_SERVER_ERROR.value(), "未知异常，请联系管理员");
    }

    /**
     * code 500 自定义异常
     * @param msg
     * @return
     */
    public static ResponseEntity error(String msg) {
        return error(HttpStatus.INTERNAL_SERVER_ERROR.value(), msg);
    }

    public static ResponseEntity error(int code, String msg) {
        ResponseEntity r = new ResponseEntity();
        r.put("code", code);
        r.put("msg", msg);
        return r;
    }

    public static ResponseEntity ok(String msg) {
        ResponseEntity r = new ResponseEntity();
        r.put("msg", msg);
        return r;
    }

    public static ResponseEntity ok(Map<String, Object> map) {
        ResponseEntity r = new ResponseEntity();
        r.putAll(map);
        return r;
    }

    public static ResponseEntity ok() {
        return new ResponseEntity();
    }

    @Override
    public ResponseEntity put(String key, Object value) {
        super.put(key, value);
        return this;
    }

    /**
     * 自定义失败信息 code 500
     * @param msg
     * @return
     */
    public static ResponseEntity fail(String msg) {
        ResponseEntity r = new ResponseEntity();
        r.put("code",HttpStatus.INTERNAL_SERVER_ERROR.value());
        r.put("msg", msg);
        return r;
    }

    /**
     * code 500
     * @return
     */
    public static ResponseEntity fail() {
        ResponseEntity r = new ResponseEntity();
        r.put("code",HttpStatus.INTERNAL_SERVER_ERROR.value());
        r.put("msg", "操作失败");
        return r;
    }

    public static ResponseEntity noAuth() {
        return noAuth("权限不足");
    }

    /**
     * 401 错误类型，权限不足
     * @param msg
     * @return
     */
    public static ResponseEntity noAuth(String msg) {
        ResponseEntity r = new ResponseEntity();
        r.put("code",HttpStatus.UNAUTHORIZED.value());
        r.put("msg", msg);
        return r;
    }

    /**
     * 403 禁止访问
     * @param msg
     * @return
     */
    public static ResponseEntity forbidden(String msg) {
        ResponseEntity r = new ResponseEntity();
        r.put("code",HttpStatus.FORBIDDEN);
        r.put("msg", msg);
        return r;
    }

    /**
     * 400 自定义错误信息
     * @param msg
     * @return
     */
    public static ResponseEntity badRequest(String msg) {
        ResponseEntity r = new ResponseEntity();
        r.put("code",HttpStatus.BAD_REQUEST.value());
        r.put("msg", msg);
        return r;
    }

    /**
     * 400 错误请求
     * @return
     */
    public static ResponseEntity badRequest() {
        ResponseEntity r = new ResponseEntity();
        r.put("code",HttpStatus.BAD_REQUEST);
        r.put("msg", "错误的请求");
        return r;
    }

}
