package com.example.lzzll.javastudy.guava;//package com.example.lzzll.guava;
//
//import com.example.lzzll.guava.domain.Student;
//import com.google.common.base.*;
//import com.google.common.collect.Lists;
//import com.google.common.collect.Maps;
//import com.google.common.util.concurrent.Futures;
//import com.sun.istack.internal.NotNull;
//import com.sun.istack.internal.Nullable;
//import org.junit.Test;
//
//import java.text.SimpleDateFormat;
//import java.util.*;
//import java.util.stream.Collectors;
//
//import static java.util.stream.Collectors.counting;
//import static java.util.stream.Collectors.joining;
//import static java.util.stream.Collectors.toList;
//
///**
// * @Author lf
// * @Date 2019/9/27 15:55
// * @Description:
// */
//public class FunctionTest {
//
//    public void print(Object obj){
//        System.out.println(String.valueOf(obj));
//    }
//
//    @Test
//    public void test1(){
//        Function<Date, String> function = new Function<Date, String>() {
//            @Override
//            public String apply(Date date) {
//                return new SimpleDateFormat("yyyy-mm-dd").format(date);
//            }
//        };
//        print(function.apply(new Date()));
//    }
//
//    @Test
//    public void test2(){
//        // 测试forMap()方法
//        Map<String, String> map = Maps.newHashMap();
//        map.put("name","zhangsan");
//        map.put("age","20");
////        Function<String, String> function = Functions.forMap(map);
//        Function<String, String> function = Functions.forMap(map, "default");  // 通过forMap的重载方法设置默认值
//        print(function.apply("name"));
//        print(function.apply("location"));  // 如果不设置默认值，会报没有这个key的非法参数异常
//        // 测试compose()方法
//        Function<String, String> function1 = new Function<String, String>() {
//            @Override
//            public String apply(String s) {
//                return s+" zhongguo";
//            }
//        };
////        java.util.function.Function<String, String> result = function1.compose(function);
//        Function<String, String> result = Functions.compose(function1, function);  // 组合两个function时要注意顺序，后执行的放前面
//        print(result.apply("name"));
//
//    }
//
//    // 跳过空字符串
//    // 确保容器中的字符串长度超过5
//    // 转成大写
//    @Test
//    public void test3() {
//
//        ArrayList<String> list = Lists.newArrayList("as ilan", "jila", "", "lakesi", "kajialian");
//        Function<List<String>, List<String>> f1 = new Function<List<String>, List<String>>() {
//            @Override
//            @Nullable
//            public List<String> apply(List<String> list1) {
//                ArrayList<String> listreturn = Lists.newArrayList();
//                for (String str : list) {
//                    if (str==null||"".equals(str)){
//                        continue;
//                    }
//                    listreturn.add(str);
//                }
//                return listreturn;
//              /*  return list1.removeIf(new Predicate<String>() {
//                    @Override
//                    public boolean apply(String s) {
//                        return s==null || "".equals(s);
//                    }
//                });*/
//            }
//        };
//        Function<List<String>, List<String>> f2 = new Function<List<String>, List<String>>() {
//            @Override
//            public List<String> apply(List<String> s) {
//                return s.stream().filter((str)->
//                    str.length()>5
//                ).collect(toList());
//            }
//        };
//        Function<String, String> f3 = new Function<String, String>() {
//            @Override
//            public String apply(String s) {
//                return s.toLowerCase();
//            }
//        };
//        Function<List<String>, List<String>> f = Functions.compose(f2, f1);
//        List<String> listr = f.apply(list);
//        listr.forEach((str)-> System.out.println(str));
//    }
//
//    // 跳过空字符串
//    // 确保容器中的字符串长度超过5
//    // 转成大写
//    @Test
//    public void test4() {
//        ArrayList<String> list = Lists.newArrayList("as ilan", "jila", "", "lakesi", "kajialian");
//        ArrayList<String> list1 = Lists.newArrayList();
//        for (int i = 0; i < list.size(); i++) {
//            String str = CharMatcher.whitespace().removeFrom(list.get(i));
//            list1.add(str);
//        }
//
////        list.stream().filter(str->!(str==null||"".equals(str))).filter(str->str.length()>5).forEach(s -> System.out.println(s));
//        List<String> listr = list1.stream().filter(str -> !(str == null || "".equals(str))).filter(str -> str.length() > 5).collect(toList());
//        Function<String, String> function = new Function<String, String>() {
//            @Override
//            public String apply(String s) {
//                return s.toUpperCase();
//            }
//        };
//        for (int i = 0; i < listr.size(); i++) {
//            String str = function.apply(listr.get(i));
//            System.out.println(str);
//        }
//    }
//
//    // 测试stream中collect()的用法
//    @Test
//    public void test5() {
//        ArrayList<String> list = Lists.newArrayList("asilan", "jila","lakesi", "kajialian");
//        // 用法一：利用collect()方法将集合转化为指定格式的字符串
//        String str = list.stream().collect(joining("|"));  // asilan|jila|lakesi|kajialian
//        System.out.println(str);
//        // 用法二：按照对象中指定字段进行统计
//        ArrayList<Student> listStudent = Lists.newArrayList();
//        for (int i = 0; i < list.size(); i++) {
//            String name = list.get(i);
//            listStudent.add(new Student(name,i));
//        }
//        Map<Integer, Long> map = listStudent.stream().collect(Collectors.groupingBy(Student::getAge, counting()));
//        printMap(map);
//        // 用法三：可以将数据类型进行转化（将listStudent转为key为age，value为student的map集合）
//        HashMap<Integer,Student> mapReturn = listStudent.stream().collect(() -> new HashMap<Integer,Student>(), (newMap, student) -> newMap.put(student.getAge(), student), (m, s) -> m.putAll(s));
//        printMap(mapReturn);
//
//    }
//
//    // 遍历map
//    private static void printMap(Map map){
//        Iterator<Map.Entry<Integer, Long>> iterator = map.entrySet().iterator();
//        while (iterator.hasNext()){
//            Map.Entry<Integer, Long> entry = iterator.next();
//            System.out.println("key:"+entry.getKey()+"value:"+entry.getValue());
//        }
//    }
//
//
//    private static void changeDate(Function<Date,String> function,Date date){
//        String apply = function.apply(date);
//    }
//}
