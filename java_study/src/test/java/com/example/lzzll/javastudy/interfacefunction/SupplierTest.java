package com.example.lzzll.javastudy.interfacefunction;

import java.util.function.Supplier;

/**
 *定义一个测试类,用supplier接口来求出数组中的最小值
 *
 * supplier (生产型接口)
 获取一个泛型指定类型的数据
 练习:利用supplier接口求数组最大值
 */

public class SupplierTest {
    public static void main(String[] args) {
        // 创建一个数组
        Integer[] arr = {10,5,21,9,20,30,24,18};
        // 调用getMin方法
        Integer min = getMin(() -> {
            // 求出数组中的最小值
            Integer minNum = arr[0];
            for (Integer num : arr) {
                if (num < minNum) {
                    minNum = num;
                }
            }
            return minNum;
        });

        System.out.println("min = " + min);
    }

    // 定义一个方法,参数使用生产型接口supplier
    public static Integer getMin(Supplier<Integer> sup) {
        return sup.get();
    }
}
