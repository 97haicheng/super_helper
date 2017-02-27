package com.chao.helper.db.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 查询SQL、插入SQL生成工具类
 * @ClassName: CommonSqlGenerateTool
 */
public class CommonSqlGenerateTool {

	// 列名数组
	private String[] colnames;

	// 数据库连接
	private static final String URL =
			"jdbc:mysql://10.34.200.216:3306/qghx?useUnicode=true&characterEncoding=UTF-8";
	private static final String NAME = "qghx";
	private static final String PASS = "qghx111!";
	private static final String DRIVER = "com.mysql.jdbc.Driver";

	public static void main(String[] args) {
		new CommonSqlGenerateTool().generateSql("tablename");
	}
	
	public List<String> generateSql(String tablename){
		// 创建连接
		Connection con = null;
		// 查要生成实体类的表
		String sql = "SELECT * FROM " + tablename;
		PreparedStatement pStemt = null;
		
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		try {
			con = DriverManager.getConnection(URL, NAME, PASS);
			pStemt = con.prepareStatement(sql);
			ResultSetMetaData rsmd = pStemt.getMetaData();
			// 统计列
			int size = rsmd.getColumnCount();
			colnames = new String[size];
			for (int i = 0; i < size; i++) {
				colnames[i] = rsmd.getColumnName(i + 1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		//生成sql
		return processAllSQL(tablename);
	}

	/**
	 * 功能：生成SQL
	 */
	private List<String> processAllSQL(String tablename) {
		String query_sql = "";
		String insert_sql = "";
		
		String colname;
		String[] strArr;
		
		String ziduanStr = "";
		String wenhaoStr = "";
				
		for (int i = 0; i < colnames.length; i++) {
			// 替换掉 _ 去掉下划线并把第二个单词的首字母转为大写
			strArr = colnames[i].replace("_", "").split("_");
			colname = strArr[0];
			for (int j = 1; j < strArr.length; j++) {
				colname += strArr[j].substring(0, 1).toUpperCase() + strArr[j].substring(1);
			}
			query_sql +=  " " + colnames[i] + " " + colname + ",";
			ziduanStr += colnames[i] + ", ";
			wenhaoStr += "?, ";
		}
		//组装、打印查询SQL
		query_sql = "select" + query_sql.substring(0, query_sql.length()-1) + " from " + tablename;
//		System.out.println("query_sql:");
//		System.out.println(query_sql);
		
		String newQuery_sql = "private static String QUERY_SQL = \"" + query_sql + "\";";
		System.out.println("newQuery_sql:");
		System.out.println(newQuery_sql);

		System.out.println("----------");

		//组装、打印插入SQL
		insert_sql = "insert into " + tablename + " (" + ziduanStr.substring(0, ziduanStr.length()-2)
			+ ") values (" + wenhaoStr.substring(0, wenhaoStr.length()-2)
			+ ");";
//		System.out.println("insert_sql:");
//		System.out.println(insert_sql);
		
		String newInsert_sql = "private static String INSERT_SQL = \"" + insert_sql + "\";";
		System.out.println("newInsert_sql:");
		System.out.println(newInsert_sql);
		
		List<String> list = new ArrayList<String>();
		list.add(newQuery_sql);
		list.add(newInsert_sql);
		
		System.out.println("ok...");
		return list;
	}

}