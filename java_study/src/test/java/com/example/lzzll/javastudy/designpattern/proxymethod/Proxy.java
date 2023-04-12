package com.example.lzzll.javastudy.designpattern.proxymethod;

/**
 * 代理类
 */
public class Proxy implements Sourceable {

    private Source source;

    public Proxy() {
        super();   // 可以调用父类的方法
        this.source = new Source();  // 需要对代理类的空参构造进行改造，让代理类持有的目标类对象进行实例化
    }

    /*public Proxy(Source source) {
        this.source = source;   // 或者在有参构造中传入一个目标对象的实例
    } */

    @Override
    public void method1() {
        before();
        source.method1();
        after();
    }

    // 定义增强方法
    private void before(){
        System.out.println("前置增强！");
    }

    private void after(){
        System.out.println("后置增强！");
    }
}
