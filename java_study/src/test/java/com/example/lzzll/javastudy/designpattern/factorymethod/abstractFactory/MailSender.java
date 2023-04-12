package com.example.lzzll.javastudy.designpattern.factorymethod.abstractFactory;

public class MailSender implements Sender {
    @Override
    public void send() {
        System.out.println("this is a mailSender!");
    }
}
