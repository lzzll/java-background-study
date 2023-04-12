package com.example.lzzll.javastudy.jsoup.example;//package com.example.lzzll.jsoup.example;
//
//
//import org.jsoup.Jsoup;
//import org.jsoup.nodes.Document;
//import org.jsoup.select.Elements;
//
//import java.io.File;
//import java.io.IOException;
//
///**
// * Jsoup解析器下的类选择器:
// * 获取c3p0配置文件中的各个字段的属性值,包括jdbc驱动的路径名称,数据库资源路径,数据库账户和数据库密码
// */
//
//public class JsoupSelector {
//    public static void main(String[] args) throws IOException {
//        // a.加载配置文件进内存
//        String path = ClassLoader.getSystemResource("c3p0-config.xml").getPath();
//        Document document = Jsoup.parse(new File(path), "utf-8");
//        // 获取类名称
//        Elements ele = document.select("property[name=\"driverClass\"]");
//        String driverClass = ele.get(0).text();
//        System.out.println(driverClass);
//        Elements ele2 = document.select("property[name=\"jdbcUrl\"]");
//        String jdbcUrl = ele2.get(0).text();
//        System.out.println(jdbcUrl);
//        Elements ele3 = document.select("property[name=\"user\"]");
//        String user = ele3.get(0).text();
//        System.out.println(user);
//        Elements ele4 = document.select("property[name=\"password\"]");
//        String password = ele4.get(0).text();
//        System.out.println(password);
//    }
//}
