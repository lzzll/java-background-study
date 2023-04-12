package com.example.lzzll.javastudy.designpattern.adapter.interfacemethod;

/**
 * 目标类只需要继承对应的有想要方法的抽象类就可以了
 */
public class Source extends Source1 {
    @Override
    public void method1() {
        super.method1();
    }
}
