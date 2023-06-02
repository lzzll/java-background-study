package com.example.lzzll.java11.specific;

import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @Author lf
 * @Date 2023/6/2 15:22
 * @Description: 文件类的功能增强
 */
@Slf4j
public class FileAddFeature {

    public static void main(String[] args) {
        try {
            String absoluteReadPath = "C:\\Users\\lf\\Desktop\\detail.txt";
            String relateReadPath = "\\canpointnas\\cpcloud\\tmpfiles\\1\\5.mp3";
            String absoluteWritePath = "C:\\Users\\lf\\Desktop\\test.txt";
            String relateWritePath = "\\canpointnas\\cpcloud\\tmpfiles\\";
            // java8中读取文件字符串
            String readStr = readFileWithJava8(absoluteReadPath);
            System.out.println(readStr);


//            Path path = Paths.get(absoluteReadPath);
//            String content = Files.readString(path, StandardCharsets.UTF_8);
//            System.out.println(content);

//            Files.writeString( path, "王老七", StandardCharsets.UTF_8 );
        } catch (Exception e) {
            log.error("操作错误",e);
            throw new RuntimeException(e);
        }

    }

    /**
     * 读取文件
     * @param filePath
     */
    public static String readFileWithJava8(String filePath) {
        StringBuilder stringBuilder = new StringBuilder();
        FileInputStream fileInputStream = null;
        InputStreamReader inputStreamReader = null;
        BufferedReader bufferedReader = null;
        try {
            fileInputStream = new FileInputStream(new File(filePath));
            // 02.将 fileInputStream（字节流） 流作为参数，转为InputStreamReader（字符流）
//            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, "UTF-8");
            // 文本中有中文需要使用GBK编码
            inputStreamReader = new InputStreamReader(fileInputStream, "GBK");
            // 03.将 字符流（参数）转为字符串流，带缓冲的流读取，默认缓冲区8k
            bufferedReader = new BufferedReader(inputStreamReader);
            String tempString;
            while ((tempString = bufferedReader.readLine()) != null) {// 直接返回读取的字符串
                // 将字符串 添加到 stringBuilder中
                stringBuilder.append(tempString);
                // 换行
                stringBuilder.append(System.getProperty("line.separator"));
            }
            bufferedReader.close();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }finally {
            if (bufferedReader != null){
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            if (inputStreamReader != null){
                try {
                    inputStreamReader.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            if (fileInputStream != null){
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return stringBuilder.toString();
    }


}
