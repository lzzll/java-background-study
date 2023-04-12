package com.example.lzzll.javastudy.redis;//package com.example.lzzll.redis;
//
//import redis.clients.jedis.Jedis;
//import redis.clients.jedis.JedisPool;
//import redis.clients.jedis.JedisPoolConfig;
//
//import java.io.IOException;
//import java.io.InputStream;
//import java.util.Properties;
//
///**
// * 抽取一个获取jedis的工具类
// */
//
//public class JedisUtils {
//
//    // 定义一个静态私有化的jedis连接池对象
//    private static JedisPool jedisPool;
//    private static InputStream is;
//
//    // 定义一个静态代码块,加载配置文件
//    static {
//        try {
//            Properties pro = new Properties();
//            is = JedisUtils.class.getClassLoader().getResourceAsStream("jedis.properties");
//            pro.load(is);
//            // 创建一个Jedis连接池的配置对象,并设置相应的属性
//            JedisPoolConfig config = new JedisPoolConfig();
//            config.setMaxTotal(Integer.parseInt(pro.getProperty("maxTotal")));
//            config.setMaxIdle(Integer.parseInt(pro.getProperty("maxIdle")));
//            // 创建一个jedis对象
//            jedisPool = new JedisPool(config,pro.getProperty("host"),Integer.parseInt(pro.getProperty("port")));
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            // 释放资源
//            try {
//                is.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
//    // 定义一个静态方法,获取jedis对象
//    public static Jedis getJedis() {
//        return jedisPool.getResource();
//    }
//}
