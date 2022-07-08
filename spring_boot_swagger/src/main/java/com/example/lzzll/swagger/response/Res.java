package com.example.lzzll.swagger.response;

import com.google.common.collect.Lists;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

/**
 * @Author lf
 * @Date 2022/7/8 10:13
 * @Description: 使用通配符定义的返回值
 */
@Data
@NoArgsConstructor
@ApiModel(value = "统一返回结果")
public class Res<T> implements Serializable{

    @ApiModelProperty(value = "响应码",position = 1,example = "200")
    private int code;

    @ApiModelProperty(value = "标识",position = 2,example = "success")
    private String message;

    @ApiModelProperty(value = "相应参数",position = 3)
    private T data;

    public Res(int code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * 静态方法可以定义泛型返回值
     * @param <T>
     * @return
     */
    public static <T> List<T> getResult(T[] param){
        List<T> result = Arrays.asList(param);
        return result;
    }






    public static void main(String[] args) {
        Res<List<String>> res = new Res<>();
        List<String> list = Lists.newArrayList();
        list.add("string");
        res.setData(list);
        System.out.println(res);
    }

}
