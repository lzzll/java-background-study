package com.example.lzzll.javastudy.redis;//package com.example.lzzll.redis;
//
//import org.junit.Test;
//import redis.clients.jedis.Jedis;
//import redis.clients.jedis.Tuple;
//
//import java.util.List;
//import java.util.Map;
//import java.util.Set;
//
///**
// * jedis使用的测试类
// *      使用步骤:
// *          a.导包(两个包commons-pool2-2.3.jar、jedis-2.7.0.jar)
// *          b.创建连接对象,直接new出来即可,参数为连接电脑的ip地址和端口号,如果不传参数,默认为localhost和6379
// *                  Jedis jedis = new Jedis()
// *          c.直接使用jedis的相关方法进行值的存储
// *          d.关闭连接 jedis.close()
// *
// *      注意:
// *          a.在redis中存储中文字符会出现乱码,在jedis中存储了中文了之后,在redis中看到的是十六进制值,获取这个值之后,jedis会自动将十六进制值解析为中文.
// *
// *
// */
//
//public class JedisTest {
//
//    // 测试存取字符串类型的值
//    @Test
//    public void test1() {
//        // 方式一：直接创建连接对象
////        Jedis jedis = new Jedis("localhost", 6379);
//        // 方式二：通过JedisUtils工具类来获取jedis对象
//        Jedis jedis = JedisUtils.getJedis();
//        // 使用jedis方法进行值的存储和获取
//        String set = jedis.set("name", "风云");  // 存储中文在redis黑窗口中看会出现乱码
//        System.out.println(set);   // ok(无论是替换还是新增,操作成功的返回值都是ok)
//        String name = jedis.get("name");  // 取出值后会自动解析为中文
//        System.out.println(name);
//        // 删除所有的值(通用方法)
//        jedis.del("name");
//        // 关闭连接
//        jedis.close();
//    }
//
//    // 测试存取Hash类型的值
//    @Test
//    public void test2() {
//        // 创建连接对象
//        Jedis jedis = new Jedis("localhost", 6379);
//        // 存取hash类型的数据
//        Long hset1 = jedis.hset("火影忍者", "木叶", "十二小强");
//        jedis.hset("火影忍者", "云之国", "奇拉比");
//        System.out.println(hset1);   // 1 (如果是新增返回值就是1,如果是替代,返回值就是0)
//        String value1 = jedis.hget("火影忍者", "木叶");
//        System.out.println(value1);  // 十二小强
//        // 获取所有的元素
//        Map<String, String> map = jedis.hgetAll("火影忍者");
//        Set<Map.Entry<String, String>> entries = map.entrySet();
//        for (Map.Entry<String, String> entry : entries) {
//            System.out.println(entry.getKey() + "--" + entry.getValue());
//        }
//        // 删除单个数据
//        jedis.hdel("火影忍者", "云之国");
//        // 删除火影忍者对应的数据(通用方法)
//        Long del = jedis.del("火影忍者");
//        System.out.println(del);
//
//        // 关闭连接
//        jedis.close();
//    }
//
//    // 测试存取list类型的数据(链表结构,数据有序,可以重复)
//    @Test
//    public void test3() {
//        // 创建连接对象
//        Jedis jedis = new Jedis("localhost", 6379);
//        // 使用jedis方法进行值的存储和获取
//        jedis.lpush("number", "1", "2", "3");   // 加入队头
//        jedis.rpush("number", "1", "2", "3");   // 加入队尾
//        // 查询所有的值
//        List<String> number = jedis.lrange("number", 0, -1);
//        System.out.println(number);
//        // 逐个删除值
//        jedis.lpop("number");
//        jedis.rpop("number");
//        List<String> number1 = jedis.lrange("number", 0, -1);
//        System.out.println(number1);
//        // 删除所有值(通用方法)
//        jedis.del("number");
//
//        // 关闭连接
//        jedis.close();
//    }
//
//    // 测试存取set类型的数据(链表结构,数据无序,不允许重复)
//    @Test
//    public void test4() {
//        // 创建连接对象
//        Jedis jedis = new Jedis("localhost", 6379);
//        // 使用jedis方法进行值的存储和获取
//        jedis.sadd("number", "1", "2", "3", "1", "4");
//        // 查询所有的值
//        Set<String> number = jedis.smembers("number");
//        System.out.println(number);
//        // 删除单个值
//        jedis.srem("number", "2");
//        Set<String> number1 = jedis.smembers("number");
//        System.out.println(number1);
//        // 删除所有值(通用方法)
//        jedis.del("number");
//
//        // 关闭连接
//        jedis.close();
//    }
//
//    // 测试存取sortedSet类型的数据(链表结构,数据有序,不允许重复,可以通过score字段对数据进行排序)
//    @Test
//    public void test5() {
//        // 创建连接对象
//        Jedis jedis = new Jedis("localhost", 6379);
//        // 使用jedis方法进行值的存储和获取
//        jedis.zadd("hero", 500, "黑足山治");
//        jedis.zadd("hero", 50, "乔巴");
//        jedis.zadd("hero", 800, "剑豪索隆");
//        jedis.zadd("hero", 300, "贼猫娜美");
//        jedis.zadd("hero", 600, "狙击王");
//        jedis.zadd("hero", 1000, "海贼王");
//        // 按score从低到高排序
//        Set<String> set = jedis.zrange("hero", 0, -1);
//        System.out.println(set);
//        // 按score从高到低排序(附带上score值)
//        Set<Tuple> tuples = jedis.zrevrangeByScoreWithScores("hero", 1000, 50);    // 参数为score的最大值和最小值
//        for (Tuple tuple : tuples) {
//            System.out.println(tuple.getElement() + "-->" + tuple.getScore());
//        }
//
//        // 删除所有值
//        jedis.del("hero");
//
//        // 关闭连接
//        jedis.close();
//    }
//
//
//    @Test
//    public void testArray() {
//        int[] array = {0, 1, 2, 3};
//        System.out.println(array.length);
////        Long num = 485;
////        int num1 = 4L;
////        float num = 1.1;
//        double num = 1.1;
//        System.out.println(num);
//        Thread thread = new Thread();
//        thread.stop();
//    }
//
//    @Test
//    public void Test()  {
//        int i = 5;
//        do {
//            System.out.print(i);
//        } while (i > 5);
//        {
//            System.out.println("finished");
//        }
//
//    }
//}
