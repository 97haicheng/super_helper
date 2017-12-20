package com.chao.helper.provider.lua.todata.utils;

import java.sql.*;

/**
 * Created by QuChao on 2017/8/28.
 * Description : mysql连接工具类
 */
public class MySqlUtil {

    //String url = "jdbc:mysql://192.168.1.116:3306/backward?user=root&password=rootLink&useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=convertToNull";
    public ResultSet getResultSet(String url, String sql) throws SQLException {
        Connection conn = null;
        Statement stmt =null;
        ResultSet rs = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");// 动态加载mysql驱动
            System.out.println("成功加载MySQL驱动程序");
            // 一个Connection代表一个数据库连接
            conn = DriverManager.getConnection(url);
            // Statement里面带有很多方法，比如executeUpdate可以实现插入，更新和删除等
            stmt = conn.createStatement();
            // executeQuery会返回结果的集合，否则返回空值
            rs = stmt.executeQuery(sql);
        } catch (SQLException e) {
            System.out.println("MySQL操作错误");
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            conn.close();
        }
        return rs;
    }
}
