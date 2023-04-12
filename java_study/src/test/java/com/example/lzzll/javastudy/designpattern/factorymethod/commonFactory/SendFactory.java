package com.example.lzzll.javastudy.designpattern.factorymethod.commonFactory;

/**
 * 工厂类
 */
public class SendFactory {

    // 实现方式一：单个方法创建对象依赖于传递的参数，如果参数写错，就无法实例化对象
   /* public Sender produce(String type){
        if ("mail".equals(type)){
            return new MailSender();
        } else if ("sms".equals(type)) {
            return new SmsSender();
        } else {
            return null;
        }
    }*/

   // 实现方式二：创建多个方法分别实现对象
//    public Sender produceSmsSender(){
//        return new SmsSender();
//    }
//    public Sender produceMailSender(){
//        return new MailSender();
//    }

    // 实现方式三：静态方法模式
    public static Sender produceSmsSender(){
        return new SmsSender();
    }
    public static Sender produceMailSender(){
        return new MailSender();
    }
}

