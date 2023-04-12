package com.example.lzzll.javastudy.socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.Socket;

/**
 * @Author lf
 * @Date 2019/9/23 10:33
 * @Description:
 */
public class SocketClientUtil {
    private static final Logger log = LoggerFactory.getLogger(SocketServerUtil.class);
    public static final Integer PORT = 12345;
    public static final String IP_ADDR = "localhost";

    /**
     * 客户端   发送TCP协议请求
     * @param
     */
    public static void main(String[] args) {
        System.out.println("客户端启动...");
        System.out.println("当接收到服务器端字符为 \"OK\" 的时候, 客户端将终止\n");
        while (true) {
            Socket socket = null;
            try {
                //创建一个流套接字并将其连接到指定主机上的指定端口号
                socket = new Socket(IP_ADDR, PORT);
                System.out.println(socket);

                //向服务器端发送数据
//                DataOutputStream writter = new DataOutputStream(socket.getOutputStream());
                BufferedWriter writter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                System.out.print("请输入: \t");
                String str = new BufferedReader(new InputStreamReader(System.in)).readLine();
//                writter.writeUTF(str);
                writter.write(str+"\n");
                writter.flush();

                //读取服务器端数据
//                DataInputStream input = new DataInputStream(socket.getInputStream());
                BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String ret = input.readLine();
//                String ret = input.readUTF();
                System.out.println("服务器端返回过来的是: " + ret);
                // 如接收到 "OK" 则断开连接
                if ("OK".equals(ret)) {
                    System.out.println("客户端将关闭连接");
                    Thread.sleep(500);
                    break;
                }

                writter.close();
                input.close();
            } catch (Exception e) {
                System.out.println("客户端异常:" + e.getMessage());
            } finally {
                if (socket != null) {
                    try {
                        socket.close();
                    } catch (IOException e) {
                        socket = null;
                        System.out.println("客户端 finally 异常:" + e.getMessage());
                    }
                }
            }
        }
    }

    // 只向服务端发送数据，而不回复
    public static void connectServerTCP (String str) {
        Socket socket = null;
        try {
            socket = new Socket(IP_ADDR, PORT);
            //接受服务端发送的数据
            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            //向服务端发送数据
            PrintStream out = new PrintStream(socket.getOutputStream());
            System.out.println("ready send info to server!!");
            out.println(str);
            System.out.println("send info to server: " + str);
            //发送后断掉连接
            out.close();
            input.close();
        } catch (Exception e) {
            log.error("客户端链接异常" + e);
        } finally {
            if (socket != null) {
                try {
                    socket.close();
                } catch (Exception e) {
                    socket = null;
                    log.error("关闭异常" + e);
                }
            }
        }
    }

}
