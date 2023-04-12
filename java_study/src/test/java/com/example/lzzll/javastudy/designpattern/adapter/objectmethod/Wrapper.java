package com.example.lzzll.javastudy.designpattern.adapter.objectmethod;

/**
 * 基于对象的适配模式
 * 这种情况下不需要继承源文件类source，只需要持有源文件对象即可，然后实现目标接口，一样可以通过适配器类调用源文件的方法和接口中的扩展方法
 * 希望将一个对象转化为接口对象时，可以使用对象的适配
 */
public class Wrapper implements Target {

    private Source source;

    public Wrapper(Source source) {
        this.source = source;
    }


    @Override
    public void method1() {
        source.method1();  // 注意持有原类的对象的时候，覆写的方法需要由成员变量来调用
    }

    @Override
    public void method2() {
        System.out.println("this is a targetbale method!");
    }
}
