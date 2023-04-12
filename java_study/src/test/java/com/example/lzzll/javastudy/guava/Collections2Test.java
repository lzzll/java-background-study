package com.example.lzzll.javastudy.guava;

import com.google.common.base.Function;
import com.google.common.base.Functions;
import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.junit.Test;
import reactor.util.annotation.Nullable;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * @Author lf
 * @Date 2019/9/26 11:49
 * @Description:
 */
public class Collections2Test {

    @Test
    public void testFilter(){
        List<String> list= Lists.newArrayList("moon","dad","refer","son");
//        Collection<String> palindromeList= Collections2.filter(list, input -> {
//            return new StringBuilder(input).reverse().toString().equals(input); //找回文串,字符串对称
//        });
        Collection<String> palindromeList = Collections2.filter(list, new Predicate<String>() {
            @Override
            public boolean apply(String s) {
                return new StringBuilder(s).reverse().toString().equals(s);
            }
        });
        System.out.println(palindromeList);
    }

    // 时间转换为字符串格式
    @Test
    public void testTransform(){
        Set<Long> times= Sets.newHashSet();
        times.add(91299990701L);
        times.add(9320001010L);
        times.add(9920170621L);
        Collection<String> timeStrCol= Collections2.transform(times, new Function<Long, String>() {
            @Nullable
            @Override
            public String apply(@Nullable Long input) {
                return new SimpleDateFormat("yyyy-MM-dd").format(input);
            }
        });
        System.out.println(timeStrCol);
    }

    // 多个函数进行组合使用
    @Test
    public void testTransform2(){
        List<String> list= Lists.newArrayList("abcde","good","happiness");
        //确保容器中的字符串长度不超过5
        Function<String,String> f1=new Function<String, String>() {
            @Nullable
            @Override
            public String apply(@Nullable String input) {
                return input.length()>5?input.substring(0,5):input;
            }
        };
        //转成大写
        Function<String,String> f2=new Function<String, String>() {
            @Nullable
            @Override
            public String apply(@Nullable String input) {
                return input.toUpperCase();
            }
        };
        Function<String,String> function= Functions.compose(f1,f2);
        Collection<String> results=Collections2.transform(list,function);
        System.out.println(results);
    }
}
