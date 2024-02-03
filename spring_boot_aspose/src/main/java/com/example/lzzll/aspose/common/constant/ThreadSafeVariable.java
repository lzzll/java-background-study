package com.example.lzzll.aspose.common.constant;

/**
 * @Author lf
 * @Date 2023/9/1 15:26
 * @Description: 线程安全的变量实现
 */
public class ThreadSafeVariable {

    private static int globalVariable; // 私有静态变量作为全局变量

    private static volatile ThreadSafeVariable instance; // 保存全局变量实例的引用

    private ThreadSafeVariable() {
        // 私有构造函数
    }

    public static ThreadSafeVariable getInstance() {
        if (instance == null) { // 第一次检查，避免不必要的同步
            synchronized (ThreadSafeVariable.class) {
                if (instance == null) { // 第二次检查，确保只有一个实例被创建
                    instance = new ThreadSafeVariable();
                }
            }
        }
        return instance;
    }

    public synchronized int getGlobalVariable() {
        return globalVariable;
    }

    public synchronized void setGlobalVariable(int value) {
        globalVariable = value;
    }

}
