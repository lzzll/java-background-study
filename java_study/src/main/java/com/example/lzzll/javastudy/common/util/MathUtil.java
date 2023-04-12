package com.example.lzzll.javastudy.common.util;


import java.math.BigDecimal;

/**
 * @Author lf
 * @Date 2019/11/8 10:21
 * @Description: 数学工具类
 */
public class MathUtil {

    /**
     * 根据总数计算指定比例的数量
     * @param totalNum
     * @param rate
     * @return
     */
    public static Integer getNumByRate(Integer totalNum ,String rate){
        return (int)Math.ceil(totalNum*Double.parseDouble(rate));
    }

    /**
     * 计算合格率
     * @param passNum
     * @param totalNum
     * @return
     */
    public static String getPassRate(Integer passNum,Integer totalNum){
//        String passRate = String.valueOf(passNum.doubleValue() / totalNum.doubleValue());  // 不保留小数
        String passRate = String.format("%.2f",passNum.doubleValue() / totalNum.doubleValue());  // 保留两位小数
        return String.valueOf(passRate);
    }

    /**
     * 获取一个小数的百分数并保留两位小数
     * @param rate
     * @return
     */
    public static Double getPercentAge(Double rate){
        if (rate == null){
            return 0.00;
        }
        BigDecimal hight = new BigDecimal(rate*100);
        return hight.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
    }


    /**
     * 保留两位小数
     * @param rate
     * @return
     */
    public static Float getPercentAge2(Float rate){
        if (rate == null){
            return (float)0.00;
        }
        BigDecimal hight = new BigDecimal(rate);
        return hight.setScale(2, BigDecimal.ROUND_HALF_UP).floatValue();
    }


    /**
     * 求和
     * @return
     */
    public static Integer sumArray(int[] array, int n){

        if(array.length==0){
            return 0;
        }
        if(n == 1) {
            return array[0];
        }else {
            return array[n-1] + sumArray(array,--n);
        }
    }

    /**
     * 求和
     * @return
     */
    public static Integer sumArray(int[] array){
        if(array.length==0){
            return 0;
        }
        return sumArray(array,array.length);
    }

    /**
     * 转数据为整型数据
     * @return
     */
    public static Object getIntNum(Object obj){
        try {
            if (obj == null){
                return null;
            }
            if (obj instanceof Integer){
                return Integer.valueOf(obj.toString());
            }
            if (obj instanceof Double){
                return ((Double) obj).intValue();
            }
            String numStr = obj.toString();
            if (numStr.contains(".")){
                String num = numStr.substring(0, numStr.indexOf("."));
                return Integer.valueOf(num);
            }else {
                return Integer.valueOf(numStr);
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return obj;
    }

    public static void main(String[] args) {
//        System.out.println(sumArray(new int[]{5,9,8,5}));
        System.out.println(getPercentAge(3.125456));
    }
}
