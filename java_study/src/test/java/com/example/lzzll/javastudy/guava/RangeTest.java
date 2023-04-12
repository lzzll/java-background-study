package com.example.lzzll.javastudy.guava;

import com.google.common.collect.BoundType;
import com.google.common.collect.ContiguousSet;
import com.google.common.collect.DiscreteDomain;
import com.google.common.collect.Range;
import org.junit.Test;

/**
 * @Author lf
 * @Date 2019/9/29 16:47
 * @Description:
 */
// guava中区间的测试
public class RangeTest {

    private static void print(Object obj){
        System.out.println(String.valueOf(obj));
    }

    @Test
    public void test1(){
        Range<String> str = Range.closed("A", "z");  //字典序在"left"和"right"之间的字符串，闭区间
        Range<Double> doubleRange = Range.lessThan(4.2);  //严格小于4.2的double值
        print(str);
        print(doubleRange);
    }

    @Test
    public void test2(){
        Range<Integer> range = Range.range(4, BoundType.CLOSED, 10, BoundType.OPEN);
        Range<Integer> canonical = range.canonical(DiscreteDomain.integers());
        System.out.println(range);
        System.out.println(canonical);
        ContiguousSet<Integer> set = ContiguousSet.create(range, DiscreteDomain.integers());   // 使用离散域创建一个set的视图映射
        print(set);
    }
}
