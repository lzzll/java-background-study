package com.example.lzzll.javastudy.jsoup.example;//package com.example.lzzll.jsoup.example;
//
//
////import cn.wanghaomiao.xpath.exception.XpathSyntaxErrorException;
////import cn.wanghaomiao.xpath.model.JXDocument;
////import cn.wanghaomiao.xpath.model.JXNode;
//import org.jsoup.Jsoup;
//import org.jsoup.nodes.Document;
//import org.jsoup.select.Elements;
//
//import java.io.File;
//import java.io.IOException;
//import java.io.InputStream;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.Statement;
//
///**
// * 要求:
// *      获取c3p0中的驱动路径,数据库路径,数据库账户和数据库密码,对数据库进行查询操作
// */
//
//public class JsoupDemo02 {
//    public static void main(String[] args) throws Exception{
//        // 获取c3p0中的驱动路径,数据库路径,数据库账户和数据库密码,对数据库进行查询操作
//        String path = ClassLoader.getSystemResource("c3p0-config.xml").getPath();
//        Document document = Jsoup.parse(new File(path), "utf-8");
////        JXDocument jxDocument = new JXDocument(document);
////        JXNode jxNode = jxDocument.selN("//property[@name='driverClass']").get(0);
////        String driverClass = jxNode.getElement().text();
////        String jdbcUrl = jxDocument.selN("//property[@name='jdbcUrl']").get(0).getElement().text();
////        String user = jxDocument.selN("//property[@name='user']").get(0).getElement().text();
////        String password = jxDocument.selN("//property[@name='password']").get(0).getElement().text();
//        // 加载数据库驱动
////        Class.forName(driverClass);
//        // 获取连接对象
////        Connection connection = DriverManager.getConnection(jdbcUrl, user, password);
//        // 获取执行对象
////        Statement stat = connection.createStatement();
//        // 定义sql语句
////        String sql = "UPDATE student SET chinese = 100 WHERE NAME = '无名';";
////        int count = stat.executeUpdate(sql);
////        System.out.println(count);
//
//        // 释放资源
////        stat.close();
////        connection.close();
//
//
//    }
//}
