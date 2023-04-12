package com.example.lzzll.javastudy.designpattern.adapter.classmethod;

/**
 * 基于类的适配模式：
 * 适配模式就是定义一个接口（Target），用来扩展原属性类（Source）的功能，
 * 适配器需要继承原来的类并实现扩展接口，这样可以达到在保证原来类的功能情况下再次扩展新功能
 * 希望将一个类转换成满足另一个接口的类时，可以使用类的适配
 */
public class AdapterTest {
    public static void main(String[] args) {
        Adapter adapter = new Adapter();
        adapter.method1();
        adapter.method2();
    }
}
