package com.example.lzzll.javastudy.guava;

import com.example.lzzll.javastudy.guava.domain.Student;
import com.google.common.collect.*;
import org.junit.Test;

import java.util.*;
import java.util.function.BiConsumer;

/**
 * @Author lf
 * @Date 2019/9/24 16:28
 * @Description:
 */
public class ListAndMapTest {

    @Test
    public void testMap(){
        // 方式一：map的创建
        Map<String,Map<String,List<Student>>> map = Maps.newHashMap();
        // 方式二：map的创建,自定义静态方法封装创建map的快捷方法,创建的是不可变集合，不饿能对数据进行增删改查
        // 直接往map中添加数据
        ImmutableMap<String, String> map1 = ImmutableMap.of("key1", "value1", "key2", "value2", "key3", "value3");
        ImmutableSet<Map.Entry<String, String>> entries = map1.entrySet();
        // 方式一：使用entrySet来遍历map集合，效率最高
        for (Map.Entry<String, String> entry : entries) {
            String key = entry.getKey();
            String value = entry.getValue();
            System.out.println(key+":"+value);
        }
        // 方式二：使用iterator迭代器来遍历map集合,可以在迭代器中删除元素（最好不要删除）
        UnmodifiableIterator<Map.Entry<String, String>> iterator = map1.entrySet().iterator();
        while (iterator.hasNext()){
//            String key = iterator.next().getKey();  // 不能使用连续点到getKey()的方式来遍历，会导致元素错位
            Map.Entry<String, String> entry = iterator.next();
            System.out.println(entry.getKey()+":"+entry.getValue());
//            iterator.remove();
        }
        // 方式三：使用foreach的方式进行遍历
        map1.forEach(new BiConsumer<String, String>() {
            @Override
            public void accept(String s, String s2) {
                System.out.println(s+":"+s2);
            }
        });
        // 简化写法
        map1.forEach((key,value)->System.out.println(key+":"+value));
        // 将map放入map中
//        map.putAll(map1);

    }

    @Test
    public void testList(){
        // 创建的是不可变的list集合，不能往其中添加数据
        ImmutableList<String> list = ImmutableList.of("a", "b", "c","a");
        // 使用foreach进行遍历
//        list.stream().forEach((str)-> System.out.println(str));
//        ArrayList<String> list1 = Lists.newArrayList();
        ArrayList<String> list2 = new ArrayList<>();
        Collections.addAll(list2,"a", "b", "c","a");
        // 使用iterator迭代器的包装类PeekingIterator来进行迭代操作
        PeekingIterator<String> pit = Iterators.peekingIterator(list2.iterator());
        List<String> listResult = new ArrayList<>();
        while (pit.hasNext()){
            String current = pit.next();
//            System.out.println(current);
            while (pit.hasNext() && pit.peek().equals(current)){
                // 跳过重复的元素
                pit.next();
            }
            listResult.add(current);
        }
//        listResult.forEach((s)-> System.out.println(s));
        // 校验自己写的去重的工具类
        List returnList = skipRepeat(list2);
        System.out.println(returnList);
    }

    // 删除ArrayList中重复元素，保持顺序,还可以使用list.contains()方法
    public static List skipRepeat(List list) {
        Set set = new HashSet();
        List newList = new ArrayList();
        for (Iterator iter = list.iterator(); iter.hasNext();) {
            Object element = iter.next();
            if (set.add(element))
                newList.add(element);
        }
        list.clear();
        list.addAll(newList);
        System.out.println( " remove duplicate " + list);
        return list;
    }

    // 求集合的交集，差集和并集
    @Test
    public void testSet(){
        HashSet<Object> set1 = Sets.newHashSet(1,2,3,4,5);
        HashSet<Object> set2 = Sets.newHashSet(3,4,5,6);
        // 求两个集合的交集
        Sets.SetView<Object> intersection = Sets.intersection(set1, set2);
        System.out.println(intersection);
        // 求两个集合的差集,在set1中不在set2中
        Sets.SetView<Object> diff = Sets.difference(set1, set2);
        System.out.println(diff);
        // 求两个集合的并集
        Sets.SetView<Object> union = Sets.union(set1, set2);
        System.out.println(union);
    }

}
