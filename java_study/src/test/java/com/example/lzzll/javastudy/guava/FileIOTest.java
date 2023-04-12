package com.example.lzzll.javastudy.guava;

import com.google.common.base.Charsets;
import com.google.common.io.Files;
import org.junit.Test;

import java.io.*;
import java.util.List;

/**
 * @Author lf
 * @Date 2019/9/25 11:31
 * @Description:
 */
public class FileIOTest {

    // 传统方法获取文件中的内容
    @Test
    public void oldMethod() {
        BufferedReader br = null;
        try {
            String text = "";
            // 创建file的方法一，硬编码将路径写死
            String filePath = "C:\\Users\\lf\\Desktop\\test\\file1.txt";
            File file = new File(filePath);
            br = new BufferedReader(new FileReader(file));
            String line = null;
            while ((line = br.readLine())!=null ){
                text += line.trim()+"\n";
            }
            System.out.println(text);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (br != null){
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    // 使用guava类中的新方法获取数据,Files工具类
    @Test
    public void newMethod(){
        // 创建file的方法二
//            File file1 = new File(getClass().getResource(filePath).getFile());
//        String filePath = getClass().getResource("/file1.txt").getFile();  // 使用相对路径进行编写，直接将文件放入resources目录下
        String filePath = getClass().getResource("/file/file2.txt").getFile();  // 将文件放入resources目录下的file文件夹下
        File file = new File(filePath);
        System.out.println(filePath);
        List<String> lines = null;
        try {
            lines = Files.readLines(file, Charsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(lines);
    }



}
