package com.example.lzzll.kafka;

import org.assertj.core.util.Lists;

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
        System.out.println("请输入目标数：");
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
        List<String> targetList = Lists.newArrayList();
        Random random = new Random();
        for (int i = 0; i < targetNum; i++) {
            int index = random.nextInt(backGroundList.size());
            targetList.add(backGroundList.get(index));
        }
        // 开始进行游戏
        System.out.println("目标位置："+targetList);
        int guessCount = 0;
        System.out.println("游戏开始，请输入目标坐标：");
        while (targetList.size() > 0){
            Scanner scan = new Scanner(System.in);
            String guessTarget = scan.next();
            if (targetList.contains(guessTarget)){
                targetList.remove(guessTarget);
                System.out.println("正中目标,【"+guessTarget+"】已被击毁,还剩"+targetList.size()+"个目标，请再次输入");
            }else {
                System.out.println("坐标错误,请重新输入");
            }
            guessCount++;
        }
        System.out.println("目标数为:"+targetNum+",猜测次数为:"+guessCount);
    }



}
