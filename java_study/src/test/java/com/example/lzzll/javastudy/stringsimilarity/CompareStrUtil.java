package com.example.lzzll.javastudy.stringsimilarity;

import org.junit.Test;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author lf
 * @Date 2019/9/5 17:04
 * @Description:
 */
public class CompareStrUtil {

    @Test
    public void testLongString(){
        String strLong = "今天天气不错，心情也不错，代码遇见bug也不多，希望以后能保持";
        String strShort = "今天天气很坏，心情也很坏，不开心";
        String compareResult = compareStrLong(strShort, strLong, "red");
        // 打印的结果以长字符串为模板，将所有和短字符串不一致的文字全部使用样式标签包裹，传入的字符串不用分出顺序
        System.out.println(compareResult);
    }

    @Test
    public void testShortString(){
        String strLong = "今天天气不错，心情也不错，代码遇见bug也不多，希望以后能保持";
        String strShort = "今天天气很坏，心情也很坏，不开心";
        String compareResult = compareStrshort(strLong, strShort, "red");
        // 打印的结果以短字符串为模板，将所有和短字符串不一致的文字全部使用样式标签包裹
        System.out.println(compareResult);
    }

    // 从txt文件中获取字符串做对比
    @Test
    public void testDocString(){
        String strLongPath = "C:\\Users\\lf\\Desktop\\test\\file1.txt";
        String strLong = "";
        String strShortPath = "C:\\Users\\lf\\Desktop\\test\\file2.txt";
        String strShort = "";
        try {
            BufferedReader br1 = new BufferedReader(new FileReader(strLongPath));
            String line1 = null;
            BufferedReader br2 = new BufferedReader(new FileReader(strShortPath));
            String line2 = null;
            try {
                while ((line1=br1.readLine())!= null){
                    strLong += line1;
                }
                while ((line2=br2.readLine())!=null){
                    strShort += line2;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                try {
                    br1.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            String compareResult = compareStrLong(strLong, strShort, "red");
            System.out.println(compareResult);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


    // 长字符串向短字符串比
    public static String compareStrLong(String char1, String char2, String colour) {
        String bcolor = "<font color='" + colour + ";'>";
        String ecolor = "</font>";
        StringBuffer sb = new StringBuffer();
        char[] a = new char[char1.length()];
        for (int i = 0; i < char1.length(); i++) {
            a[i] = char1.charAt(i);
        }
        char[] b = new char[char2.length()];
        for (int i = 0; i < char2.length(); i++) {
            b[i] = char2.charAt(i);
        }
        // 不同字符集合
        Map<Object, Object> map1 = new HashMap<Object, Object>();
        // 包含字符集合
        Map<Object, Object> map2 = new HashMap<Object, Object>();
        if (char1.length() > char2.length()) {
            for (int i = 0; i < a.length; i++) {
                if (i == a.length - 1) {
                    if (i > 1) {
                        if (String.valueOf(b).contains(String.valueOf(a[i - 1]) + String.valueOf(a[i]))) {
                            map2.put(i - 1, a[i - 1]);
                            map2.put(i, a[i]);
                        }else{
                            map1.put(i, a[i]);
                        }
                    } else {
                        map2.put(i, a[i]);
                    }
                } else {
                    if (String.valueOf(b).contains(String.valueOf(a[i]) + String.valueOf(a[i + 1]))) {
                        if (i > 1) {
                            if (String.valueOf(b).contains(String.valueOf(a[i - 1]) + String.valueOf(a[i]))) {
                                map2.put(i - 1, a[i - 1]);
                                map2.put(i, a[i]);
                            }
                        } else {
                            map2.put(i, a[i]);
                        }
                    } else {
                        if (i > 0) {
                            if (String.valueOf(b).contains(String.valueOf(a[i - 1]) + String.valueOf(a[i]))) {
                                map2.put(i - 1, a[i - 1]);
                                map2.put(i, a[i]);
                            } else {
                                map1.put(i, a[i]);
                            }
                        } else {
                            map1.put(i, a[i]);
                        }
                    }
                }
            }
        } else {
            for (int i = 0; i < b.length; i++) {
                if (i == b.length - 1) {
                    if (i > 1) {
                        if (String.valueOf(a).contains(String.valueOf(b[i - 1]) + String.valueOf(b[i]))) {
                            map2.put(i - 1, b[i - 1]);
                            map2.put(i, b[i]);
                        }else{
                            map1.put(i, b[i]);
                        }
                    } else {
                        map2.put(i, b[i]);
                    }
                } else {
                    if (String.valueOf(a).contains(String.valueOf(b[i]) + String.valueOf(b[i + 1]))) {
                        if (i > 1) {
                            if (String.valueOf(a).contains(String.valueOf(b[i - 1]) + String.valueOf(b[i]))) {
                                map2.put(i - 1, b[i - 1]);
                                map2.put(i, b[i]);
                            }
                        } else {
                            map2.put(i, b[i]);
                        }
                    } else {
                        if (i > 0) {
                            if (String.valueOf(a).contains(String.valueOf(b[i - 1]) + String.valueOf(b[i]))) {
                                map2.put(i - 1, b[i - 1]);
                                map2.put(i, b[i]);
                            } else {
                                map1.put(i, b[i]);
                            }
                        } else {
                            map1.put(i, b[i]);
                        }
                    }
                }
            }
        }
        if (char1.length() > char2.length()) {
            for (int i = 0; i < a.length; i++) {
                if (map1.get(i) != null) {
                    sb.append(bcolor).append(map1.get(i)).append(ecolor);
                } else if (map2.get(i) != null) {
                    sb.append(map2.get(i));
                }
            }
        } else if (char1.length() <= char2.length()) {
            for (int i = 0; i < b.length; i++) {
                if (map1.get(i) != null) {
                    sb.append(bcolor).append(map1.get(i)).append(ecolor);
                } else if (map2.get(i) != null) {
                    sb.append(map2.get(i));
                }
            }
        }
        return sb.toString();
    }

    // 短字符串向长字符串比
    public static String compareStrshort(String char1, String char2, String colour) {
        String bcolor = "<font color='" + colour + ";'>";
        String ecolor = "</font>";
        StringBuffer sb = new StringBuffer();
        char[] a = new char[char1.length()];
        for (int i = 0; i < char1.length(); i++) {
            a[i] = char1.charAt(i);
        }
        char[] b = new char[char2.length()];
        for (int i = 0; i < char2.length(); i++) {
            b[i] = char2.charAt(i);
        }
        // 不同字符集合
        Map<Object, Object> map1 = new HashMap<Object, Object>();
        // 包含字符集合
        Map<Object, Object> map2 = new HashMap<Object, Object>();
        if (char1.length() > char2.length()) {
            for (int i = 0; i < b.length; i++) {
                if (i == b.length - 1) {
                    if (i > 1) {
                        if (String.valueOf(a).contains(String.valueOf(b[i - 1]) + String.valueOf(b[i]))) {
                            map2.put(i - 1, b[i - 1]);
                            map2.put(i, b[i]);
                        }else{
                            map1.put(i, b[i]);
                        }
                    } else {
                        map2.put(i, b[i]);
                    }
                } else {
                    if (String.valueOf(a).contains(String.valueOf(b[i]) + String.valueOf(b[i + 1]))) {
                        if (i > 1) {
                            if (String.valueOf(a).contains(String.valueOf(b[i - 1]) + String.valueOf(b[i]))) {
                                map2.put(i - 1, b[i - 1]);
                                map2.put(i, b[i]);
                            }
                        } else {
                            map2.put(i, b[i]);
                        }
                    } else {
                        if (i > 0) {
                            if (String.valueOf(a).contains(String.valueOf(b[i - 1]) + String.valueOf(b[i]))) {
                                map2.put(i - 1, b[i - 1]);
                                map2.put(i, b[i]);
                            } else {
                                map1.put(i, b[i]);
                            }
                        } else {
                            map1.put(i, b[i]);
                        }
                    }
                }
            }
        } else {
            for (int i = 0; i < a.length; i++) {
                if (i == a.length - 1) {
                    if (i > 1) {
                        if (String.valueOf(b).contains(String.valueOf(a[i - 1]) + String.valueOf(a[i]))) {
                            map2.put(i - 1, a[i - 1]);
                            map2.put(i, a[i]);
                        }else{
                            map1.put(i, a[i]);
                        }
                    } else {
                        map2.put(i, a[i]);
                    }
                } else {
                    if (String.valueOf(b).contains(String.valueOf(a[i]) + String.valueOf(a[i + 1]))) {
                        if (i > 1) {
                            if (String.valueOf(b).contains(String.valueOf(a[i - 1]) + String.valueOf(a[i]))) {
                                map2.put(i - 1, a[i - 1]);
                                map2.put(i, a[i]);
                            }
                        } else {
                            map2.put(i, a[i]);
                        }
                    } else {
                        if (i > 0) {
                            if (String.valueOf(b).contains(String.valueOf(a[i - 1]) + String.valueOf(a[i]))) {
                                map2.put(i - 1, a[i - 1]);
                                map2.put(i, a[i]);
                            } else {
                                map1.put(i, a[i]);
                            }
                        } else {
                            map1.put(i, a[i]);
                        }
                    }
                }
            }
        }

        if (char1.length() > char2.length()) {
            for (int i = 0; i < a.length; i++) {
                if (map1.get(i) != null) {
                    sb.append(bcolor).append(map1.get(i)).append(ecolor);
                } else if (map2.get(i) != null) {
                    sb.append(map2.get(i));
                }
            }
        } else if (char1.length() <= char2.length()) {
            for (int i = 0; i < b.length; i++) {
                if (map1.get(i) != null) {
                    sb.append(bcolor).append(map1.get(i)).append(ecolor);
                } else if (map2.get(i) != null) {
                    sb.append(map2.get(i));
                }
            }
        }
        return sb.toString();
    }
    // 获取对比标题
    public static String getcompareStr(String char1, String char2, String colour, String tag1, String tag2) {
        String bcolor = "<font color='" + colour + ";'>" + tag1;
        String ecolor = tag2 + "</font>";
        StringBuffer sb = new StringBuffer();
        char[] a = new char[char1.length()];
        for (int i = 0; i < char1.length(); i++) {
            a[i] = char1.charAt(i);
        }
        char[] b = new char[char2.length()];
        for (int i = 0; i < char2.length(); i++) {
            b[i] = char2.charAt(i);
        }
        // 不同字符集合
        Map<Object, Object> map1 = new HashMap<Object, Object>();
        // 包含字符集合
        Map<Object, Object> map2 = new HashMap<Object, Object>();
        for (int i = 0; i < a.length; i++) {
            if (i == a.length - 1) {
                if (i > 1) {
                    if (String.valueOf(b).contains(String.valueOf(a[i - 1]) + String.valueOf(a[i]))) {
                        map2.put(i - 1, a[i - 1]);
                        map2.put(i, a[i]);
                    }else{
                        map1.put(i, a[i]);
                    }
                } else {
                    map2.put(i, a[i]);
                }
            } else {
                if (String.valueOf(b).contains(String.valueOf(a[i]) + String.valueOf(a[i + 1]))) {
                    if (i > 1) {
                        if (String.valueOf(b).contains(String.valueOf(a[i - 1]) + String.valueOf(a[i]))) {
                            map2.put(i - 1, a[i - 1]);
                            map2.put(i, a[i]);
                        }
                    } else {
                        map2.put(i, a[i]);
                    }
                } else {
                    if (i > 0) {
                        if (String.valueOf(b).contains(String.valueOf(a[i - 1]) + String.valueOf(a[i]))) {
                            map2.put(i - 1, a[i - 1]);
                            map2.put(i, a[i]);
                        } else {
                            map1.put(i, a[i]);
                        }
                    } else {
                        map1.put(i, a[i]);
                    }
                }
            }
        }
        for (int i = 0; i < a.length; i++) {
            if (map1.get(i) != null) {
                sb.append(bcolor).append(map1.get(i)).append(ecolor);
            } else if (map2.get(i) != null) {
                sb.append(map2.get(i));
            }
        }
        return sb.toString();
    }

}

