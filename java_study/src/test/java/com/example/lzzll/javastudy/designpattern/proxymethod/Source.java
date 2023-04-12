package com.example.lzzll.javastudy.designpattern.proxymethod;

/**
 * 目标类
 */
public class Source implements Sourceable {
    @Override
    public void method1() {
        System.out.println("this is a original class method!");
    }
}
