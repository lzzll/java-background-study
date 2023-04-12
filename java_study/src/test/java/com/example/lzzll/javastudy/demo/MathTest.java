package com.example.lzzll.javastudy.demo;

import com.example.lzzll.javastudy.common.util.MathUtil;
import org.junit.Test;

import java.text.NumberFormat;

/**
 * @Author lf
 * @Date 2019/11/18 15:41
 * @Description:
 */
public class MathTest {

    @Test
    public void test1(){
        String passRate = MathUtil.getPassRate(10, 15);
        System.out.println(passRate);
    }

    @Test
    public void test2(){
        String a = "  ";
        System.out.println("".equals(a.trim()));
    }

    @Test
    public void test3(){
        NumberFormat nf = NumberFormat.getPercentInstance();
        nf.setMaximumFractionDigits(2); //这个1的意识是保存结果到小数点后几位，但是特别声明：这个结果已经先＊100了。
        System.out.println(nf.format(0.12010)); //自动四舍五入。
    }


    @Test
    public void test4(){
//        System.out.println(0.2+0.4);
        System.out.println(1== 0.99999999999f);
    }
}
