package com.example.lzzll.java11.specific;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @Author lf
 * @Date 2023/6/5 14:35
 * @Description: 文件流的增强方法
 */
public class InputStreamAddFeature {

    public static void main(String[] args) {
        try {
            String inputPath = "\\alidata1\\tomcat\\logs\\english.txt";
            String outputPath = "\\alidata1\\tomcat\\logs\\test.txt";
            FileInputStream fileInputStream = new FileInputStream(inputPath);
            FileOutputStream fileOutputStream = new FileOutputStream(outputPath);
            // 直接将文件输入流写入文件输出流
            fileInputStream.transferTo(fileOutputStream);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
