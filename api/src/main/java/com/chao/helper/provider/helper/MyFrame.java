package com.chao.helper.provider.helper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by think on 2017/3/6.
 */
public class MyFrame extends JFrame{
    public MyFrame(){

        JLabel jl = new JLabel("欢迎注册",SwingUtilities.CENTER);
        Font font = new Font("宋体",Font.BOLD,24);
        jl.setFont(font);
        jl.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        this.add(jl,BorderLayout.NORTH);
        font = new Font("宋体",Font.PLAIN,12);

        JLabel jl_name = new JLabel("用户名：",SwingUtilities.RIGHT);
        jl_name.setFont(font);
        JLabel jl_pass = new JLabel("密码：",SwingUtilities.RIGHT);
        jl_pass.setFont(font);

        JPanel jp_center_left = new JPanel();
        jp_center_left.setLayout(new GridLayout(5,1));
        jp_center_left.add(jl_name);
        jp_center_left.add(jl_pass);

        final JTextField jt_name = new JTextField();
        final JPasswordField jt_pass = new JPasswordField();
        jt_pass.setEchoChar('*');

        JPanel jp_center_right = new JPanel();
        jp_center_right.setLayout(new GridLayout(5,1));
        jp_center_right.add(jt_name);
        jp_center_right.add(jt_pass);

        JPanel jp_center = new JPanel();
        jp_center.setLayout(new GridLayout(1,2));
        jp_center.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 60));
        jp_center.add(jp_center_left);
        jp_center.add(jp_center_right);

        JButton jb1 = new JButton("确认");
        JButton jb2 = new JButton("返回");

        JPanel jp_south = new JPanel();
        jp_south.add(jb1);
        jp_south.add(jb2);
        jp_south.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        this.add(jp_center);
        this.add(jp_south,BorderLayout.SOUTH);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setSize(370, 280);
        this.setResizable(false);
        this.setLocationRelativeTo(null);

        jb1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                System.out.println(jt_name.getText());
                System.out.println(jt_pass.getText());

            }
        });

        jb2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                jt_name.setText("");
                jt_pass.setText("");

            }
        });
    }
}
