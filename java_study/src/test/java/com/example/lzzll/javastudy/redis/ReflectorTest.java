package com.example.lzzll.javastudy.redis;

import org.junit.Test;

/**
 * @Author lf
 * @Date 2019/9/11 8:38
 * @Description:
 */

/**
 * 百万次级别的使用反射会对性能带来影响，少量使用反射不影响性能，如果大量使用反射时可以考虑使用缓存
 */
// 反射性能测试
public class ReflectorTest {

    @Test
    public void performanceTest(){
        // 直接new出来对象
        long start = System.currentTimeMillis();
        int count = 100*10000;
        for (int i = 0; i < count; i++) {
            ReflectorTest reflectorTest = new ReflectorTest();
        }
        long end = System.currentTimeMillis();
        long time = end- start;
        System.out.println("直接new对象耗费的时间为:"+time);
        long start1 = System.currentTimeMillis();
        for (int i = 0; i < count; i++) {
            try {
                // 方式一：对象.getClass()
//                ReflectorTest re = new ReflectorTest();
//                Class<? extends ReflectorTest> reClass = re.getClass();
//                reClass.newInstance()
                // 方式二; 类名.class()
//                Class<ReflectorTest> reflectorTestClass = ReflectorTest.class;
                // 方式三
                ReflectorTest reflectorTest = (ReflectorTest) Class.forName("com.example.lzzll.redis.ReflectorTest").newInstance();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            }
        }
        long end1 = System.currentTimeMillis();
        long time1 = end1- start1;
        System.out.println("采用反射耗费的时间为:"+time1);

    }


}
