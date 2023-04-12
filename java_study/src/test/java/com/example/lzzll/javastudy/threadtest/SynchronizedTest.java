package com.example.lzzll.javastudy.threadtest;

/**
 * 同步锁的使用规范示例,可重入锁特点
 *      可重入锁又名递归锁,是指在同一个线程在外层方法获取锁的时候,在进入内层方法会自动获取锁,对于ReentrantLock而言意思就是可重入锁,
 * synchronized也是一个可重入锁;
 *      可重入锁可以在一定程度上避免死锁
 *
 *  下面的案例中如果不是可重入锁的话,setB()不会被当作一个线程执行,可能会造成死锁
 */
public class SynchronizedTest {

    synchronized void setA() throws InterruptedException {
        System.out.println("setA方法开始执行....");
        Thread.sleep(2000);
        System.out.println("setA方法休眠结束....");
        setB();
    }

    synchronized void setB() throws InterruptedException {
        System.out.println("setB方法开始执行....");
        Thread.sleep(2000);
        System.out.println("setB方法休眠结束....");
        setA();
    }

    public static void main(String[] args) throws InterruptedException {
        new SynchronizedTest().setA();
    }
}


