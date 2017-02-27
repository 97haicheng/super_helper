package com.chao.helper.db.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import com.mysql.jdbc.DatabaseMetaData;

/**
 * 表实体类生成工具类
 * @ClassName: EntityGenerate
 * @Description: 表实体类生成工具类
 */
public class EntityGenerateTool {

	// 生成类所在的工程名，用于类头的注释，非重要配置
	private String comment_projectName = "lbDao";
	// 指定实体生成所在包的路径
	private String packageOutPath = "hk.linktech.flow.beans.trade";
	// 作者名字
	private String authorName = "winner";
	// 表名
	private String tablename = "_trade_notify_ch";
	// 实体类名称
	private String entityname = "TradeNotifyCh";
	// 列名数组
	private String[] colnames;
	// 列名类型数组
	private String[] colTypes;
	// 字段和表列名对应关系
	private Map<String, String> colnamesMap = new HashMap<String, String>();
	// 列名大小数组
	private int[] colSizes;
	// 表字段描述
	private Map<String, String> remark = new HashMap<String, String>();
	// 是否需要导入包java.util.*
	private boolean f_util = true;
	// 是否需要导入包java.sql.*
	private boolean f_sql = false;
	// 查询sql
	private String query_sql = "";
	// 数据库连接
	private static final String URL =
			"jdbc:mysql://10.34.200.216:3306/qghx?useUnicode=true&characterEncoding=UTF-8";
	private static final String NAME = "qghx";
	private static final String PASS = "qghx111!";
	private static final String DRIVER = "com.mysql.jdbc.Driver";

	/**
	 * 出口 TODO
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		new EntityGenerateTool();
	}
	
	/*
	 * 构造函数
	 */
	public EntityGenerateTool() {
		/*String[] strArr = tablename.split("_");
		for (int i = 1; i < strArr.length; i++) {
			entityname += strArr[i].substring(0, 1) + strArr[i].substring(1).toLowerCase();
		}*/
		// 创建连接
		Connection con = null;
		// 查要生成实体类的表
		String sql = "SELECT * FROM " + tablename;
		PreparedStatement pStemt = null;
		try {
			try {
				Class.forName(DRIVER);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			con = DriverManager.getConnection(URL, NAME, PASS);
			pStemt = con.prepareStatement(sql);
			ResultSetMetaData rsmd = pStemt.getMetaData();
			// 统计列
			int size = rsmd.getColumnCount();
			colnames = new String[size];
			colTypes = new String[size];
			colSizes = new int[size];
			for (int i = 0; i < size; i++) {
				colnames[i] = rsmd.getColumnName(i + 1);
				colTypes[i] = rsmd.getColumnTypeName(i + 1);
				if (colTypes[i].equalsIgnoreCase("datetime")) {
					f_util = true;
				}
				if (colTypes[i].equalsIgnoreCase("image") || colTypes[i].equalsIgnoreCase("text")) {
					f_sql = true;
				}
				colSizes[i] = rsmd.getColumnDisplaySize(i + 1);
			}

			// 获取字段描述
			try {
				DatabaseMetaData dbmd = (DatabaseMetaData) con.getMetaData();
				ResultSet resultSet = dbmd.getTables(null, "%", "%", new String[] {"TABLE"});
				while (resultSet.next()) {
					String tableName = resultSet.getString("TABLE_NAME");
					// System.out.println(tableName);
					if (tableName.equalsIgnoreCase(tablename)) {
						ResultSet rs = dbmd.getColumns(null, "%", tableName, "%");
						// System.out.println("表名：" + tableName + "\t\n表字段信息：");
						while (rs.next()) {
							// System.out.println("字段名："+rs.getString("COLUMN_NAME")+"\t字段注释："+rs.getString("REMARKS")+"\t字段数据类型："+rs.getString("TYPE_NAME"));
							remark.put(rs.getString("COLUMN_NAME"), rs.getString("REMARKS"));
						}
						break;
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			String content = parse(colnames, colTypes, colSizes);
			try {
				
				// System.out.println("绝对路径："+directory.getAbsolutePath());
				// System.out.println("相对路径："+directory.getCanonicalPath());
				// String path = this.getClass().getResource("").getPath();
				// System.out.println(path);
				// System.out.println("src/?/" + path.substring(path.lastIndexOf("/com/",
				// path.length())));
				// 文件生成路径
				File directory = new File("");
				StringBuffer outputPath = new StringBuffer(directory.getAbsolutePath());
				outputPath.append("\\src\\main\\java\\");
				outputPath.append(this.packageOutPath.replace(".", "\\"));
				outputPath.append("\\" + initcap(entityname) + ".java");
				FileWriter fw = new FileWriter(outputPath.toString());
				PrintWriter pw = new PrintWriter(fw);
				pw.println(content);
				pw.flush();
				pw.close();
				System.out.println("生成文件成功，路径：" + outputPath);
				System.out.println("查询sql：" + query_sql);
			} catch (IOException e) {
				e.printStackTrace();
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (null != con) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 功能：生成实体类主体代码
	 * 
	 * @param colnames
	 * @param colTypes
	 * @param colSizes
	 * @return
	 */
	private String parse(String[] colnames, String[] colTypes, int[] colSizes) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		StringBuffer sb = new StringBuffer();
		sb.append("package " + this.packageOutPath + ";\r\n\r\n");
		// 判断是否导入工具包
		if (f_util) {
			sb.append("import java.util.Date;\r\n");
		}
		if (f_sql) {
			sb.append("import java.sql.*;\r\n");
		}
		sb.append("\r\n");
		// 注释部分
		sb.append("/**\r\n");
		sb.append(" * 项目名称   ：" + comment_projectName + " \r\n");
		sb.append(" * 类名称       ：" + entityname + " \r\n");
		sb.append(" * 类描述       ：对应数据库表  " + tablename + " \r\n");
		sb.append(" * 版本号       ：v1.0" + "\r\n");
		sb.append(" * 作者&时间 ：" + this.authorName + " " + df.format(new Date()) + " \r\n");
		sb.append(" */ \r\n");
		// 实体部分
		sb.append("public class " + initcap(entityname) + " {\r\n");
		// 属性
		processAllAttrs(sb);
		// get set方法
		processAllMethod(sb);
		sb.append("}");
		// System.out.println(sb.toString());
		return sb.toString();
	}

	/**
	 * 功能：生成所有属性
	 * 
	 * @param sb
	 */
	private void processAllAttrs(StringBuffer sb) {
		String colname;
		String[] strArr;
		for (int i = 0; i < colnames.length; i++) {
			// 替换掉 _ 去掉下划线并把第二个单词的首字母转为大写
			strArr = colnames[i].replace("_", "").split("_");
			colname = strArr[0];
			for (int j = 1; j < strArr.length; j++) {
				colname += strArr[j].substring(0, 1).toUpperCase() + strArr[j].substring(1);
			}
			query_sql +=  " " + colnames[i] + " " + colname + ",";
			// 字段注释
			sb.append("\r\n\t/**");
			sb.append("\r\n\t * ").append(remark.get(colnames[i]).replace("\n", "\r\n\t * "));
			sb.append("\r\n\t */");
			sb.append("\r\n\t");
			sb.append("private " + sqlType2JavaType(colTypes[i]) + " " + colname + ";\r\n");
			// 保存对应关系
			colnamesMap.put(colname, colnames[i]);
			colnames[i] = colname;
		}
		query_sql = "SELECT" + query_sql.substring(0, query_sql.length()-1) + " FROM " + tablename;

	}

	/**
	 * 功能：生成所有方法
	 * 
	 * @param sb
	 */
	private void processAllMethod(StringBuffer sb) {

		for (int i = 0; i < colnames.length; i++) {
			sb.append("\r\n\t");
			sb.append("public " + sqlType2JavaType(colTypes[i]) + " get");
			sb.append(initcap(colnames[i]) + "() {");
			sb.append("\r\n\t\t");
			sb.append("return " + colnames[i] + ";");
			sb.append("\r\n\t");
			sb.append("}");
			sb.append("\r\n\r\n\t");
			
			sb.append("public void set" + initcap(colnames[i]));
			sb.append("(" + sqlType2JavaType(colTypes[i]) + " ");
			sb.append(colnames[i] + ") {");
			sb.append("\r\n\t\t");
			sb.append("this." + colnames[i] + " = " + colnames[i] + ";");
			sb.append("\r\n\t");
			sb.append("}");
			sb.append("\r\n");
		}

	}

	/**
	 * 功能：将输入字符串的首字母改成大写
	 * 
	 * @param str
	 * @return
	 */
	private String initcap(String str) {

		char[] ch = str.toCharArray();
		if (ch[0] >= 'a' && ch[0] <= 'z') {
			ch[0] = (char) (ch[0] - 32);
		}

		return new String(ch);
	}

	/**
	 * 功能：获得列的数据类型
	 * 
	 * @param sqlType
	 * @return
	 */
	private String sqlType2JavaType(String sqlType) {

		if (sqlType.equalsIgnoreCase("bit")) {
			// return "boolean";
			return "Boolean";
		} else if (sqlType.equalsIgnoreCase("tinyint")) {
			// return "int";
			return "Integer";
		} else if (sqlType.equalsIgnoreCase("smallint")) {
			// return "short";
			return "Short";
		} else if (sqlType.equalsIgnoreCase("int")) {
			// return "int";
			return "Integer";
		} else if (sqlType.equalsIgnoreCase("bigint")) {
			// return "long";
			return "Long";
		} else if (sqlType.equalsIgnoreCase("float")) {
			// return "float";
			return "Float";
		} else if (sqlType.equalsIgnoreCase("decimal") || sqlType.equalsIgnoreCase("numeric")
				|| sqlType.equalsIgnoreCase("real") || sqlType.equalsIgnoreCase("money")
				|| sqlType.equalsIgnoreCase("smallmoney")) {
			// return "double";
			return "Double";
		} else if (sqlType.equalsIgnoreCase("varchar") || sqlType.equalsIgnoreCase("char")
				|| sqlType.equalsIgnoreCase("nvarchar") || sqlType.equalsIgnoreCase("nchar")
				|| sqlType.equalsIgnoreCase("text")) {
			return "String";
		} else if (sqlType.equalsIgnoreCase("datetime")) {
			return "Date";
		} else if (sqlType.equalsIgnoreCase("image")) {
			return "Blod";
		}else if(sqlType.equalsIgnoreCase("BIGINT UNSIGNED")){
			return "Number";
		}

		return null;
	}


}