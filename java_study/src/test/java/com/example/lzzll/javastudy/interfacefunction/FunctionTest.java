package com.example.lzzll.javastudy.interfacefunction;

import java.util.function.Function;

/**
 * Function (转换型接口) function(T R )
        利用其抽象方法appay(T t)可以将T类型的数据转换为R类型,并返回R类型的对象
        默认方法:function1.andThen(function2).apply(T t) -- 可以对数据进行多次转换,function1先调用apply方法
    练习:截取人物信息的年龄字段,进行数据转换操作.
 */

public class FunctionTest {
    public static void main(String[] args) {
        // 创建一个字符串对象
        String str = "赵丽颖,20";
        // 调用change方法
        int number = change(str, (String string) -> {
            // 截取字符串中的年龄
            String s = str.split(",")[1];
            return s;
        }, (String num) -> {
            // 将字符串对象转化为Integer对象
            int i = Integer.parseInt(num);
            return i;
        }, (Integer integer) -> {
            // 将Integer类型的对象加100后返回
            return integer + 100;
        });
        System.out.println("number = " + number);

    }

    // 定义一个方法,将字符串类型的数据转换成Integer类型的数据,并对数据进行加100的操作
    public static int change(String str , Function<String , String> fun1 ,
                             Function<String , Integer> fun2 ,Function<Integer , Integer> fun3) {
        Integer num = fun1.andThen(fun2).andThen(fun3).apply(str);
        return num;
    }
}
