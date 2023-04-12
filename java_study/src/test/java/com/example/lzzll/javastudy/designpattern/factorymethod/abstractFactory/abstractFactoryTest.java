package com.example.lzzll.javastudy.designpattern.factorymethod.abstractFactory;
import com.example.lzzll.javastudy.designpattern.factorymethod.abstractFactory.factory.SendMailFactory;

/**
 * 抽象工厂模式比静态工厂模式更加的易于扩展，只需要重新创建一个实例化类的工厂实现provider接口就可以，不用对原来的代码进行修改
 */
public class abstractFactoryTest {
    public static void main(String[] args) {
        Provider provider = new SendMailFactory();
        Sender sender = provider.produce();
        sender.send();
    }
}
