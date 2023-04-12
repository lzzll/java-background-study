package com.example.lzzll.javastudy.designpattern.singleton;

/**
 * 单例模式的测试
 */
public class SingletonTest {

    public static void main(String[] args) {
        SingleTon instance = SingleTon.getInstance();
        System.out.println(instance);
    }


}
