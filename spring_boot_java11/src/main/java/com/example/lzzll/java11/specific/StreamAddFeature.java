package com.example.lzzll.java11.specific;

import com.example.lzzll.java11.entity.CalculatePoint;
import com.example.lzzll.java11.entity.PersonEntity;
import com.example.lzzll.java11.entity.Review;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.example.lzzll.java11.entity.CalculatePoint.average;

/**
 * @Author lf
 * @Date 2023/6/1 14:36
 * @Description: 函数式编程功能增强 takeWhile() 、 dropWhile()
 */
public class StreamAddFeature {

    public static void main(String[] args) {
        var list = List.of("aa","ba","cd","da","ba","aa");
        PersonEntity person1 = new PersonEntity("java", 50);
        PersonEntity person2 = new PersonEntity("python", 35);
        PersonEntity person3 = new PersonEntity("php", 10);
        List<PersonEntity> personList = List.of(person1, person2,person3);

        // takeWhile() 从集合中依次取出满足条件的元素，直到不满足的元素为止
//        var filterList = list.stream().takeWhile(item->item.contains("a")).collect(Collectors.toList());
//        System.out.println(filterList);

        // dropWhile() 从集合中依次删除满足条件的元素，直到不满足的元素为止
//        var filterList2 =  list.stream().dropWhile(item->item.contains("a")).collect(Collectors.toList());
//        System.out.println(filterList2);

        // distinct() 去除集合中的重复元素
//        var distinctList = list.stream().distinct().collect(Collectors.toList());
//        System.out.println(distinctList);
//        var set = list.stream().collect(Collectors.toSet());
//        System.out.println(set);

        // peek()等流执行之后才真正进行执行方法，可用于调试代码。不会改编流中的对象，但是可以改编对象中的属性值
//        list.stream().peek(System.out::println);  // 不会有内容输出
////        List<String> peekList = list.stream().peek(System.out::println).collect(Collectors.toList());  // 可以输出打印内容
//        list.stream().peek(String::toUpperCase).forEach(System.out::println);  // 元素不会被改为大写
//        list.stream().map(String::toUpperCase).forEach(System.out::println);  // 元素可被改变为大写
//        personList.stream().peek(item->item.setAge(333)).forEach(System.out::println);  // peek可修改集合对象中的字段
//        personList.stream().map(item->{item.setAge(333);return item;}).forEach(System.out::println);  // map也可修改集合对象字段

        // reduce() 归并，可用于数据求和、合并、等需求
        // 1、简单用法，不用传入初始化的值。合并字符串
//        String result = list.stream().distinct().map(String::toUpperCase).reduce((x, y) -> x + y).get();
//        String result1 = list.stream().distinct().map(String::toUpperCase).reduce(String::concat).get();
//        String result2 = list.stream().distinct().reduce((x, y) -> x.toUpperCase() + y.toUpperCase()).get();
//        System.out.println(result);
//        System.out.println(result1);
//        System.out.println(result2);
        // 2、传入初始值的用法。计算多个用户的平均年龄
//        Double totalAge = personList.stream().map(PersonEntity::getAge).mapToDouble(Integer::doubleValue).reduce(0, Double::sum);
//        System.out.println(String.format("各用户的平均年龄：%.2f",totalAge/personList.size()));
        // 3、复杂用法。流中包含的是user对象，但是累加函数的参数分别是数字和user对象，而累加器的实现是求和，所以编译器无法推断参数user的类型，故编译无法通过
//        Integer totalAge = personList.stream().reduce(0, (preTotalAge, user) -> preTotalAge + user.getAge());
        // 增加一个求和函数可通过编译和正确计算
//        Integer totalAge = personList.stream().reduce(0, (preTotalAge, user) -> preTotalAge + user.getAge(), Integer::sum);
//        System.out.println(String.format("各用户的平均年龄：%.2f",totalAge.doubleValue()/personList.size()));

        // 字符串转数组
        String[] array = {"111","222"};
        Arrays.stream(array).map(a->a+=100).map(String::toUpperCase).forEach(System.out::println);

        // 复杂案例：计算一个网站用户的评分，该评分是所有用户所有评论的平均值
        person1.getCalculatePoint().add(new Review(3.0,"common"));
        person1.getCalculatePoint().add(new Review(5.0,"perfect"));
        person2.getCalculatePoint().add(new Review(1.0,"terrible"));
        person3.getCalculatePoint().add(new Review(0.0,"fuck"));
        List<PersonEntity> persons = List.of(person1, person2,person3);
        // 方法一 老式的方式根据业务来计算
        CalculatePoint result = oldCountMethod(persons);
        System.out.println(result);
        // 方法二 采用流式思想获取来进行计算
        CalculatePoint result1 = persons.stream().reduce(new CalculatePoint(),(calcu,person)->average(calcu,person.getCalculatePoint()),CalculatePoint::average);
        System.out.println(result1);
    }

    /**
     * 计算用户评分平均值的方法
     * @param persons
     * @return
     */
    private static CalculatePoint oldCountMethod(List<PersonEntity> persons) {
        // 计算所有用户的平均值
        List<Review> reviews = new ArrayList<>();
        Double totalPoint = 0d;
        for (PersonEntity person : persons) {
            reviews.addAll(person.getCalculatePoint().getReviews());
            totalPoint += person.getCalculatePoint().getReviews().stream().reduce(0d,(count,review)->count+review.getPoint(),Double::sum);
        }
        return new CalculatePoint(totalPoint/reviews.size(),reviews);

        // 先取单个用户的平均值，最后再取用户平均值的平均值（逻辑错误）
//        List<CalculatePoint> calculatePointList = persons.stream().map(PersonEntity::getCalculatePoint).collect(Collectors.toList());
//        List<Review> reviews = new ArrayList<>();
//        Double totalPoint = 0d;
//        for (CalculatePoint calculatePoint : calculatePointList) {
//            reviews.addAll(calculatePoint.getReviews());
//            totalPoint+=calculatePoint.getAveragePoint();
//        }
//        return new CalculatePoint(totalPoint/calculatePointList.size(),reviews);
    }


}
