package com.example.lzzll.javastudy.common.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/**
 * Map工具类
 *
 * @author zfan
 */
public class MapUtil extends HashMap<String, Object> {

    @Override
    public MapUtil put(String key, Object value) {
        super.put(key, value);
        return this;
    }

    /**
     * 将一个集合平均分成n份
     * @param source
     * @param n
     * @param <T>
     * @return
     */
    public static <T> List<List<T>> averageAssign(List<T> source, int n){
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
