package com.example.lzzll.javastudy.guava;

import com.example.lzzll.javastudy.guava.domain.Student;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.google.common.collect.Ordering;
import com.google.common.primitives.Ints;
import org.junit.Test;

import java.text.Collator;
import java.util.*;

/**
 * @Author lf
 * @Date 2019/9/25 18:36
 * @Description:
 */
// 实现比较的功能
public class OrderingTest {


    private static void print(Object obj){
        System.out.println(String.valueOf(obj));
    }

    // 传统的排序方式
    @Test
    public void test1(){
        ArrayList<Integer> list = Lists.newArrayList(1, 3, 2, 5, 4);
        ArrayList<String> list1 = Lists.newArrayList("java","python","c++");
        ArrayList<String> list2 = Lists.newArrayList("张三", "李四", "王五", "赵六");
        // 对数字进行排序
        Collections.sort(list, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
//                return o1-o2;  // 正序
                return o2-o1;  // 倒序
            }
        });
        // 对英文字符串进行排序
        Collections.sort(list1,(str1,str2)-> str1.compareTo(str2));
        // 对中文进行排序
        Collections.sort(list2,(str1,str2)->  {
                    String o1 = "";
                    String o2 = "";
                    if (str1 != null) {
                        o1 = str1;
                    }
                    if (str2 != null) {
                        o2 = str2;
                    }
                    Collator instance = Collator.getInstance(Locale.CHINA);
                    return instance.compare(o1, o2);
                });
//        System.out.println(list);
//        System.out.println(list1);
        System.out.println(list2);
    }

    // 使用Ordering进行排序
    @Test
    public void test2(){
        ArrayList<Integer> list = Lists.newArrayList(1, 3, 2, 5, 4);
        ArrayList<String> list1 = Lists.newArrayList("java","python","c++");
        ArrayList<String> list2 = Lists.newArrayList("张三", "李四", "王五", "赵六");
        Collections.sort(list,Ordering.natural());  // 顺序排序，可用于数字和日期，英文字符也可使用
        Collections.sort(list,Ordering.natural().reverse()); // 倒序
//        Collections.sort(list1,Ordering.usingToString());  // 对英文字符串进行排序，无法对中文字符串排序
       /* Collections.sort(list2,Ordering.from((str1,str2)->{  // 中文排序可传入自定义的比较器
            String o1 = "";
            String o2 = "";
            if (str1 != null){
                o1 = str1;
            }
            if (str2 != null){
                o2 = str2;
            }
            Collator instance = Collator.getInstance(Locale.CHINA);
            return instance.compare(o1,o2);
        }));*/
        System.out.println(list);
        System.out.println(list1);
//        System.out.println(list2);
    }

    // 使用Ordering进行链式调用，可以获取其它元素
    @Test
    public void test3(){
        ArrayList<Integer> list = Lists.newArrayList(1, 3, 2, 5, 4);
//        Integer max = Ordering.natural().max(list);  // 获取集合中的最大值5
//        List<Integer> list1 = Ordering.natural().greatestOf(list, 3);  // 获取最大的三个元素
//        System.out.println(list1);
//        System.out.println(max);
//        Integer max1 = Ordering.natural().reverse().max(list);
//        System.out.println(max1);  // 如果进行逆序后，最大值就是1
//        System.out.println(list);
        List<Integer> copyList = Ordering.natural().sortedCopy(list);  // 返回一个可变的副本
        copyList.add(6);
        System.out.println(copyList);
        System.out.println(list);
        ImmutableList<Integer> copyList1 = Ordering.natural().immutableSortedCopy(list);  // 返回一个不可变的副本，不能对元素进行增删改查
//        copyList1.add(7);  // 报错
        print(copyList1);
        print(list);

    }

    // 将两个比较器进行整合，多段排序
    @Test
    public void test4(){
        // 可以对多个比较器进行整合，当第一个比较器比较的结果相同时，可以使用第二个比较器进行比较
        Student stu1 = new Student("张三", 16);
        Student stu2 = new Student("李四", 16);
        Student stu3 = new Student("王五", 14);
        List<Student> list = Lists.newArrayList(stu1, stu2, stu3);
        // 整合两个比较器,不能使用lambda表达式进行连续调用
//        Ordering<Object> compartor = Ordering.from((student1, student2) -> {
//            return student1.getAge() - student2.getAge()
//                    ;
//        }).compound((student3, student4) -> {
//            return student3.getName().compareTo(student4.getName());
//        });
//        Collections.sort(list,Ordering.from((student1,student2)-> {return student1.getAge()-student2.getAge()
//        ;}).compound((student3,student4)->{return student3.getName().compareTo(student4.getName());})
//        );
        // 声明两个比较器的实现类对象，然后再进行整合
        StudentAgeComparator studentAgeComparator = new StudentAgeComparator();
        StudentNameComparator studentNameComparator = new StudentNameComparator();
        Ordering<Student> compartor = Ordering.from(studentAgeComparator).compound(studentNameComparator);
        Collections.sort(list,compartor);
        print(list);
    }

    public class StudentAgeComparator implements Comparator<Student> {
        @Override
        public int compare(Student student1, Student student2) {
            return Ints.compare(student1.getAge(), student2.getAge());
        }
    }

    public class StudentNameComparator implements Comparator<Student> {
        @Override
        public int compare(Student student1, Student student2) {
            String o1 = "";
            String o2 = "";
            if (student1 != null){
                o1 = student1.getName();
            }
            if (student2 != null){
                o2 = student2.getName();
            }
            Collator instance = Collator.getInstance(Locale.CHINA);
            return instance.compare(o1,o2);
        }
    }

    @Test
    public void test(){
        Ordering<Integer> order = Ordering.from(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }
        });
        List<Integer> list = Arrays.asList(5,4,1,2,3);
        Collections.sort(list,order);
        print(list);
    }
}
