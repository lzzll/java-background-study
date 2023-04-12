package com.example.lzzll.javastudy.interfacefunction;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * //key:姓名 value:成绩
 map.put("岑小村",59);
 map.put("谷天洛",82);
 map.put("渣渣辉",98);
 map.put("蓝小月",65);
 map.put("皮几万",70);

 分别使用lambda表达式完成以下需求
 a) 打印谷天洛的分数
 b) 打印最高分
 c) 分别以60分和70分为及格线，打印及格的人的名字(组合型消费)

 */

public class Test {
    public static void main(String[] args) {
        // 定义一个HashMap集合
        HashMap<String, Integer> map = new HashMap<>();
        // 往集合中添加五个元素
        map.put("岑小村",59);
        map.put("谷天洛",82);
        map.put("渣渣辉",98);
        map.put("蓝小月",65);
        map.put("皮几万",70);
        // 调用方法
        // a) 打印谷天洛的分数
        Integer score = map.get("谷天洛");
        System.out.println("古天洛的分数为:"+score);
        // b) 打印最高分
        int maxNum = printMax(map);
        System.out.println("maxNum = " + maxNum);
        // c) 分别以60分和70分为及格线，打印及格的人的名字(组合型消费)
        printName(70,map);


    }

    // 定义一个方法,打印集合中的最高分
    public static int printMax(HashMap<String,Integer> map /*, Consumer<Integer> con*/ ) {
        int max = 0;
        // 遍历集合
        Set<Map.Entry<String, Integer>> set = map.entrySet();
        for (Map.Entry<String, Integer> entry : set) {
            Integer value = entry.getValue();
//            con.accept(value);
            if (value > max) {
                max = value;
            }
        }
        return max;
    }

    // 定义一个方法,分别以60分和70分为及格线，打印及格的人的名字(组合型消费)
    public static void printName(int score , HashMap<String,Integer> map /*, Consumer<HashMap<String,Integer>> con*/) {
        // 遍历集合
        System.out.println("及格的人分别为:");
        Set<String> set = map.keySet();
        for (String s : set) {
            // 键找值
            Integer value = map.get(s);
            if (value >= score) {
                System.out.println(s);
            }
        }
    }
}
