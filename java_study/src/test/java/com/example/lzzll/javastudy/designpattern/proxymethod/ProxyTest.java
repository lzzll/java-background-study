package com.example.lzzll.javastudy.designpattern.proxymethod;

/**
 * 代理模式：
 * 代理类和目标类实现同一个接口，持有目标类的引用，再到代理类中定义私有的增强方法，可以在调用目标类方法的时候对其进行前后增强
 * 需要对代理类的空参构造进行改造，让代理类持有的目标类对象进行实例化
 *
 */
public class ProxyTest {
    public static void main(String[] args) {
        Sourceable source = new Proxy();
        source.method1();  // 已增强的目标类方法
    }
}
