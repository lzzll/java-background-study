package com.example.lzzll.javastudy.common.entity;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import org.apache.http.HttpStatus;

import java.util.HashMap;
import java.util.Map;

/**
 * 返回数据
 *
 * @author zfan
 */
public class R extends HashMap<String, Object> {
	private static final long serialVersionUID = 1L;
	
	public R() {
		put("code", 200);
		put("msg", "success");
	}
	
	public static R error() {
		return error(HttpStatus.SC_INTERNAL_SERVER_ERROR, "未知异常，请联系管理员");
	}
	
	public static R error(String msg) {
		return error(HttpStatus.SC_INTERNAL_SERVER_ERROR, msg);
	}
	
	public static R error(int code, String msg) {
		R r = new R();
		r.put("code", code);
		r.put("msg", msg);
		return r;
	}

	public static R ok(String msg) {
		R r = new R();
		r.put("msg", msg);
		return r;
	}
	
	public static R ok(Map<String, Object> map) {
		R r = new R();
		r.putAll(map);
		return r;
	}
	
	public static R ok() {
		return new R();
	}

	@Override
	public R put(String key, Object value) {
		super.put(key, value);
		return this;
	}

	public static R fail(String msg) {
		R r = new R();
		r.put("code", HttpStatus.SC_INTERNAL_SERVER_ERROR);
		r.put("msg", msg);
		return r;
	}
	public static R fail() {
		R r = new R();
		r.put("code", HttpStatus.SC_INTERNAL_SERVER_ERROR);
		r.put("msg", "操作失败");
		return r;
	}

	public static R noAuth() {
		return noAuth("权限不足");
	}

	public static R noAuth(String msg) {
		R r = new R();
		r.put("code", HttpStatus.SC_UNAUTHORIZED);
		r.put("msg", msg);
		return r;
	}
	public static R forbidden(String msg) {
		R r = new R();
		r.put("code", HttpStatus.SC_FORBIDDEN);
		r.put("msg", msg);
		return r;
	}

	public static R badRequest(String msg) {
		R r = new R();
		r.put("code", HttpStatus.SC_BAD_REQUEST);
		r.put("msg", msg);
		return r;
	}

	public static R badRequest() {
		R r = new R();
		r.put("code", HttpStatus.SC_BAD_REQUEST);
		r.put("msg", "错误的请求");
		return r;
	}
	public static R badParam() {
		R r = new R();
		r.put("code", HttpStatus.SC_BAD_REQUEST);
		r.put("msg", "访问失败");
		return r;
	}

	public static R okWithEmptyObj() {
		return new R().put("data", new JSONObject());
	}

	public static R okWithEmptyArray() {
		return new R().put("data", new JSONArray());
	}

	// 工作台试题任务状态不对，需要重新领取下一道题，返回的code为405
	public static R taskStateError() {
		R r = new R();
		r.put("code", HttpStatus.SC_METHOD_NOT_ALLOWED);
		r.put("msg", "试题在其它批次报错，修改后将同步至此题");
		return r;
	}
}
