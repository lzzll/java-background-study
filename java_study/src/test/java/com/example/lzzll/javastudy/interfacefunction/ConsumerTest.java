package com.example.lzzll.javastudy.interfacefunction;

import java.util.function.Consumer;

/**
 * 利用consumer接口对字符串信息进行拼接.
 *
 * consumer(消费型接口)
    泛型是什么类型,就可以用抽象方法accept(T t)使用该类型数据
    默认方法:consumer1.andThen(consumer2) --连接两个consumer接口,谁在前面谁先使用数据.
    练习:按指定格式输出姓名和性别的信息.
 */

public class ConsumerTest {
    public static void main(String[] args) {
        // 定义一个数组
        String[] array = {"迪丽热巴,女","古力娜扎,女","马尔扎哈,男"};

        // 调用show()方法
        show(array,
                (message) -> {
            // 首先对字符串信息进行切割,获取部分信息,再按照格式进行拼接
                    // 切割后的返回值是一个字符串数组
                    String[] arr = message.split(",");
                    String str = arr[0];
                    System.out.print("姓名:" + str);
                } ,
                (message) -> {
            // 链式编程
                    String str1 = message.split(",")[1];
                    System.out.println("。性别:" + str1 + "。");
                });

    }

    // 定义一个方法,完成对信息进行切割,并按指定格式进行拼接的过程
    public static void show(String[] array, Consumer<String> con1,Consumer<String> con2) {
        // 遍历数组
        for (String message : array) {
            // 消费数据
            con1.andThen(con2).accept(message);
        }

    }
}
