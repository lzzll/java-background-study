package com.example.lzzll.javastudy.threadtest;

import com.sun.org.apache.xpath.internal.operations.Number;

/**
 * 独享锁/共享锁案例
 * 独享锁只能保证一个线程执行,如ReentrantLock就是一个独享锁,ReadWriteLock中读锁是共享锁,写锁是独享锁
 * 独享锁和共享锁也是通过AQS的方式来实现的,通过实现不同的方式来实现
 * synchronized就是一个独享锁
 * (AQS定义了一套多线程访问共享资源的同步器框架,许多同步类的实现都依赖于它,如常用的ReentrantLock/Semaphore/CountDownLatch)
 * AQS定义了两种资源的共享方式,Exclusive独享,share共享,多个线程可同时执行,如Semaphore/CountDownLatch
 *
 * CAS(比较并交换)是乐观锁技术,当多个线程尝试使用CAS修改某个变量时,只有一个能成功,其它的不是挂起而是继续尝试
 *
 * 互斥锁/读写锁
 * ReentrantLock就是互斥锁,ReadWriteLock就是读写锁
 */

public class AtomicInteger extends Number implements java.io.Serializable {
        private volatile int value;

        public final int get(){
            return value;
        }

        public final int getAndIncrement(){
            while(true){
                int current = get();
                int next = current + 1;
                if (compareAndSet(current,next)){
                    return current;
                }
            }
        }

    private final boolean compareAndSet(int expect, int update) {
//            return unsafe.compareAndSwapInt(this,valueOffset,expect,update);
        return false;
    }

}
