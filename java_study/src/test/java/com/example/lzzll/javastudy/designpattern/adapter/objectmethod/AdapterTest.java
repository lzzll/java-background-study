package com.example.lzzll.javastudy.designpattern.adapter.objectmethod;

import com.example.lzzll.javastudy.designpattern.adapter.classmethod.Adapter;

/**
 * 基于对象的适配模式测试：
 */
public class AdapterTest {
    public static void main(String[] args) {
        Adapter adapter = new Adapter();
        adapter.method1();
        adapter.method2();
    }
}
