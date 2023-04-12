package com.example.lzzll.javastudy.guava;

import com.google.common.primitives.Ints;
import org.junit.Test;

import java.util.Arrays;

/**
 * @Author lf
 * @Date 2019/9/25 18:40
 * @Description:
 */
public class IntsTest {

    @Test
    public void test1(){
        int[] content = {1,3,4};
        int index = Ints.indexOf(content, 3);// 1
        int[] arr = Ints.concat(new int[]{1, 2}, new int[]{3, 4});// 1,2,3,4
        boolean flag = Ints.contains(new int[]{10, 20, 30, 40}, 20);// true
        int min = Ints.min(10, 20, 30, 40);// 10
        byte[] bytes = Ints.toByteArray(10);
        System.out.println(bytes);
        System.out.println(Arrays.toString(bytes));
    }
}
