package com.example.lzzll.jpa.common.test;

import java.util.Calendar;

/**
 * @Author lf
 * @Date 2022/9/9 13:36
 * @Description:
 */
public class TestClass {

    static int anInt ;

    int bbb;
    Integer bbbb;

    final static int ccc;

    static final int ddd = 10;

    static {
        ccc = 5;
    }

    int eee = 20;

    public void go(){
//        System.out.println(anInt);
//        System.out.println(bbb);
        System.out.println(ccc);
        System.out.println(ddd);
    }

    public void go1(final int x){
//        System.out.println(anInt);
//        System.out.println(bbb);
        System.out.println(ccc);
        System.out.println(ddd);
        System.out.println(x);
    }

    public void go2(final int x){
        System.out.println(eee);
    }

    public void go3(){
        bbbb = bbb;
        System.out.println(bbb);
        System.out.println(bbbb);
    }

    static final int DAY_IM = 24 * 60 * 60 * 1000;

    public static void main(String[] args) {
//        new TestClass().go();
//        new TestClass().go1(2);
//          new TestClass().go2(5);
//          new TestClass().go3();
//        System.out.println(String.format("%,1.1f",100.659853));
//        System.out.println(String.format("%X",18));

        try {
            Calendar cal = Calendar.getInstance();
//        System.out.println(cal.getClass());
            cal.set(2004,0,7,15,40);
            System.out.println(String.format("%tc",cal));
            long timeInMillis = cal.getTimeInMillis();
            for (int i = 0; i < 60; i++) {
                timeInMillis += 29.26 * DAY_IM;
                cal.setTimeInMillis(timeInMillis);
                System.out.println(String.format("%tc",cal));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
