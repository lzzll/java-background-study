package com.example.lzzll.javastudy.socket.chatdemo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * @Author lf
 * @Date 2019/9/23 17:21
 * @Description:
 */
public class Client {
    private Socket s;
    private BufferedReader br;
    private PrintWriter out;
    private boolean flag = true;

    public static void main(String[] args) {
        new Client().stratUp();
    }

    private void stratUp() {
        BufferedReader sbr = null;
        try {
            s = new Socket("127.0.0.1", 5858);
            out = new PrintWriter(s.getOutputStream(), true);
            br = new BufferedReader(new InputStreamReader(s.getInputStream()));
//            out.println("老林");
			out.println("老周");
//			out.println("老王");
//            out.println("老万");
            sbr = new BufferedReader(new InputStreamReader(System.in));
            new Thread(new ClientThread()).start();
            String str = null;
            while (flag && (str=sbr.readLine())!=null) {
                if (!flag) break;
                out.println(str);
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (s != null) s.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (sbr != null) s.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void receive() {
        try {
            String rs = br.readLine();
//            String editStr = rs.split("]")[1];
            if (rs.equalsIgnoreCase("disconnect")) {
                System.out.println(rs);
                flag = false;
                System.out.println("点击回车退出");
            }
            System.out.println(rs);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private class ClientThread implements Runnable {

        @Override
        public void run() {
            while (true) {
                if (!flag) break;
                receive();
            }
        }

    }

}

