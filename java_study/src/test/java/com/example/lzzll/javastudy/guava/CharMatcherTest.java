package com.example.lzzll.javastudy.guava;//package com.example.lzzll.guava;
//
//import com.google.common.base.CharMatcher;
//import com.google.common.base.Joiner;
//import com.google.common.base.Splitter;
//import com.google.common.collect.Lists;
//import com.google.common.collect.Maps;
//import com.google.common.primitives.Ints;
//import org.junit.Test;
//
//import static org.junit.Assert.*;
//import static org.hamcrest.CoreMatchers.*;
//
//import java.util.ArrayList;
//import java.util.Map;
//
///**
// * @Author lf
// * @Date 2019/9/24 17:40
// * @Description:
// */
//public class CharMatcherTest {
//
//    // CharMatcher
//    //1.需要匹配什么样的字符串？类型选错无法操作字符串
//    //2.在这些匹配的字符串上执行什么样的操作？例如 trimming,collapsing, removing等
//    @Test
//    public void testAny(){
//        String str = "需要匹配什2么3样6的字符串？类型选错无法操作456字符串123";
////        String s = CharMatcher.digit().removeFrom(str);
////        String s = CharMatcher.ASCII.removeFrom(str);
//        String s = CharMatcher.JAVA_LETTER.removeFrom(str);
//        System.out.println(s);
////        CharMatcher any = CharMatcher.any();
////        System.out.println(any);
////        boolean flag = CharMatcher.any().matchesAnyOf(str);
////        System.out.println(flag);
//    }
//
//    @Test
//    public void testMatcher(){
//        String str = "    String  with      spaces     and           tabs ";
//        // 删除所有匹配到的空白字符
//        String scrubbed  = CharMatcher.whitespace().removeFrom(str);
//        String expected = "Stringwithspacesandtabs";
////        assertThat(scrubbed,is(expected));  // 断言的用法，需要额外添加两个依赖jar包
//        // 将字符串中连续的字符串替换为一个新的字符
//        String scrubbed1 = CharMatcher.whitespace().collapseFrom(str,' ');
//        System.out.println(scrubbed1);
//    }
//
//    @Test
//    public void testMatcher1(){
//        String str = "AAabbbCcCcDdDEeE";
////        String scrubbed = CharMatcher.inRange('a','e').retainFrom(str);  // abbbccde
//        String scrubbed = CharMatcher.inRange('a','e').removeFrom(str);  // AACCDDEE
//        System.out.println(scrubbed);
//    }
//
//    @Test
//    public void test1(){
//        // 从字符串中获取所有数据
//        String string = CharMatcher.DIGIT.retainFrom("some text 89983 and more");
////        String str = CharMatcher.ANY.retainFrom("some text 89983 and more");
////        System.out.println(str);
//        System.out.println(string);
//        // 去掉字符串中数据
//        String string1 = CharMatcher.DIGIT.removeFrom("some text 89983 and more");
//        System.out.println(string1);
//    }
//
//    // Joiner
//    @Test
//    public void test2(){
//        String[] subdirs = { "usr", "local", "lib" };
//        String directory = Joiner.on("/").join(subdirs);
//        System.out.println(directory);
//        System.out.println("--------------------");
//        int[] numbers = { 1, 2, 3, 4, 5 };
//        String numbersAsString = Joiner.on(";").join(Ints.asList(numbers));
//        System.out.println(numbersAsString);
//        String numbersAsStringDirectly = Ints.join(";", numbers);
//        System.out.println("----------------------");
//        ArrayList<String> strArr1 = Lists.newArrayList(
//                "test1","test2","test3",null,"test4",null,null);
//        String join1 = Joiner.on(';').skipNulls().join(strArr1);
//        System.out.println(join1);
//        String join2 = Joiner.on(';').useForNull("_").join(strArr1);
//        System.out.println(join2);
//        System.out.println("------------------------");
//        Map<String, String> map = Maps.newHashMap();
//        map.put("key1", "value1");
//        map.put("key2", "value2");
//        map.put("key3", null);
//        map.put("key4", "value3");
//
//        String str = Joiner.on(';')
//                .useForNull("NULL")
//                .withKeyValueSeparator("=")
//                .join(map);
//        System.out.println(str);
//        System.out.println("------------------------");
//        String tmpValue = "a_b_c_1_2_3";
//        String[] valArr = tmpValue.split("_");
//
//        // 求字符串数组的子串，并最后拼接起来
//        String tmpVal = "";
//        for (int i = 1; i < valArr.length; i++) {
//            tmpVal = tmpVal.equalsIgnoreCase("") ? valArr[i] : tmpVal + "_" + valArr[i];
//        }
//        System.out.println(tmpVal);
//        System.out.println("———————");
//
//        // 上面这么一段与下面这句等价
//        System.out.println(Joiner.on("_").join(Lists.newArrayList(valArr).subList(1, valArr.length)));
//
//    }
//
//    // splitter
//    @Test
//    public void test3(){
//        String numberAsString = "1;2;3;4;5";
//        Iterable<String> strs = Splitter.on(";").split(numberAsString);
//        strs.forEach((str)-> System.out.println(str));
//        String testString = "foo , what,,,more,";
//        Iterable<String> str1s = Splitter.on(",").omitEmptyStrings().trimResults().split(testString);
//        str1s.forEach((str)-> System.out.println(str));
//    }
//}
