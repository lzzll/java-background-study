package com.example.lzzll.javastudy.common.util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author LI
 * @Date 2020/9/2
 **/
public class ReflectUtil {




    /**
     *判断一个实体类对象实例的所有成员变量是否为空
     *@param obj 校验的类对象实例
     *@return List
     *@throws Exception
     */

    public static List<String> isObjectFieldEmpty(Object obj) throws Exception {
        Class<?> clazz=obj.getClass();
        Field[] fs=clazz.getDeclaredFields() ;
        List<String> list=new ArrayList<>();
        for(Field field:fs){
            field.setAccessible(true);
            if(field.get(obj)==null||field.get(obj)==""||"null".equalsIgnoreCase((String)field.get(obj))){
                String name=(String)field.getName();
                list.add(name);
            }
        }
        return list;
    }

    /**
     *getNotNullFieldMap
     *@param obj 校验的类对象实例
     *@return List
     *@throws Exception
     */

    public static Map<String, Object> getNotNullFieldMap(Object obj) throws Exception {
        Map<String, Object> map = new HashMap<>();
        Class<?> clazz = obj.getClass();
        Field[] fs = clazz.getDeclaredFields();
        for (Field field : fs) {
            field.setAccessible(true);
            Object v = field.get(obj);
            if (v != null && !"null".equalsIgnoreCase(v.toString())) {
                String name = field.getName();
                map.put(name, v);
            }
        }
        return map;
    }

    public static boolean isAllFiledEmpty(Object obj) throws IllegalAccessException {
        Class<?> clazz = obj.getClass();
        Field[] fields = clazz.getDeclaredFields();
        int i = 0, len = fields.length;
        for (Field f : fields) {
            f.setAccessible(true);
            Object v = f.get(obj);
            System.out.println(v);
            if(v == null || v == "" || "null".equalsIgnoreCase(v.toString())){
                i++;
            }
        }
        return i == len;
    }

    /**
     * 非空字段数
     * @param obj
     * @return
     * @throws IllegalAccessException
     */
    public static int countNotEmptyFiled(Object obj) throws IllegalAccessException {
        Class<?> clazz = obj.getClass();
        Field[] fields = clazz.getDeclaredFields();
        int i = 0;
        for (Field f : fields) {
            f.setAccessible(true);
            Object v = f.get(obj);
            if(v != null && v != "" && !"null".equalsIgnoreCase(v.toString())){
                i++;
            }
        }
        return i;
    }


    public static void main(String[] args) throws IllegalAccessException {
//        String orderCode = CommonUtil.getOrderCode();
//        System.out.println(orderCode);
//        String resultTrialCode = CommonUtil.getResultTrialCode();
//        System.out.println(resultTrialCode);
    }
}
