package com.example.lzzll.aspose.service.impl;

import com.example.lzzll.aspose.common.exception.ReTryException;
import com.example.lzzll.aspose.service.RetryableService;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author lf
 * @Date 2023/9/1 14:39
 * @Description:
 */
@Service
public class RetryableServiceImpl implements RetryableService {

    // 全局变量
//    public Map<String,String> errorDetailMap = new HashMap<>();
//    public int count = 0;

    /**
     * value：抛出我们指定异常才会重试
     * include：和value一样，默认为空，当exclude也为空时，默认所有异常
     * exclude：指定不处理的异常
     * maxAttempts：最大重试次数，默认3次，包括第一次请求也算在其中
     * backoff：重试等待策略，默认使用@Backoff，@Backoff的value默认为1000L
     * multiplier（指定延迟倍数）默认为0，表示固定暂停1秒后进行重试，如果把multiplier设置为1.5，则第一次重试为2秒，第二次为3秒，第三次为4.5秒。
     *
     * 由于是基于AOP实现，所以不支持类里自调用方法，且需引入aop依赖，需要引入“spring-boot-starter-aop”的依赖
     * @return
     * @throws Exception
     */
    @Override
    @Retryable(value = ReTryException.class,maxAttempts = 3,backoff = @Backoff(delay = 1000,multiplier = 1.5))
    public String testRetry() throws ReTryException {
        // 方法私有变量
        Map<String,String> errorDetailMap = new HashMap<>();
        int count = 0;
        System.out.println("开始调用----------");
        if (0 == 0) {
            count++;
            errorDetailMap.put(count+"","接口调用出错");
            throw new ReTryException("调用出错啦！！！",500,errorDetailMap);
        }
        System.out.println("成功调用");
        return "200";
    }


    /**
     * 回调方法
     * 方法的返回值必须与@Retryable方法一致
     * 方法的第一个参数，必须是Throwable类型的，建议是与@Retryable配置的异常一致，其他的参数，需要哪个参数，写进去即可
     * 回调方法与重试方法写在同一个实现类里面
     * @Recover注解来开启重试失败后调用的方法(注意,需跟重处理方法在同一个类中)，此注解注释的方法参数一定要是@Retryable抛出的异常，否则无法识别，可以在该方法中进行日志处理
     * @return
     */
    @Recover
    public String recover(ReTryException e) {
        System.out.println("错误原因："+e.getMsg());
        System.out.println("错误明细："+e.getErrorDetailMap());
        System.out.println("---------------回调方法执行----------------");
        //记录日志到数据库
        return "500";
    }
}
