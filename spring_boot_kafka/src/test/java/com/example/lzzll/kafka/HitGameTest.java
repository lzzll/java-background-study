package com.example.lzzll.kafka;

import org.assertj.core.util.Lists;
import org.assertj.core.util.Sets;

import java.util.*;

/**
 * @Author lf
 * @Date 2022/8/31 15:49
 * @Description:
 */
public class HitGameTest {


    public static void main(String[] args) {
        List<String> rowList = Lists.newArrayList();
        Collections.addAll(rowList,"A","B","C","D","E","F","G");
        List<String> columnList = Lists.newArrayList();
        Collections.addAll(columnList,"1","2","3","4","5","6","7");
        // 生成棋盘
        List<String> backGroundList = Lists.newArrayList();
        for (String row : rowList) {
            for (String column : columnList) {
                backGroundList.add(row+column);
            }
        }
        // 生成目标
        System.out.println("请输入目标数:");
        Scanner scanner = new Scanner(System.in);
        int targetNum = 0;
        try {
            targetNum = scanner.nextInt();
        } catch (Exception e) {
            throw new RuntimeException("请输入正确的数字");
        }
        if (targetNum <= 0){
            throw new RuntimeException("请输入大于0的正整数");
        }
        List<List<String>> targetList = Lists.newArrayList();
        Random random = new Random();
        // 确定单个目标横竖的集合
        List<Integer> locationList = Lists.newArrayList();
        Collections.addAll(locationList,0,1);
        // 确保目标直接没有交叉的地方
        Set<Integer> indexSet = Sets.newHashSet();
        for (int i = 0; i < targetNum; i++) {
            boolean flag = true;
            Integer index = null;
            while (flag){
                index = random.nextInt(backGroundList.size());
                if (!indexSet.contains(index)){
                    flag = false;
                }
            }
            //  暂时不考虑重复问题
            List<String> singleTarget = generateSingleTarget(random,locationList,backGroundList,index,indexSet);
            targetList.add(singleTarget);
        }
        // 开始进行游戏
        System.out.println("目标位置："+targetList);
        int guessCount = 0;
        System.out.println("游戏开始，请输入目标坐标:");
        while (targetList.size() > 0){
            String guessTarget = scanner.next();
            boolean hitFlag = false;
            for (int i = targetList.size() - 1; i >= 0; i--) {
                List<String> singleList = targetList.get(i);
                if (singleList.indexOf(guessTarget) != -1){
                    singleList.remove(guessTarget);
                    hitFlag = true;
                }
                if (hitFlag){
                    if (singleList.size() == 0){
                        targetList.remove(singleList);
                        System.out.println(i+"号目标已被击毁");
                    }else {
                        System.out.println("正中目标,还需要"+singleList.size()+"下才能击毁"+i+"号目标");
                    }
                    break;
                }
            }
            if (!hitFlag){
                System.out.println("坐标错误,请重新输入:");
            }
            guessCount++;
        }
        System.out.println("目标数为"+targetNum+",猜测次数为"+guessCount);
    }

    /**
     * 随机生成横竖不定的3个目标
     * @param random
     * @param locationList
     * @param backGroundList
     * @param index
     * @return
     */
    private static List<String> generateSingleTarget(Random random,List<Integer> locationList,List<String> backGroundList,int index,Set<Integer> indexSet) {
        List<String> singleTarget = Lists.newArrayList();
        int location = random.nextInt(locationList.size());
        if (location == 0){
            // 横排
            if (index%7 == 0){
                // 第一列
                singleTarget.add(backGroundList.get(index));
                singleTarget.add(backGroundList.get(index+1));
                singleTarget.add(backGroundList.get(index+2));
                Collections.addAll(indexSet,index,index+1,index+2);
            }else if (index%6 == 0){
                // 第七列
                singleTarget.add(backGroundList.get(index));
                singleTarget.add(backGroundList.get(index-1));
                singleTarget.add(backGroundList.get(index-2));
                Collections.addAll(indexSet,index,index-1,index-2);
            }else {
                // 中间列
                singleTarget.add(backGroundList.get(index));
                singleTarget.add(backGroundList.get(index-1));
                singleTarget.add(backGroundList.get(index+1));
                Collections.addAll(indexSet,index,index-1,index+1);
            }

        }else {
            // 竖排
            if (index/7 == 0){
                // 第一行
                singleTarget.add(backGroundList.get(index));
                singleTarget.add(backGroundList.get(index+7));
                singleTarget.add(backGroundList.get(index+14));
                Collections.addAll(indexSet,index,index+7,index+14);
            }else if (index/7 == 6){
                // 最后一行
                singleTarget.add(backGroundList.get(index));
                singleTarget.add(backGroundList.get(index-7));
                singleTarget.add(backGroundList.get(index-14));
                Collections.addAll(indexSet,index,index-7,index-14);
            }else {
                // 中间行
                singleTarget.add(backGroundList.get(index));
                singleTarget.add(backGroundList.get(index-7));
                singleTarget.add(backGroundList.get(index+7));
                Collections.addAll(indexSet,index,index-7,index+7);
            }
        }
        return singleTarget;
    }


}
