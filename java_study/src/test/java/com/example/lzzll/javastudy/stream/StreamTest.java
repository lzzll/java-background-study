package com.example.lzzll.javastudy.stream;

/**
 * @Author lf
 * @Date 2019/9/23 9:54
 * @Description:
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

/**
 * * 练习:集合元素处理（传统方式）
 现在有两个ArrayList集合存储队伍当中的多个成员姓名，要求使用传统的for循环（或增强for循环）依次进行以下若干操作步骤：
 1. 第一个队伍只要名字为3个字的成员姓名；存储到一个新集合中。
 2. 第一个队伍筛选之后只要前3个人；存储到一个新集合中。
 3. 第二个队伍只要姓张的成员姓名；存储到一个新集合中。
 4. 第二个队伍筛选之后不要前2个人；存储到一个新集合中。
 5. 将两个队伍合并为一个队伍；存储到一个新集合中。
 6. 根据姓名创建Person对象；存储到一个新集合中。
 7. 打印整个队伍的Person对象信息。
 */
public class StreamTest {
    public static void main(String[] args) {

        List<String> list1 = new ArrayList<String>();
        Collections.addAll(list1,"阿斯兰","基拉大和","卡嘉莲","拉克丝");
//        List<String> list2 = new ArrayList<String>();
//        Collections.addAll(list2,"张三丰","宋远桥","俞岱岩","俞松溪","张翠山","殷梨亭","张无忌");
        Stream<String> stream1 = list1.stream().filter(
                (str) -> str.length() == 3
        ).limit(3);
        Stream<String> stream2 = Stream.of("张三丰", "宋远桥", "俞岱岩", "俞松溪", "张翠山", "殷梨亭", "张无忌").filter(
                str -> str.startsWith("张")
        ).skip(2);
        Stream.concat(stream1,stream2).map(
//                new Function<String, Object>() {
//
//                    @Override
//                    public Object apply(String s) {
//                        return new Person(s);
//                    }
//                }.andThen(new Function<Object, Object>() {
//
//                    @Override
//                    public Object apply(Object o) {
//                        return null;
//                    }
//                })
        (name)->new Person(name,"男",30)
        ).forEach(
                str-> System.out.println(str)
        );

    }

}
