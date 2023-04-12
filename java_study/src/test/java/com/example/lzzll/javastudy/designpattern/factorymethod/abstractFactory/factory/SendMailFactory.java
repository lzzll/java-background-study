package com.example.lzzll.javastudy.designpattern.factorymethod.abstractFactory.factory;

import com.example.lzzll.javastudy.designpattern.factorymethod.abstractFactory.MailSender;
import com.example.lzzll.javastudy.designpattern.factorymethod.abstractFactory.Provider;
import com.example.lzzll.javastudy.designpattern.factorymethod.abstractFactory.Sender;

public class SendMailFactory implements Provider {
    @Override
    public Sender produce() {
        return new MailSender();
    }
}
