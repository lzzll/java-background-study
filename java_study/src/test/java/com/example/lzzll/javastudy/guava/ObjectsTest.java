package com.example.lzzll.javastudy.guava;

import com.example.lzzll.javastudy.guava.domain.Student;
import com.example.lzzll.javastudy.guava.domain.Teacher;
import com.google.common.base.MoreObjects;
import com.google.common.collect.ComparisonChain;
import org.junit.Test;

import java.util.Objects;


/**
 * @Author lf
 * @Date 2019/9/25 17:40
 * @Description:
 */
public class ObjectsTest {

    @Test
    public void test1(){
        // 直接使用equals方法比较两个对象的值
        boolean flag1 = Objects.equals("a", "a");
        boolean flag2 = Objects.equals(null, "a");
        boolean flag3 = Objects.equals(null, null); //true
        System.out.println(flag1+":"+flag2+":"+flag3);
        Student student1 = new Student("张三", 20);
        Student student2 = new Student("张三", 20);
        Student student3 = new Student("张三", 20);
        // 计算hash散列：可用作身份校验和数字签名
        int hash = Objects.hash(student1, student2, student3);
        System.out.println(hash);
        int hash1 = Objects.hashCode(student1);
        System.out.println(hash1);
    }

    // Objects.toStringHelper()方法来对数据进行toString处理，没有该方式时使用toString()方法形式依旧和toString一样
    @Test
    public void test2(){
        Student student = new Student("张三", 20);
        String string = Objects.toString(student);
        System.out.println(student);
        System.out.println(string);
        Teacher teacher = new Teacher();
        teacher.setName("李四");
        teacher.setAge(10);
//        String string1 = Objects.toString(teacher);
//        System.out.println(teacher);
//        System.out.println(string1);
        String teacherString = MoreObjects
                .toStringHelper(teacher)  // 不能直接添加已经有值的实体类，需要重新设置值
                .add("name","李四")
                .add("age",10)
                .toString();
        System.out.println(teacherString);
    }

    // 如果内容相同，返回的结果为0，如果有一个内容不同，那么结果就是-1
    @Test
    public void test3(){
        Teacher teacher1 = new Teacher("李四", 20, "武汉");
        Teacher teacher2 = new Teacher("王五", 18, "武汉");
        int result = ComparisonChain.start()
                .compare(teacher1.getName(), teacher2.getName())
                .compare(teacher1.getAge(), teacher2.getAge())
                .compare(teacher2.getLocation(), teacher2.getLocation())
                .result();
        System.out.println(result);
    }

}
