package com.example.lzzll.aspose.common.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Map;

/**
 * 自定义重试异常
 * @author lf
 *
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ReTryException extends RuntimeException {
	private static final long serialVersionUID = 1L;

    private String msg;
    private int code = 500;
	private Map<String,String> errorDetailMap = null;

    public ReTryException(String msg) {
		super(msg);
		this.msg = msg;
	}

	public ReTryException(String msg, Throwable e) {
		super(msg, e);
		this.msg = msg;
	}

	public ReTryException(String msg, int code, Map<String,String> errorDetailMap) {
		super(msg);
		this.msg = msg;
		this.code = code;
		this.errorDetailMap = errorDetailMap;
	}

	public ReTryException(String msg, int code, Throwable e) {
		super(msg, e);
		this.msg = msg;
		this.code = code;
	}

}
