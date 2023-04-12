package com.example.lzzll.javastudy.designpattern.singleton;

/**
 * 单例模式
 */
public class SingleTon {

    // 持有私有静态实例，防止被引用，此处赋值为null，目的是为了延迟加载
    private static SingleTon instance = null;

    // 私有构造方法，防止被实例化
    private SingleTon() {
    }

    // 静态工程方法，创建实例
   /* public static SingleTon getSingleTon(){
        if (null == instance){
            return new SingleTon();
        }
        return instance;
    }*/
    // 实际使用的时候还要考虑线程安全的问题，可以使用syncronized同步代码块将对象进行包裹，保证对象创建的是唯一的
    /* public static SingleTon getSingleTon(){
         if (instance == null){
             // 只在对象创建的时候加锁
             synchronized (instance){
                 if (null == instance){
                     return new SingleTon();
                 }
             }
         }
        return instance;
     }*/
    // 此处使用一个内部类来维持单例
    private static class SingletonFactory{
        private static SingleTon instance = new SingleTon();
    }

    // 获取实例
    public static SingleTon getInstance(){
        return SingletonFactory.instance;
    }

    // 如果该对象被用于实例化，可以保证对象在序列化前后保持一致
    public Object readResolve(){
        return instance;
    }

}
