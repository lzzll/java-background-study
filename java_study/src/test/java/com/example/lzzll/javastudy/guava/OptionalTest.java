package com.example.lzzll.javastudy.guava;

import java.util.Optional;

/**
 * @Author lf
 * @Date 2019/9/25 10:14
 * @Description:
 */
public class OptionalTest {
    public static void main(String[] args) {
        Integer value1=null;
        Integer value2=10;
       /*创建指定引用的Optional实例，若引用为null则快速失败返回absent()
         absent()创建引用缺失的Optional实例
        */
        Optional<Integer> a=Optional.ofNullable(value1);  // 替代了Optional.fromNullable(T)方法
        Optional<Integer> b=Optional.of(value2); //返回包含给定的非空引用Optional实例
        System.out.println(sum(a,b));
        System.out.println(a);
        System.out.println(b);
        String aStr = "张三";
        Optional<String> aStr1 = (Optional<String>) Optional.of(aStr);
        String bStr = null;
        Optional<String> bStr1 = (Optional<String>) Optional.ofNullable(bStr);  // 如果参数为null，那么运行时of方法会报空指针异常
        String cStr = "lisi";
        Optional<String> cStr1 = (Optional<String>) Optional.of(cStr);
        System.out.println(sum(aStr1,bStr1,cStr1));

    }

    private static Integer sum(Optional<Integer> a,Optional<Integer> b){
        //isPresent():如果Optional包含非null的引用（引用存在），返回true
        System.out.println("First param is present: "+a.isPresent());
        System.out.println("Second param is present: "+b.isPresent());
        Integer value1=a.orElse(0); //返回Optional所包含的引用,若引用缺失,返回指定的值
        Integer value2=b.get(); //返回所包含的实例,它必须存在,通常在调用该方法时会调用isPresent()判断是否为null
        return value1+value2;
    }

    private static String sum(Optional<String> a,Optional<String> b,Optional<String> c){
        String a1 = a.orElse("");
        System.out.println(a1);
        String a2 = b.orElse("");
        System.out.println(a2);
        String a3 = c.orElse("");
        System.out.println(a3);
        return a1+a2+a3;
    }

}
