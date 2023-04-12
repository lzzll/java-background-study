package com.example.lzzll.javastudy.gson;

import com.example.lzzll.javastudy.gson.domain.School;
import com.example.lzzll.javastudy.gson.domain.Student;
import com.google.gson.Gson;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Author lf
 * @Date 2019/9/24 11:28
 * @Description:
 */
public class JsontToObject {

    private static Gson gson = null;

    static {
        gson = new Gson();
    }

    // 1.未嵌套的Json格式对象
    @Test
    public void test1(){
//        Gson gson = new Gson();
        String studentJson = "{'studentName':'jack','age':'10','school':null}";
        Student student = gson.fromJson(studentJson, Student.class);
        System.out.println(student);
    }

    // 2.将list集合转化为json格式数据
    @Test
    public void test2(){
        List<Student> list = new ArrayList<Student>();
        Student student1 = new Student("jack", 18, new School("hafu", new Date(), 5000));
        Student student2 = new Student("rose", 17, new School("liujin", new Date(), 3000));
        Student student3 = new Student("terry", 16, null);
        boolean flag = Collections.addAll(list, student1, student2, student3);
        System.out.println(flag);
//        Gson gson = new Gson();
        String str = gson.toJson(list);
        System.out.println(str);
    }

    // 3.将List集合的json数据转化为对象,字节码文件不能是实体类的字节码文件
    @Test
    public void test3(){
        String listJson = "[{\"studentName\":\"jack\",\"age\":18,\"school\":{\"name\":\"hafu\",\"buildTime\":\"Sep 24, 2019 11:41:55 AM\",\"size\":5000}},{\"studentName\":\"rose\",\"age\":17,\"school\":{\"name\":\"liujin\",\"buildTime\":\"Sep 24, 2019 11:41:55 AM\",\"size\":3000}},{\"studentName\":\"terry\",\"age\":16}]";
//        Student student = gson.fromJson(listJson, Student.class);
        List list = gson.fromJson(listJson, List.class);
        list.stream().forEach((student)-> System.out.println(student));
    }

    // 4.将map集合转化为json格式字符串
    @Test
    public void test4(){
        Student student1 = new Student("jack", 18, new School("hafu", new Date(), 5000));
        Student student2 = new Student("rose", 17, new School("liujin", new Date(), 3000));
        Student student3 = new Student("terry", 16, null);
        Map<String, Student> map = new HashMap<>();
        map.put("student1",student1);
        map.put("student2",student2);
        map.put("student3",student3);
        String mapJson = gson.toJson(map);
        System.out.println(mapJson);
    }

    // 5.解析map格式的json字符串
    @Test
    public void test5(){
        String mapJson = "{\"student2\":{\"studentName\":\"rose\",\"age\":17,\"school\":{\"name\":\"liujin\",\"buildTime\":\"Sep 24, 2019 11:55:03 AM\",\"size\":3000}},\"student1\":{\"studentName\":\"jack\",\"age\":18,\"school\":{\"name\":\"hafu\",\"buildTime\":\"Sep 24, 2019 11:55:03 AM\",\"size\":5000}},\"student3\":{\"studentName\":\"terry\",\"age\":16}}";
        Map map = gson.fromJson(mapJson, Map.class);
        Iterator iterator = map.entrySet().iterator();
        while (iterator.hasNext()){
            Map.Entry entry = (Map.Entry) iterator.next();
            System.out.println(entry.getKey()+":"+entry.getValue());
        }
    }

    // 6.将数组转化成json字符串
    @Test
    public void test6(){
        String[] array = {"jack","rose","terry"};
        String jsonStr = gson.toJson(array);
        System.out.println(jsonStr);
    }

    // 7.将数组格式的json串转化为数组，指定string数组类型的字节码文件即可
    @Test
    public void test7(){
        String jsonStr = "[\"jack\",\"rose\",\"terry\"]";
        String[] strings = gson.fromJson(jsonStr, String[].class);
//        Array array = gson.fromJson(jsonStr, Array.class);
        for (String string : strings) {
            System.out.println(string);
        }
    }

    // 8.将嵌套的对象转化为json字符串
    @Test
    public void test8(){
        try {
            School school = new School();
            school.setName("jiangda");
            school.setBuildTime(new SimpleDateFormat("yyyy-MM-dd").parse("1987-05-21"));
            school.setSize(5000);
            Student student = new Student("zhangsan", 20, school);
            String str = gson.toJson(student);
            System.out.println(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    // 9.将嵌套的json字符串转化为对象
    @Test
    public void test9(){
        String jsonStr = "{\"studentName\":\"zhangsan\",\"age\":20,\"school\":{\"name\":\"jiangda\",\"buildTime\":\"May 21, 1987 12:00:00 AM\",\"size\":5000}}";
        Student student = gson.fromJson(jsonStr, Student.class);
        System.out.println(student);
    }

    public static String formatDate(Date date){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS");
        return sdf.format(date);
    }

}
