package com.chao.helper.provider.helper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by think on 2017/3/6.
 */
public class AppHelper {

    public static void main(String[] args) {

        JFrame frame = new JFrame();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JButton btn1 = new JButton("sout 1");
        btn1.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                showInfo("sout 1");
                System.out.println(1);
            }
        });

        JButton btn2 = new JButton("sout 2");
        btn2.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                showInfo("sout 2");
                System.out.println(2);
            }
        });

        JButton btn3 = new JButton("sout 3");
        btn3.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                showInfo("sout 3");
                System.out.println(3);
            }
        });


        frame.getContentPane().setLayout(new GridLayout());
        frame.getContentPane().add(btn1);
        frame.getContentPane().add(btn2);
        frame.getContentPane().add(btn3);

        frame.setSize(800, 600);
        frame.setVisible(true);

    }

    private static void showInfo(String info){
        JFrame newFrame = new JFrame();
        newFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JOptionPane.showMessageDialog(newFrame.getContentPane(),
                info, "系统信息", JOptionPane.INFORMATION_MESSAGE);
    }

    public static String getTimestamp() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        return sdf.format(new Date());
    }
}
