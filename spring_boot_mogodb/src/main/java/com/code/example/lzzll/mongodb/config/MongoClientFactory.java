package com.code.example.lzzll.mongodb.config;

import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoDatabase;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author lf
 * @Date 2020/9/10 11:37
 * @Description: mongoDb的配置类
 */
@Component
public class MongoClientFactory {

    private static final String MONGO_IP = "127.0.0.1";
    private static final Integer MONGO_PORT = 27017;

    private static MongoClient mongoClient = null;

    /**
     * 静态代码块加载mongoDb的客户端连接
     */
//    static {
//        //连接到MongoDB服务 如果是远程连接可以替换“localhost”为服务器所在IP地址
//        //ServerAddress()两个参数分别为 服务器地址 和 端口
//        ServerAddress serverAddress = new ServerAddress(MONGO_IP,MONGO_PORT);
//        List<ServerAddress> addrs = new ArrayList<ServerAddress>();
//        addrs.add(serverAddress);
//
//        //MongoCredential.createScramSha1Credential()三个参数分别为 用户名 数据库名称 密码
//        MongoCredential credential = MongoCredential.createScramSha1Credential("username", "databaseName", "password".toCharArray());
//        List<MongoCredential> credentials = new ArrayList<MongoCredential>();
//        credentials.add(credential);
//
//        //通过连接认证获取MongoDB连接
//        mongoClient = new MongoClient(addrs,credentials);
//
//        //连接到数据库
//        MongoDatabase mongoDatabase = mongoClient.getDatabase("databaseName");
//        System.out.println("Connect to database successfully");
//
//
//    }

    /**
     * 通过方法获取mongoDb数据库的连接
     * @return
     */
//    private static MongoClient initClient(){
//        //连接到MongoDB服务 如果是远程连接可以替换“localhost”为服务器所在IP地址
//        //ServerAddress()两个参数分别为 服务器地址 和 端口
//        ServerAddress serverAddress = new ServerAddress(MONGO_IP,MONGO_PORT);
//        List<ServerAddress> addrs = new ArrayList<ServerAddress>();
//        addrs.add(serverAddress);
//
//        //MongoCredential.createScramSha1Credential()三个参数分别为 用户名 数据库名称 密码
//        MongoCredential credential = MongoCredential.createScramSha1Credential("username", "databaseName", "password".toCharArray());
//        List<MongoCredential> credentials = new ArrayList<MongoCredential>();
//        credentials.add(credential);
//
//        //通过连接认证获取MongoDB连接
//        mongoClient = new MongoClient(addrs,credentials);
//        return mongoClient;
//
//    }




}
