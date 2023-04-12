package com.example.lzzll.javastudy.getdocument;

import java.io.File;
// import org.apache.poi.hwpf.extractor.WordExtractor;

/**
 * @Author lf
 * @Date 2019/9/4 16:53
 * @Description:
 */
// 获取word文件中的内容
public class ReadWord {

    public static void main(String[] args) {
        String filePath = "E:\\study\\开发文档\\linux系统命令大全\\linux系统中安装软件";
        File file = new File(filePath);
//        readWord2003(file);

    }

    /**
     * 支持word-2003
     *
     * @param args
     */
   /* private static void readWord2003(File file) {
        try {
            FileInputStream fis = new FileInputStream(file);
            // 创建WordExtractor对象
            WordExtractor wordExtractor = new WordExtractor(fis);
            // 取得所有文本内容
            String text = wordExtractor.getText();
            System.out.println("readWord2003--------------->"+text);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 支持word-2003
    public static void readWordExtractor(File file) {
        try {
            FileInputStream fis = new FileInputStream(file);
            // 创建WordExtractor对象
            WordExtractor wordExtractor = new WordExtractor(fis);
            // 通过getParagraphText()提取每个段落
            String[] paragraph = wordExtractor.getParagraphText();
            System.out.println("该Word文件共有" + paragraph.length + "段。");


            for (int i = 0; i < paragraph.length; i++) {
                System.out.println("readWordExtractor--------------->"+paragraph[i]);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    *//**
     * word 2007解决方案
     *
     * @param
     *//*
    public static void readWord2007(File file) {
        try {
            // word 2007,读取word中字符
            OPCPackage opcPackage = POIXMLDocument.openPackage("D:\\apache-tomcat-6.0.18\\webapps\\GOVWBWeb\\upload\\user\\2014\\04\\18\\08\\193b299f-e8fc-4a32-a7ba-f951beeec1d9");
            POIXMLTextExtractor extractor = new XWPFWordExtractor(opcPackage);
            String text2007 = extractor.getText();
            System.out.println("readWord2007--------------->"+text2007);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/

}

