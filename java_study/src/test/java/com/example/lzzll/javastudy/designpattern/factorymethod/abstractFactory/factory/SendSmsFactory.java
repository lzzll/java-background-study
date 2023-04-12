package com.example.lzzll.javastudy.designpattern.factorymethod.abstractFactory.factory;

import com.example.lzzll.javastudy.designpattern.factorymethod.abstractFactory.Provider;
import com.example.lzzll.javastudy.designpattern.factorymethod.abstractFactory.Sender;
import com.example.lzzll.javastudy.designpattern.factorymethod.abstractFactory.SmsSender;

public class SendSmsFactory implements Provider {
    @Override
    public Sender produce() {
        return new SmsSender();
    }
}
