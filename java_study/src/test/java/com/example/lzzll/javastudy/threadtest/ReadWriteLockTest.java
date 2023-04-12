package com.example.lzzll.javastudy.threadtest;


import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 读写锁实例验证
 * 写锁是独享锁,读锁是共享锁
 */
public class ReadWriteLockTest {
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    Cache.put("key",new String(Thread.currentThread().getName()+" joke"));
                }
            },"threadW-"+ i).start();

            new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Cache.get("key"));
                }
            },"threadR-"+ i).start();

            new Thread(new Runnable() {
                @Override
                public void run() {
                    Cache.clear();
                }
            },"threadW-"+ i).start();
        }
    }

}

    class Cache{
    static Map<String,Object> map = new HashMap<String,Object>();
    static ReadWriteLock rwl = new ReentrantReadWriteLock();
    static Lock r = rwl.readLock();
    static Lock w = rwl.writeLock();
    // 获取一个key对应的value
    public static final Object get(String key){
        r.lock();
        try{
            System.out.println("get "+Thread.currentThread().getName());
            return map.get(key);
        } finally {
            r.unlock();
        }
    }

    // 设置key对应的value,并返回旧有的value
    public static final Object put(String key,Object value){
        w.lock();
        try{
            System.out.println("put "+Thread.currentThread().getName());
            return map.put(key,value);
        } finally {
            w.unlock();
        }
    }

    // 清空所有内容
    public static final void clear(){
        w.lock();
        try {
            System.out.println("clear "+Thread.currentThread().getName());
            map.clear();
        } finally {
            w.unlock();
        }
    }
}
