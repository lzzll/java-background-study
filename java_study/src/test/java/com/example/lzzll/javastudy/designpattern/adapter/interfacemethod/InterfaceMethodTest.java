package com.example.lzzll.javastudy.designpattern.adapter.interfacemethod;

/**
 * 基于接口的适配器模式：
 * 当用户对于目标接口中的方法不需要实现那么多的时候，可以定义一个抽象类，对接口中不需要的方法进行空实现，再让自定义类继承该抽象类，覆写其想要的方法即可
 */
public class InterfaceMethodTest {
    public static void main(String[] args) {
        Source source = new Source();
        source.method1();
        source.method2();  // 空实现
    }
}
