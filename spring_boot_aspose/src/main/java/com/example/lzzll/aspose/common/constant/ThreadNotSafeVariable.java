package com.example.lzzll.aspose.common.constant;

import lombok.Data;

/**
 * @Author lf
 * @Date 2023/9/1 15:26
 * @Description: 线程安全的变量实现
 */
@Data
public class ThreadNotSafeVariable {

    public int globalVariable; // 私有静态变量作为全局变量

}
