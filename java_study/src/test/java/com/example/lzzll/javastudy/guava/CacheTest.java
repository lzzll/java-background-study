package com.example.lzzll.javastudy.guava;

import com.example.lzzll.javastudy.guava.domain.Student;
import com.google.common.cache.*;
import com.google.common.collect.Maps;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @Author lf
 * @Date 2019/9/25 14:16
 * @Description:
 */

/**
 * 使用guava缓存的条件：
 * 1.愿意消耗一些内存空间来提升速度。
 * 2.预料到某些键会被多次查询。
 * 3.缓存中存放的数据总量不会超出内存容量。
 */
public class CacheTest {

    // 测试缓存设置最大数量，如果达到数量上限之后，最开始缓存的数据会被删除
    @Test
    public void test1(){
        Cache<String,String> cache = CacheBuilder.newBuilder()
                .maximumSize(2)
                .build();
        cache.put("key1","value1");
        cache.put("key2","value2");
        cache.put("key3","value3");
        System.out.println("第一个值：" + cache.getIfPresent("key1"));
        System.out.println("第二个值：" + cache.getIfPresent("key2"));
        System.out.println("第三个值：" + cache.getIfPresent("key3"));
    }

    // 设置过期时间，这种情况是存入数据三秒后自动过期
    @Test
    public void test2(){
        Cache<String,String> cache = CacheBuilder.newBuilder()
                .maximumSize(2)
                .expireAfterWrite(3,TimeUnit.SECONDS)
                .build();
        cache.put("key1","value1");
        int time = 1;
        while(true) {
            System.out.println("第" + time++ + "次取到key1的值为：" + cache.getIfPresent("key1"));
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    // 设置三秒没有用户访问自动过期
    @Test
    public void test3(){
        Cache<String,String> cache = CacheBuilder.newBuilder()
                .expireAfterAccess(3,TimeUnit.SECONDS)
                .maximumSize(2)
                .build();
        cache.put("key2","value2");
        int count = 1;
        while (true){
            System.out.println("第" + count + "秒后取到key2的值为：" + cache.getIfPresent("key2"));
            try {
                Thread.sleep(count*1000);
                count++;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    // 测试缓存中的弱引用，缓存中的数据一旦被重新赋值，那么缓存中的值就被重置了
    @Test
    public void test4(){
        Cache<String,Object> cache = CacheBuilder.newBuilder()
                .maximumSize(2)
//                .weakValues()  ，如果不设置弱引用，那么缓存中的数据不会被重置
                .build();
        Object value = new Object();
        cache.put("key1",value);

        value = new Object();//原对象不再有强引用
        System.gc();
        System.out.println(cache.getIfPresent("key1"));
    }

    // 删除缓存中的数据，使用invalidate()方法是删除单条数据，使用invalidateAll()方法是删除所有数据，可以接受实现了Iterable接口的集合类型，也可以不传入参数
    @Test
    public void test5(){
        Cache<String,String> cache = CacheBuilder.newBuilder().build();
        Object value = new Object();
        cache.put("key1","value1");
        cache.put("key2","value2");
        cache.put("key3","value3");

        List<String> list = new ArrayList<String>();
//        HashMap<Object, Object> map = Maps.newHashMap();
//        HashSet<Object> set = Sets.newHashSet();
        list.add("key1");
        list.add("key2");

        cache.invalidateAll(list);//批量清除list中全部key对应的记录
        System.out.println(cache.getIfPresent("key1"));
        System.out.println(cache.getIfPresent("key2"));
        System.out.println(cache.getIfPresent("key3"));
    }

    // 移除监听器，可以感知缓存中数据删除的动作
    @Test
    public void test6(){
        RemovalListener<String, String> listener = new RemovalListener<String, String>() {
            public void onRemoval(RemovalNotification<String, String> notification) {
                System.out.println("[" + notification.getKey() + ":" + notification.getValue() + "] is removed!");
            }
        };
        Cache<String,String> cache = CacheBuilder.newBuilder()
                .maximumSize(3)
                .removalListener(listener)
                .build();
        Object value = new Object();
        cache.put("key1","value1");
        cache.put("key2","value2");
        cache.put("key3","value3");
        cache.put("key4","value3");
        cache.put("key5","value3");
        cache.put("key6","value3");
        cache.put("key7","value3");
        cache.put("key8","value3");
    }


    // 测试自动加载，从结果中可以看出，虽然是两个线程同时调用get方法，但只有一个get方法中的Callable会被执行(没有打印出load2)。
    // Guava可以保证当有多个线程同时访问Cache中的一个key时，如果key对应的记录不存在，Guava只会启动一个线程执行get方法中Callable参数对应的任务加载数据存到缓存。
    // 当加载完数据后，任何线程中的get方法都会获取到key对应的值。
    public static void main(String[] args) {
        Cache<String,String> cache = CacheBuilder.newBuilder()
                .maximumSize(3)
                .build();

        new Thread(new Runnable() {
            public void run() {
                System.out.println("thread1");
                try {
                    String value = cache.get("key", new Callable<String>() {
                        public String call() throws Exception {
                            System.out.println("load1"); //加载数据线程执行标志
                            Thread.sleep(1000); //模拟加载时间
                            return "auto load by Callable";
                        }
                    });
                    System.out.println("thread1 " + value);
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(new Runnable() {
            public void run() {
                System.out.println("thread2");
                try {
                    String value = cache.get("key", new Callable<String>() {
                        public String call() throws Exception {
                            System.out.println("load2"); //加载数据线程执行标志
                            Thread.sleep(1000); //模拟加载时间
                            return "auto load by Callable";
                        }
                    });
                    System.out.println("thread2 " + value);
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    // 测试cache方法中的统计开关，可以对各种缓存操作进行统计
    @Test
    public void test7(){
        Cache<String,String> cache = CacheBuilder.newBuilder()
                .maximumSize(3)
                .recordStats() //开启统计信息开关
                .build();
        cache.put("key1","value1");
        cache.put("key2","value2");
        cache.put("key3","value3");
        cache.put("key4","value4");

        cache.getIfPresent("key1");
        cache.getIfPresent("key2");
        cache.getIfPresent("key3");
        cache.getIfPresent("key4");
        cache.getIfPresent("key5");
        cache.getIfPresent("key6");

        System.out.println(cache.stats()); //获取统计信息
    }

    /**
     * 与构建Cache类型的对象类似，LoadingCache类型的对象也是通过CacheBuilder进行构建，不同的是，在调用CacheBuilder的build方法时，
     * 必须传递一个CacheLoader类型的参数，CacheLoader的load方法需要我们提供实现。
     * 当调用LoadingCache的get方法时，如果缓存不存在对应key的记录，则CacheLoader中的load方法会被自动调用从外存加载数据，
     * load方法的返回值会作为key对应的value存储到LoadingCache中，并从get方法返回。
     */
    @Test
    public void test8(){
        CacheLoader<String, String> loader = new CacheLoader<String, String> () {
            public String load(String key) throws Exception {
                Thread.sleep(1000); //休眠1s，模拟加载数据
                System.out.println(key + " is loaded from a cacheLoader!");
                return key + "'s value";
            }
        };

        LoadingCache<String,String> loadingCache = CacheBuilder.newBuilder()
                .maximumSize(3)
                .build(loader);//在构建时指定自动加载器
        try {
            loadingCache.get("key1");
            loadingCache.get("key2");
            loadingCache.get("key3");
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void cacheTest(){
        // LoadingCache是Cache的子类对象，它创建Cache时build方法中必须传入一个CacheLoader对象
        LoadingCache<String, Student>  cache = CacheBuilder.newBuilder()
                // 设置缓存的最大数量
                .maximumSize(3)
                // 指定数据的过期时间：expireAfterWrite方法指定对象被写入到缓存后多久过期，expireAfterAccess指定对象多久没有被访问后过期，两者可同时指定
                .expireAfterWrite(10, TimeUnit.SECONDS)
                // 统计缓存的命中率
                .recordStats()
                // 缓存被移除时收到通知(移除监听器)
                .removalListener(new RemovalListener<Object, Object>() {
                    @Override
                    public void onRemoval(RemovalNotification<Object, Object> notification) {
                        System.out.println(notification.getKey() + " was removed, cause is " + notification.getCause());
                    }
                })
                //build方法中指定CacheLoader，在缓存不存在时通过CacheLoader的实现自动加载缓存
                .build(new CacheLoader<String, Student>() {
                    @Override
                    public Student load(String key) throws Exception {
                        return createStudentByKey(key);
                    }
                });
        try {
            Student student1 = cache.get("张三");
            Student student2 = cache.get("李四");
            Student student3 = cache.get("王五");
            cache.put("赵六",new Student("赵六",25));
            System.out.println(cache.get("张三"));
            System.out.println(cache.get("李四"));
            System.out.println(cache.get("王五"));
            System.out.println(cache.get("赵六"));
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }

    private Student createStudentByKey(String key){
        HashMap<Object, Object> map = Maps.newHashMap();
        return new Student(key,20);
    }

    @Test
    public void timeUnitTest(){
        // 将后者的时间单位转化为前者，将10分钟转化为多少秒
        long during = TimeUnit.SECONDS.convert(10, TimeUnit.MINUTES);
        System.out.println(during);
        // 将10分钟转化为多少秒
        long seconds = TimeUnit.MINUTES.toSeconds(10);
        System.out.println(seconds);
    }
}
