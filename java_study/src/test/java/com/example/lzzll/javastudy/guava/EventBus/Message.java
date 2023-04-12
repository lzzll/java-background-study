package com.example.lzzll.javastudy.guava.EventBus;

/**
 * @Author lf
 * @Date 2019/9/29 8:32
 * @Description:
 */
public class Message {
    private String message;

    public Message(String message) {
        this.message = message;
    }

    public Message() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "Message{" +
                "message='" + message + '\'' +
                '}';
    }
}
