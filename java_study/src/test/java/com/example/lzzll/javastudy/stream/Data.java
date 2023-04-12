package com.example.lzzll.javastudy.stream;

import com.google.common.collect.Lists;

import java.util.Arrays;
import java.util.List;

/**
 * @Author lf
 * @Date 2019/12/18 9:02
 * @Description:
 */
public class Data {

    private static List<Person> list = null;
//    private static List<Person> list = new ArrayList<>();

    static {
        Person zhang = new Person("zhangsan", "男", 20);
        Person li = new Person("lisi", "男", 25);
        Person wang = new Person("wangwu", "女", 28);
        Person zhao = new Person("zhaoliu", "男", 40);
        Person tian = new Person("tianqi", "女", 19);
        list= Arrays.asList(zhang,li,wang,zhao,tian);
//        boolean flag = Collections.addAll(list, zhang, li, wang, zhao, tian);
//        System.out.println(flag);

    }

    public static void main(String[] args) {
        System.out.println(list);
    }

}
