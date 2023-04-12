package com.example.lzzll.javastudy.socket;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @Author lf
 * @Date 2019/9/23 10:28
 * @Description:
 */
public class SocketServerUtil {
    private static final Logger log = LoggerFactory.getLogger(SocketServerUtil.class);
    private static final Integer PORT = 12345;  // 监听的端口号


    public static void main(String[] args) {
        System.out.println("服务器启动...\n");
        SocketServerUtil server = new SocketServerUtil();
        server.init();
    }

    public void init () {
        log.info("the socketserver is running!");
        try {
            //创建一个ServerSocket，port是客户端的端口
            ServerSocket serverSocket = new ServerSocket(PORT);
            while (true){
                // 方式一：使用多线程来处理通信，如果获取了一个连接，就开启一个线程来处理该连接
                Socket socket = serverSocket.accept();
                log.info("监听客户端...");
                new HandlerThread(socket);
                // 方式二：使用单线程来处理通信
//                //从请求队列中取出链接,如果只接受一次则不用使用while循环
//                Socket socket = serverSocket.accept();
//                //获取客户端信息
//                BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//                String clientContent = input.readLine();
//                //接下来可以对信息进行操作，我这里是直接打印了，你可以读取或者做其他操作
//                System.out.println(clientContent);
//                log.info("收到客户端发的消息："+clientContent);
//
//                //初始化输出流，回复客户端
//                PrintStream out = new PrintStream(socket.getOutputStream());
//                //获取键盘输出的内容
//                String s = new BufferedReader(new InputStreamReader(System.in)).readLine();
//                //将信息发送给客户端
//                out.println("open");
//
//                //关闭
//                out.close();
//                input.close();
            }
        } catch (IOException e) {
            log.error("服务器异常:" + e);
        }
    }


    private class HandlerThread implements Runnable{

        private Socket socket;

        public HandlerThread(Socket client) {
            socket = client;
            new Thread(this).start();
        }

        @Override
        public void run() {
            try {
                log.info("启动客户端通信线程..."+socket);
                // 获取客户端数据(使用bufferReader)
                BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String clientInfo = reader.readLine();
                // 使用DataInputStream
//                DataInputStream reader = new DataInputStream(socket.getInputStream());
//                String clientInfo = reader.readUTF();
                System.out.println(clientInfo);
                // 向客户端发送数据
                BufferedWriter writter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
//                DataOutputStream writter = new DataOutputStream(socket.getOutputStream());
                System.out.println("请输入回复的话，以enter结尾");
                String returnInfo = new BufferedReader(new InputStreamReader(System.in)).readLine();
                writter.write(returnInfo+"\n");
                writter.flush();
//                writter.writeUTF(returnInfo);
                // 释放资源
                writter.close();
                reader.close();
            } catch (IOException e) {
                log.info("获取客户端数据异常："+e);
            } finally {
                if (socket != null) {
                    try {
                        socket.close();
                    } catch (Exception e) {
                        socket = null;
                        System.out.println("服务端 finally 异常:" + e.getMessage());
                    }
                }
            }
        }
    }


}
