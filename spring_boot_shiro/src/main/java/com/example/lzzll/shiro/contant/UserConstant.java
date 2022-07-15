package com.example.lzzll.shiro.contant;

/**
 * @Author lf
 * @Date 2022/7/11 9:58
 * @Description: 用户常量
 */
public class UserConstant {

    /**
     * 用户状态枚举
     */
    public enum StateEnum{
        NOT_USE(0,"未激活"),
        USED(1,"已激活"),
        LOCKED(2,"已锁定"),
        ;

        public int code;
        public String name;

        StateEnum(int code, String name) {
            this.code = code;
            this.name = name;
        }
    }

}
