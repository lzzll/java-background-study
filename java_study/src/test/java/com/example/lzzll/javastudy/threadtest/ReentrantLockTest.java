package com.example.lzzll.javastudy.threadtest;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 可重入锁验证
 * synchronized是一个可重入,非公平的独占锁;
 * reentrantLock默认是一个非公平锁,但是可以在构造方法中传入一个true将它转变为一个公平锁
 */
public class ReentrantLockTest implements Runnable {

    // 可重入锁
//    private ReentrantLock reentrantLock = new ReentrantLock();  // 默认非公平锁
    private ReentrantLock reentrantLock = new ReentrantLock(true);  // 传入true可以变为公平锁


//    public synchronized void get(){
    public void get(){
        System.out.println("2 enter thread name-->"+Thread.currentThread().getName());
        reentrantLock.lock();
        System.out.println("3 get thread name-->"+Thread.currentThread().getName());
        set();
        reentrantLock.unlock();
        System.out.println("5 leave thread name-->"+Thread.currentThread().getName());

    }

//    private synchronized void set() {
    private void set() {
        reentrantLock.lock();
        System.out.println("3 set thread name-->"+Thread.currentThread().getName());
        reentrantLock.unlock();
    }

    @Override
    public void run() {
        System.out.println("1 run thread name-->"+Thread.currentThread().getName());
        get();

    }

    public static void main(String[] args) {
        ReentrantLockTest test = new ReentrantLockTest();
        for (int i = 0; i < 10; i++) {
            new Thread(test,"thread-"+i).start();
        }
    }
}
