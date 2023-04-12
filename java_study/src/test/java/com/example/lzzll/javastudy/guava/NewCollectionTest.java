package com.example.lzzll.javastudy.guava;

import com.google.common.collect.*;
import org.junit.Test;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

/**
 * @Author lf
 * @Date 2019/9/26 11:26
 * @Description:
 */
// 新型集合的测试
public class NewCollectionTest {

    // 将map集合的键值进行调换，完成双向映射，要求原map中value唯一，并且操作映射前的map集合会影响映射后的map集合
    @Test
    public void biMapTest(){
        BiMap<Integer,String> idNameMap = HashBiMap.create();
        idNameMap.put(1,"xiaohong");
        idNameMap.put(2,"xiaoming");
        idNameMap.put(3,"xiaolan");
        System.out.println("before idNameMap:"+idNameMap);
        BiMap<String,Integer> nameIdMap = idNameMap.inverse();
        System.out.println("before nameIdMap:"+nameIdMap);
        String value = idNameMap.remove(1);
        System.out.println(value);
        System.out.println("after idNameMap:"+idNameMap);
        System.out.println("after nameIdMap:"+nameIdMap);
    }

    // 新型的set集合，可以统计一个词在文档中出现了多少次
    @Test
    public void multiSetTest(){
        Multiset<String> set= LinkedHashMultiset.create();
        set.add("a");
        set.add("a");
        set.add("a");
//        set.add("a");
        set.setCount("a",5); //添加或删除指定元素使其在集合中的数量是count，表示直接将一个元素设置为添加五次
        System.out.println(set.count("a")); //给定元素在Multiset中的计数
        System.out.println(set);
        System.out.println(set.size()); //所有元素计数的总和,包括重复元素
        System.out.println(set.elementSet().size()); //所有元素计数的总和,不包括重复元素
        set.clear(); //清空集合
        System.out.println(set);
    }

    @Test
    public void multiMapTest(){
        Multimap<String,Integer> map= HashMultimap.create(); //Multimap是把键映射到任意多个值的一般方式
        map.put("a",1); //key相同时不会覆盖原value
        map.put("a",2);
        map.put("a",3);
        System.out.println(map); //{a=[1, 2, 3]}
        System.out.println(map.get("a")); //返回的是集合
        System.out.println(map.size()); //返回所有”键-单个值映射”的个数,而非不同键的个数
        System.out.println(map.keySet().size()); //返回不同key的个数
        Map<String,Collection<Integer>> mapView=map.asMap();
        System.out.println(mapView);
    }

    @Test
    public void tableTest(){
        //记录学生在某门课上的成绩
        Table<String,String,Integer> table= HashBasedTable.create();
        table.put("jack","java",100);
        table.put("jack","c",90);
        table.put("mike","java",93);
        table.put("mike","c",100);
        Set<Table.Cell<String,String,Integer>> cells=table.cellSet();
        for (Table.Cell<String,String,Integer> cell : cells) {
            System.out.println(cell.getRowKey()+" "+cell.getColumnKey()+" "+cell.getValue());
        }
        Map<String, Integer> rowValue = table.column("java");
        System.out.println(rowValue);  // 获取行和value组成的键值对，结果是map格式，{jack=100, mike=93}
        System.out.println(table.row("jack"));  // 获取列和value组成的键值对，结果是map格式，{java=100, c=90}
        System.out.println(table);  // {jack={java=100, c=90}, mike={java=93, c=100}}
        System.out.println(table.rowKeySet()); // [jack, mike]
        System.out.println(table.columnKeySet()); // [java, c]
        System.out.println(table.values()); // [100, 90, 93, 100]
    }
}
