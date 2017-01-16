package com.chao.helper.tools;

import java.sql.*;

/**
 * Created by think on 2016/11/22.
 *
 * 全国后向码表类生成
 * 1、首先把供应商码表粘贴到TXT     CODE MSG
 * 2、导入数据库中
 */
public class Provider_Code_Msg {

    public static void main(String[] args) throws Exception {
        Connection conn = null;
        String sql;
        String url = "jdbc:mysql://localhost:3306/chao?user=root&password=670980756&useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=convertToNull";

        try {
            Class.forName("com.mysql.jdbc.Driver");// 动态加载mysql驱动

            System.out.println("成功加载MySQL驱动程序");
            // 一个Connection代表一个数据库连接
            conn = DriverManager.getConnection(url);
            // Statement里面带有很多方法，比如executeUpdate可以实现插入，更新和删除等
            Statement stmt = conn.createStatement();
            sql = "select * from code_llyh_code_msg";
            ResultSet rs = stmt.executeQuery(sql);// executeQuery会返回结果的集合，否则返回空值
            while (rs.next()) {
//                Thread.sleep(5000);
                //子表
//                System.out.println("LLYH_"+rs.getString(2)+"(\""+rs.getString(2)+"\",\"LLYH_"+rs.getString(2)+"\"),");
                //总表
                System.out.println("LLYH_"+rs.getString(2)+"(\"LLYH_"+rs.getString(2)+"\",\""+rs.getString(3)+"\"),");
            }
        } catch (SQLException e) {
            System.out.println("MySQL操作错误");
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            conn.close();
        }
    }
}
