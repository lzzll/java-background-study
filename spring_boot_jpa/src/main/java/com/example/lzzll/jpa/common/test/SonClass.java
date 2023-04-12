package com.example.lzzll.jpa.common.test;

import lombok.Data;

/**
 * @Author lf
 * @Date 2022/9/9 10:00
 * @Description:
 */
@Data
public class SonClass extends FatherClass {

    public SonClass(String name) {
//        super(name);
        this();
    }

    public SonClass() {
        super("123");
    }

    public SonClass(int aaa) {
        super("123");
    }


    public SonClass(int aaa,String bbb){
//        super("123");
        this();
    }



    public static void main(String[] args) {
//        SonClass sonClass = new SonClass("aaa");
//        System.out.println(sonClass.getName());
//        SonClass sonClass2 = new SonClass();
//        System.out.println(sonClass2.getName());
//        SonClass sonClass3 = new SonClass(1);
//        System.out.println(sonClass3.getName());
        SonClass sonClass4 = new SonClass(1,"a");
        System.out.println(sonClass4.getName());
    }


}
