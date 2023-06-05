package com.example.lzzll.java11.specific;

import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * @Author lf
 * @Date 2023/6/2 15:22
 * @Description: 文件类的功能增强
 */
@Slf4j
public class FileAddFeature {

    public static void main(String[] args) {
        try {
            // 文件编码为GB2312(简体中文)
//            String absoluteReadPath = "C:\\Users\\lf\\Desktop\\detail.txt";
            // 文件编码为UTF-8
            String absoluteReadPath = "C:\\Users\\lf\\Desktop\\english.txt";
            String relateReadPath = "\\alidata1\\tomcat\\logs\\english.txt";
            // 音频格式无法读取字符串
            String relateReadPathMp3 = "\\alidata1\\tomcat\\logs\\1.mp3";
            String absoluteWritePath = "C:\\Users\\lf\\Desktop\\test.txt";
            String relateWritePath = "\\alidata1\\tomcat\\logs\\test.txt";
            // java8中读取文件字符串
//            String readStr = readFileWithJava8(absoluteReadPath);
            // java11中读取文件字符串
//            String readStr = readFileWithJava11(absoluteReadPath);
            // 读取相对路径文件(txt文件)
//            String readStr = readFileWithJava11(relateReadPath);
            // 读取其它格式文件会报格式不匹配的错误
//            String readStr = readFileWithJava11(relateReadPathMp3);

            // java8中将字符串写入文件中,绝对路径
//            writeFileWithJava8("11111111111111这是随意写入的一段文件22222222222",absoluteWritePath,"UTF-8");
            // 相对路径
//            writeFileWithJava8("11111111111111这是随意写入的一段文件22222222222",relateWritePath,"UTF-8");

            // java11将字符串写入文件
            Files.writeString( Paths.get(absoluteWritePath), "this is the day", StandardCharsets.UTF_8 );

        } catch (Exception e) {
            e.printStackTrace();
            log.error("操作错误",e);
            throw new RuntimeException(e);
        }

    }

    /**
     * java8中将指定内容写入文件
     * @param content
     * @param absoluteReadPath
     * @param charsetName 编码格式
     */
    private static void writeFileWithJava8(String content, String absoluteReadPath, String charsetName) {
        // 方式一、通过bufferWriter将内容写入文件
//        FileOutputStream fileOutputStream = null;
//        OutputStreamWriter outputStreamWriter =null;
//        BufferedWriter bufferedWriter = null;
//        try {
//            fileOutputStream = new FileOutputStream(new File(absoluteReadPath));
//            outputStreamWriter = new OutputStreamWriter(fileOutputStream);
//            bufferedWriter = new BufferedWriter(outputStreamWriter);
//            bufferedWriter.write(content);
//        } catch (FileNotFoundException e) {
//            throw new RuntimeException(e);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }finally {
//            if (bufferedWriter != null){
//                try {
//                    bufferedWriter.close();
//                } catch (IOException e) {
//                    throw new RuntimeException(e);
//                }
//            }
//            if (outputStreamWriter != null){
//                try {
//                    outputStreamWriter.close();
//                } catch (IOException e) {
//                    throw new RuntimeException(e);
//                }
//            }
//            if (fileOutputStream != null){
//                try {
//                    fileOutputStream.close();
//                } catch (IOException e) {
//                    throw new RuntimeException(e);
//                }
//            }
//        }

        // 方式二、通过bufferWriter的简便写法写入
        try {
            Path path = Paths.get(absoluteReadPath);
            try (BufferedWriter writer = Files.newBufferedWriter(path,StandardCharsets.UTF_8)) {
                writer.write(content);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        // 方式三、通过Files.write()方法写入
//        try {
//            Files.write(Paths.get(absoluteReadPath),content.getBytes(StandardCharsets.UTF_8));
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }


    }

    /**
     * java11中读取文件字符串
     * @param absoluteReadPath
     * @return
     */
    private static String readFileWithJava11(String absoluteReadPath) {
        String result = null;
        try {
            Path path = Paths.get(absoluteReadPath);
            // 在jdk11中使用此方法，文件的编码和设置的编码必须一致，否则会报错
            result = Files.readString(path, StandardCharsets.UTF_8);

            // 读取所有的文件行
//      List<String> lines = Files.readAllLines(path);
//      result = String.join(System.lineSeparator(),lines);
            System.out.println(result);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return result;
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
            // 02.将 fileInputStream（字节流） 流作为参数，转为InputStreamReader（字符流）。
            // 文件编码和设置的编码必须保持一致，否则读取文件内容的时候会报错
            inputStreamReader = new InputStreamReader(fileInputStream, "UTF-8");
//            inputStreamReader = new InputStreamReader(fileInputStream, "GBK");
            // 03.将 字符流（参数）转为字符串流，带缓冲的流读取，默认缓冲区8k
            bufferedReader = new BufferedReader(inputStreamReader);
            String tempString;
            while ((tempString = bufferedReader.readLine()) != null) {// 直接返回读取的字符串
                // 将字符串 添加到 stringBuilder中
                stringBuilder.append(tempString);
                // 换行
                stringBuilder.append(System.getProperty("line.separator"));
            }

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
        System.out.println(stringBuilder.toString());
        return stringBuilder.toString();
    }


}
