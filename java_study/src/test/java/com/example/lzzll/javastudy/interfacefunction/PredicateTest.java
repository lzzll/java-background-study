package com.example.lzzll.javastudy.interfacefunction;

import java.util.ArrayList;
import java.util.function.Predicate;

/**
 * predicate (判断型接口)
 * 利用其抽象方法test()对某种数据类型进行判断,返回值为boolean型数据.
 * 三个默认方法:predicate1.and(predicate2).test(T t) -- 表示两个条件同时满足
 * predicate1.or(predicate2).test(T t) -- 表示两个条件满足一个即可
 * predicate1.negate().test(T t) -- 对判断的结果进行取反
 * 练习:对人物的信息进行判断.
 */

public class PredicateTest {
    public static void main(String[] args) {
        // 创建一个字符串数组
        String[] array = {"迪丽热巴,女", "古力娜扎,女", "马尔扎哈,男", "赵丽颖,女"};
        // 遍历数组
        for (String s : array) {
            filter(s,
                    (string) -> {
                        // 判断字符串中姓名的长度是否等于四个字
                        return string.split(",")[0].length() == 4;
                    },
                    (string) -> {
                        // 判断人物的性别是否为女
                        return string.split(",")[1].equals("女");

                    });
        }
    }

    // 定义一个方法,对字符串的内容进行判断
    public static void filter(String str, Predicate<String> p1, Predicate<String> p2) {
        // 创建一个字符串集合
        ArrayList<String> list = new ArrayList<>();
        // 对参数传递进来的字符串进行判断
        boolean b = p1.and(p2).test(str);
        // 对判断的结果进行判断
        if (b) {
            list.add(str);
        }
        for (String s : list) {
            System.out.println(s);
        }
    }
}
