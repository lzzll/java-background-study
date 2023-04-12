package com.example.lzzll.javastudy.jsoup.example;//package com.example.lzzll.jsoup.example;
//
//
//import org.jsoup.Jsoup;
//import org.jsoup.nodes.Document;
//import org.jsoup.nodes.Node;
//import org.jsoup.select.Elements;
//
//import java.io.File;
//import java.io.IOException;
//
///**
// * 获取c3p0配置文件中的各个字段的属性值,包括jdbc驱动的路径名称,数据库资源路径,数据库账户和数据库密码
// */
//
//public class Demo02_C3P0 {
//    public static void main(String[] args) throws IOException {
//        // a.加载配置文件进内存
//        String path = ClassLoader.getSystemResource("c3p0-config.xml").getPath();
//        Document document = Jsoup.parse(new File(path), "utf-8");
//        // 获取元素对象(default-config)
//        Elements elements = document.getElementsByTag("default-config");
////        System.out.println(elements.size());
////        System.out.println(elements);
//        // 根据元素对象调用getElementByAttributeValue的方法,获取对应字段的值(方法一)
//        String name = elements.get(0).getElementsByAttributeValue("name", "driverClass").get(0).text();
//        System.out.println(name);
//        String url = elements.get(0).getElementsByAttributeValue("name", "jdbcUrl").get(0).text();
//        System.out.println(url);
//        String user = elements.get(0).getElementsByAttributeValue("name", "user").get(0).text();
//        System.out.println(user);
//        String password = elements.get(0).getElementsByAttributeValue("name", "password").get(0).text();
//        System.out.println(password);
//        System.out.println("-----------------");
//        // 直接获取元素对象的文本,然后对所获取的字符串进行切割(方法二)
//        String text = elements.get(0).getElementsByTag("property").text();
//        System.out.println(text);
//        // 切割字符串
//        String[] textArr = text.split(" ");
//        for (String s : textArr) {
//            System.out.println(s);
//        }
//    }
//}
