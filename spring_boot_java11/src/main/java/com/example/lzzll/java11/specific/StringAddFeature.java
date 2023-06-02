package com.example.lzzll.java11.specific;

/**
 * @Author lf
 * @Date 2023/6/1 14:08
 * @Description: String类新增的api
 */
public class StringAddFeature {

    /**
     * isBlank() strip() repeat()
     * @param args
     */
    public static void main(String[] args) {
        String str = "  the feature of string  ";
//        System.out.println("  ".isBlank());  // true
//        System.out.println("  ".isEmpty());  // false
//        System.out.println( str.strip());         // 去除空格
//        System.out.println( str.stripLeading());  // 去除左空格
//        System.out.println( str.stripTrailing() ); // 去除右空格
//        System.out.println( str.repeat(2));  // 复制字符串特定次数

        String lineStr = "{\n" +
                "\t\t\t\t\"id\": 19049092,\n" +
                "\t\t\t\t\"taskId\": 2061343,\n" +
                "\t\t\t\t\"sourceId\": 8675596,\n" +
                "\t\t\t\t\"taskState\": 30,\n" +
                "\t\t\t\t\"logCode\": \"013\",\n" +
                "\t\t\t\t\"logSummery\": \"领取任务\",\n" +
                "\t\t\t\t\"createrGuid\": 121032612,\n" +
                "\t\t\t\t\"createTime\": \"2023-05-31 20:27:39\",\n" +
                "\t\t\t\t\"roleName\": \"审校-标定员\",\n" +
                "\t\t\t\t\"ipAddr\": \"117.152.223.137\",\n" +
                "\t\t\t\t\"isProcess\": 1\n" +
                "\t\t\t}";
        str.lines().forEach(System.out::println);
        lineStr.lines().forEach(System.out::println);   // 获取字符串的不同行组成的字符串
    }


}
