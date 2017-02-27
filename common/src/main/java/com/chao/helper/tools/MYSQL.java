package com.chao.helper.tools;

import java.sql.*;

/**
 * Created by think on 2016/11/22.
 *
 */
public class MYSQL {

    public static void main(String[] args) throws Exception {
        Connection conn = null;
        String sql;
        String url = "jdbc:mysql://121.40.116.249:3306/backward?user=root&password=rootLink&useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=convertToNull";
        int count = 0;

        try {
            Class.forName("com.mysql.jdbc.Driver");// 动态加载mysql驱动

            System.out.println("成功加载MySQL驱动程序");
            // 一个Connection代表一个数据库连接
            conn = DriverManager.getConnection(url);
            // Statement里面带有很多方法，比如executeUpdate可以实现插入，更新和删除等
            Statement stmt = conn.createStatement();
            sql = "SELECT _usernum FROM _vl_usertable WHERE _province = '吉林'";
            ResultSet rs = stmt.executeQuery(sql);// executeQuery会返回结果的集合，否则返回空值
            while (rs.next()) {
//                System.out.println( "SELECT _mobile FROM _order_payments WHERE SUBSTR(_mobile,1,7) = '"+rs.getString(1)+"'");
                Statement stmtX = conn.createStatement();
                String sqlX = "SELECT _mobile FROM _order_payments WHERE SUBSTR(_mobile,1,7) = '"+rs.getString(1)+"'";
                ResultSet rsX = stmtX.executeQuery(sqlX);// executeQuery会返回结果的集合，否则返回空值
                while (rsX.next()) {
                    count++;
                    System.out.println("----------------------------------------------------");
                    System.out.println(count);
                    System.out.println("----------------------------------------------------");
                }
                System.out.println(count);
            }
            System.out.println(count);
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
