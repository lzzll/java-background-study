package com.example.lzzll.javastudy.demo;

import com.google.common.collect.Lists;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @Author lf
 * @Date 2019/10/14 12:38
 * @Description:
 */
public class FinalListTest {

    public static void main(String[] args) {
        ArrayList<String> list = Lists.newArrayList();
        list.add("a");
        method(list);
        System.out.println("list = " + list);
    }

    public static void method(List<String> list){
        final List<String> flist = list;
        list.add("b");
    }

    @Test
    public void test1(){
        final ArrayList<String> list = Lists.newArrayList();
        list.add("a");
        System.out.println("list = " + list);

//        final String str = "java";
//        str += "python";
//        System.out.println(str);

    }

    @Test
    // 一维数组
    public void test2(){
       final int intArr[] = {1,2,3};
//       int[] intArr = {1,2,3};  // 静态初始化
//        int[] intArr = new int[3];  // 动态初始化
       intArr[2] = 4;
        for (int i = 0; i < intArr.length; i++) {
            System.out.println("intArr[i] = " + intArr[i]);
        }
    }

    @Test
    // 二维数组
    public void test3(){
        int[][] arr1=new int[2][3];
        int[][] arr2={{1,2},{2,3},{3,4}};
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 3; j++) {
                arr1[i][j]=(i+1)*(j+1);
            }
        }
//        seeArr(arr1,2,3);
//        seeArr(arr2,3,2);

        int[][] resultArr = new int[2][3];
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 3; j++) {
                for (int k = 0; k < 3; k++) {
                    resultArr[i][j]=arr1[i][j]*arr2[k][i];
                }
            }
        }
        seeArr(resultArr,2,3);
    }

    public static void seeArr(int[][] arr,int iRows,int iCols){
        for (int i = 0; i < iRows; i++) {
            for (int j = 0; j < iCols; j++) {
                System.out.println("arr["+i+"]["+j+"]:"+arr[i][j]);
            }
        }
    }

    @Test
    public void test4(){
        ArrayList<String> lists = Lists.newArrayList("录入员", "标定员", "解析员");
        boolean flag = lists.contains("标定员");
        System.out.println(flag);
    }

    @Test
    public void test6(){
        List<Integer> list1 = Lists.newArrayList();
        List<Integer> list2 = Lists.newArrayList();
        Collections.addAll(list1,1,2,3);
        Collections.addAll(list2,1,2,3,4,5,6);
        List<Integer> list3 = Lists.newArrayList();

        // 可以操作
        for (int i = 0; i < list1.size(); i++) {
            for (int i1 = 0; i1 < list2.size(); i1++) {
                if (list2.get(i1)==list1.get(i)){
                    list2.remove(i1);
                }
            }
        }

        // 报错
//        for (Integer integer : list1) {
//            for (Integer integer1 : list2) {
//                if (integer1.equals(integer)){
//                    list2.remove(integer1);
//                }
//            }
//        }

        // 数据不完整
//        Iterator<Integer> iterator1 = list1.iterator();
//        Iterator<Integer> iterator2 = list2.iterator();
//        while (iterator1.hasNext()){
//            Integer next1 = iterator1.next();
//            while (iterator2.hasNext()){
//                Integer next2 = iterator2.next();
//                if (next2.equals(next1)){
//                    iterator2.remove();
//                }
//            }
//        }

        System.out.println(list2);

    }

    @Test
    public void test9(){
        List<String> strList = new ArrayList<String>();
        List<String> strList2 = new ArrayList<String>();
        for(int i = 0; i < 10; i ++) {
            strList.add("aaa>>" + i);
            strList2.add("aaa>>" + (10 - i));
        }
        //求出并集
//        strList2.removeAll(strList);
//        strList2.addAll(strList);
//        System.out.println("并集大小：" + strList2.size());
        // 求出交集
        strList2.retainAll(strList);

        for(int i = 0; i < strList2.size(); i++) {
            System.out.println(strList2.get(i));
        }

    }

    @Test
    public void test10(){
        ArrayList<Integer> list = Lists.newArrayList();
        for (int i = 0; i < 14; i++) {
            list.add(i);
        }
        List<List<Integer>> result = averageAssign(list, 4);
        for (int i = 0; i < result.size(); i++) {
            System.out.println(result.get(i));
        }
    }

    public static <T> List<List<T>> averageAssign(List<T> source,int n){
        List<List<T>> result=new ArrayList<List<T>>();
        int remaider=source.size()%n; //(先计算出余数)
        int number=source.size()/n; //然后是商
        int offset=0;//偏移量
        for(int i=0;i<n;i++){
            List<T> value=null;
            if(remaider>0){
                value=source.subList(i*number+offset, (i+1)*number+offset+1);
                remaider--;
                offset++;
            }else{
                value=source.subList(i*number+offset, (i+1)*number+offset);
            }
            result.add(value);
        }
        return result;
    }

}
