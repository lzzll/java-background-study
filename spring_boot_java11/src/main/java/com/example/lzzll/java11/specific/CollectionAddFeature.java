package com.example.lzzll.java11.specific;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @Author lf
 * @Date 2023/6/1 14:27
 * @Description: 集合增强的功能 of()、copyOf()
 */
public class CollectionAddFeature {

    public static void main(String[] args) {
        var list = List.of(111,"111",111.0,111L);
        var copyList =  List.copyOf(list);
        System.out.println(list);
        System.out.println(copyList);

        var set = Set.of(111,"111",111L);
        var copySet = Set.copyOf(set);
        System.out.println(set);
        System.out.println(copySet);

        var map = Map.of("aaa","111","bbb",222);
        var copyMap = Map.copyOf(map);
        System.out.println(map);
        System.out.println(copyMap);

    }

}
