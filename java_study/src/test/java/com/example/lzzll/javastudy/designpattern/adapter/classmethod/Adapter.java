package com.example.lzzll.javastudy.designpattern.adapter.classmethod;

// 适配者模式的实现类
/**
 * 基于类的适配模式：
 * 适配模式就是定义一个接口（Target），用来扩展原属性类（Source）的功能，
 * 适配器需要继承原来的类并实现扩展接口，这样可以达到在保证原来类的功能情况下再次扩展新功能
 */
public class Adapter extends Source implements Target {
    @Override
    public void method2() {
        System.out.println(" this is a targetable method!");
    }
}
