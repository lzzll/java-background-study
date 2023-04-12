package com.example.lzzll.javastudy.guava;

import com.google.common.math.IntMath;
import org.junit.Test;

/**
 * @Author lf
 * @Date 2019/9/29 17:21
 * @Description:
 */
public class MathTest {

    @Test
    public void test(){
        double random = Math.floor(Math.random()*1000000);
        int number = new Double(random).intValue();  // 将double转化为int类型
        System.out.println(random);
        System.out.println(number);
    }

    @Test
    public void test2(){
        int i = IntMath.checkedSubtract(3, 10);
        System.out.println(i);
    }
}
