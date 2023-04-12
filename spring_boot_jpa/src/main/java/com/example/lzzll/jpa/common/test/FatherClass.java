package com.example.lzzll.jpa.common.test;

/**
 * @Author lf
 * @Date 2022/9/9 9:59
 * @Description:
 */
public class FatherClass {

    private String name;


    public FatherClass(String name){
        this.name = name;
        System.out.println("1执行了");
    }


    public FatherClass(String name,int age){
//        this.name = name;
        System.out.println("2执行了");
    }


//    public FatherClass(){
//
//    }

    public String getName() {
        return name;
    }
}
