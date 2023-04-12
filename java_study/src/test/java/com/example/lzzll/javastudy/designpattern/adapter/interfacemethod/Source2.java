package com.example.lzzll.javastudy.designpattern.adapter.interfacemethod;

/**
 * 目标抽象类二，可以将不需要的目标接口中的方法做空实现
 */
public abstract class Source2 implements Target{

    @Override
    public void method1() {

    }

    @Override
    public void method2() {
        System.out.println("this is second original method!");
    }
}
