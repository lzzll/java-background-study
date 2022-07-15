package com.example.lzzll.shiro.common.exception;

import lombok.Data;
import org.apache.shiro.authc.AuthenticationException;

/**
 * @Author lf
 * @Date 2022/7/12 10:24
 * @Description:
 */
@Data
public class CpException extends AuthenticationException {

    private String message;

    public CpException(String message){
        this.message = message;
    }

}
