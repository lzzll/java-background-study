package com.example.lzzll.javastudy.jsoup.example;//package com.example.lzzll.jsoup.example;
//
//
//import cn.wanghaomiao.xpath.exception.XpathSyntaxErrorException;
//import cn.wanghaomiao.xpath.model.JXDocument;
//import cn.wanghaomiao.xpath.model.JXNode;
//import org.jsoup.Jsoup;
//import org.jsoup.nodes.Document;
//
//import java.io.File;
//import java.io.IOException;
//import java.util.List;
//
///**
// * 使用Xpath选择器完成需求
// *  获取c3p0配置文件中的各个字段的属性值,包括jdbc驱动的路径名称,数据库资源路径,数据库账户和数据库密码
// */
//
//public class JsoupXPath {
//    public static void main(String[] args) throws IOException, XpathSyntaxErrorException {
//        // 加载配置文件进内存,获取DOM树对象document
//        String path = JsoupXPath.class.getClassLoader().getResource("c3p0-config.xml").getPath();
//        Document document = Jsoup.parse(new File(path), "utf-8");
//        // 根据document对象,获取JXdocument对象
//        JXDocument jxDocument = new JXDocument(document);
//        // 集合XPath的语法进行查询
//        List<JXNode> jxNodes = jxDocument.selN("//property[@name='driverClass']");
//        String driverClass = jxNodes.get(0).getElement().text();
//        System.out.println(driverClass);
//        String jdbcUrl = jxDocument.selN("//property[@name='jdbcUrl']").get(0).getElement().text();
//        System.out.println(jdbcUrl);
//        String user = jxDocument.selN("//property[@name='user']").get(0).getElement().text();
//        System.out.println(user);
//        String password = jxDocument.selN("//property[@name='password']").get(0).getElement().text();
//        System.out.println(password);
//    }
//}
//
