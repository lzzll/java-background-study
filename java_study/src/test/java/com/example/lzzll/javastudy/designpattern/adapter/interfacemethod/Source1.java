package com.example.lzzll.javastudy.designpattern.adapter.interfacemethod;

/**
 * 目标抽象类一，可以将不需要的目标接口中的方法做空实现
 */
public abstract class Source1 implements Target{

    @Override
    public void method1() {
        System.out.println("this is first original method!");
    }

    @Override
    public void method2() {

    }
}
