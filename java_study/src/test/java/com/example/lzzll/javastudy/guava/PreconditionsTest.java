package com.example.lzzll.javastudy.guava;

import com.google.common.base.Preconditions;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author lf
 * @Date 2019/9/25 18:47
 * @Description:
 */
// 其中提供了许多重要的静态校验方法，用来简化我们工作或开发中对代码的校验或预处理，能够确保代码符合我们的期望，并且能够在不符合校验条件的地方，准确的为我们显示出问题所在。
public class PreconditionsTest {

    public static void main(String[] args) {
        try {
            getValue(5);
        } catch (IndexOutOfBoundsException e){
            System.out.println(e.getMessage());
        }

        try {
            sum(4,null);
        } catch (NullPointerException e){
            System.out.println(e.getMessage());
        }

        try {
            sqrt(-1);
        } catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
        }



    }

    private static double sqrt(double input){
        Preconditions.checkArgument(input>0.0,
                "Illegal Argument passed: Negative value %s.",input);
        return Math.sqrt(input);
    }

    private static int sum(Integer a,Integer b){
        a=Preconditions.checkNotNull(a,
                "Illegal Argument passed: First parameter is Null.");
        b=Preconditions.checkNotNull(b,
                "Illegal Argument passed: First parameter is Null.");
        return a+b;
    }

    private static int getValue(int input){
        int[] data={1,2,3,4,5};
        int index=Preconditions.checkElementIndex(input,data.length,
                "Illegal Argument passed: Invalid index.");
        return data[index];
    }


    // 打印输出方法
    private static void print(Object obj) {
        System.out.println(String.valueOf(obj));
    }

    // 测试方法
    private static boolean testMethod() {
//        int num = 0;
//        boolean flag = false;
//        list.forEach((s)->{
//            if (s>num){
//                return true;
//            }
//        });
//        for (int i = 0; i < list.size(); i++) {
//            if (list.get(i)>num){
//                flag =  true;
//            }
//        }
//        return false;
        return 1 < 2;
    }

    // 测试对象
    private static Object testObject(){
        return null;
    }

    @Test
    public void testTrue(){
        try {
            // 参数的含义，第一个参数为boolean型的表达式，第二个为错误的提示信息，可以用%s将第三个参数的信息拼接上来
            Preconditions.checkState(1>2,"%s 表达式错误","121");
//            Preconditions.checkState(1 > 2, "%s is wrong", "1 > 2");
        } catch (IllegalArgumentException  e) {
            print(e.getMessage());
        }
    }

    @Test
    // 错误时可以捕获错误信息，正确的时候没有任何输出
    public void testMethodExpreesion(){
//       ArrayList<Integer> list = Lists.newArrayList();
//       list.add(-1);
       try {
           Preconditions.checkState(testMethod(),"%s is wrong", "testMethod()");
       } catch (IllegalStateException e) {
           print(e.getMessage());
       }
   }

   @Test
   public void testIndex(){
//       ArrayList<Integer> list = Lists.newArrayList(10);
//       System.out.println(list.size());
       List<Object> list = new ArrayList<>();
       for (int i = 0; i < 10; i++) {
           list.add(i);
       }
//       System.out.println(list.size());
       try {
//           Preconditions.checkElementIndex(10, list.size());  // 打印异常信息，校验是否出现索引越界异常
//           Preconditions.checkPositionIndex(10,list.size()); // 不打印异常信息
           Preconditions.checkPositionIndexes(3,11, list.size());  // 校验是否为有效索引区间
       } catch (Exception e) {
           print(e.getMessage());
       }
   }


}
