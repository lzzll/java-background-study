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
// * Jsoup解析器的使用步骤:
// *      a.导包;
// *      b.获取document对象,通过parse(File , character);方法
// *      c.根据document对象获取指定的元素对象,通过getElementByXXX();方法
// *      d.操作获取的元素对象;
// */
//
//public class JsoupDemo01 {
//    public static void main(String[] args) throws IOException {
//
//        // a.获取document对象
//        // 1.获取文件的绝对路径
//        String path = ClassLoader.getSystemResource("student.xml").getPath();
//        // 2.加载文件进内存,获取document对象
//        Document document = Jsoup.parse(new File(path), "utf-8");
////         3.获取student标签对象
//        Elements elements = document.getElementsByTag("student");
//        System.out.println(elements);
//
//    }
//}
