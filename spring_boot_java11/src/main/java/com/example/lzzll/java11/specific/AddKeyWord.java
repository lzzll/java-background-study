package com.example.lzzll.java11.specific;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @Author lf
 * @Date 2023/5/31 13:54
 * @Description: java11版本新增的关键字var
 */
public class AddKeyWord {

    public static void main(String[] args) {
        var list = new ArrayList<>();
        Collections.addAll(list,1,1.00,"张三");
        for (Object o : list) {
            System.out.println(o);
        }
        var list1 =  List.of(1,1.00,"张三");
        list1.forEach(System.out::println);
    }
}
