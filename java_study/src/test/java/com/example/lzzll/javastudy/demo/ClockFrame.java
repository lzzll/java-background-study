package com.example.lzzll.javastudy.demo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

/**
 * @Author lf
 * @Date 2021/11/4 16:26
 * @Description:
 */

public class ClockFrame extends JFrame {
    ClockFrame() {
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 400);
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        ClockFrame frame = new ClockFrame();
        ClockPanel panel = new ClockPanel();
        frame.add(panel);
        panel.setCalendar();
        frame.setVisible(true);
    }
}


class ClockPanel extends JPanel {
    //        画h:m:s;
    Calendar calendar = Calendar.getInstance();
    int s = calendar.get(Calendar.SECOND);
    int m = calendar.get(Calendar.MINUTE);
    int h = calendar.get(Calendar.HOUR);

    public void setCalendar() {
        Timer timer = new Timer(1000, new Listener());
        timer.start();
    }
    class Listener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            s++;
            if (s == 60) {
                m++;
                s = 0;
            }
            if (m == 60) {
                h++;
                m = 0;
            }
            repaint();
        }
    }

    //run 动态
    int sx = 190;
    int sy = 70;

    ClockPanel() {
        setBackground(Color.GRAY);
    }

    public void paint(Graphics g) {
        super.paintComponent(g);
//        圆
        g.drawOval(83, 57, 220, 220);

        //画点
        g.fillOval(190, 170, 5, 5);//Center
        for (int i = 12; i > 0; i--) {
            g.fillOval((int) (190 + 100 * Math.sin(Math.PI / 6 * i)), (int) (170 + 100 * Math.cos(Math.PI / 6 * i)), 5, 5);
            String time = i + "";
            g.setFont(new Font("微软雅黑", Font.BOLD, 15));
            g.drawString(time, (int) (190 + Math.sin(Math.PI / 6 * i) * 100), (int) (170 - Math.cos(Math.PI / 6 * i) * 100));
        }
        g.setFont(new Font("微软雅黑", Font.BOLD, 20));
        String S = s > 10 ? s + "" : "0" + s;
        String M = m > 10 ? m + "" : "0" + m;
        String H = h > 10 ? h + "" : "0" + h;
        g.drawString("现在是中国时间" + H + ":" + M + ":" + S, 80, 40);

        final BasicStroke stokeLine = new BasicStroke(3.0f);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setStroke(stokeLine);
        g.setColor(Color.black);
        g.drawLine(190, 170, (int) (190 + (Math.cos((s * Math.PI / 30) - Math.PI / 2) * 100)), (int) (170 + (Math.sin((s * Math.PI / 30 - Math.PI / 2)) * 100)));
        g.setColor(Color.blue);
        g.drawLine(190, 170, (int) (190 + (Math.cos((m * Math.PI / 30 - Math.PI / 2)) * 100)), (int) (170 + (Math.sin((m * Math.PI / 30 - Math.PI / 2)) * 100)));
        g.setColor(Color.GREEN);
        g.drawLine(190, 170, (int) (190 + (Math.cos((h * Math.PI / 12)) * 100)), (int) (170 + ((Math.sin(h * Math.PI / 12)) * 100)));
    }
}

