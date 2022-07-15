package com.example.lzzll.shiro.common;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author lf
 * @Date 2022/7/8 14:51
 * @Description: 统一返回类
 */
@Data
@NoArgsConstructor
public class R<T> {

    private int code;

    private String message;

    private T data;

    public R(int code,String message){
        this.code = code;
        this.message = message;
    }

    public static R ok(){
        return new R<>(200,"success");
    }

    public static R fail(){
        return new R<>(500,"fail");
    }

    public static R fail(String message){
        return new R<>(500, message);
    }

    public static R fail(int code,String message){
        return new R<>(code, message);
    }

}
