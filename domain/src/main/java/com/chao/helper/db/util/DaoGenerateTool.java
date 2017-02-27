package com.chao.helper.db.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * 表实体类生成工具类
 * @ClassName: EntityGenerate
 * @Description: 表实体类生成工具类
 */
public class DaoGenerateTool {

	// 生成类所在的工程名，用于类头的注释，非重要配置
	private String comment_projectName = "lbDao";
	// 指定实体生成所在包的路径
	private String packageOutPath = "hk.linktech.flow.db.dao";
	// 作者名字
	private String authorName = "wh";
	// 表名
	private String tablename = "_trade_notify_ch";
	// 实体类名称
	private String entityname = "TradeNotifyCh";
	// 实体类名称
	private String objectname = "tradeNotifyCh";
		
	// 列名数组
	private String[] colnames;
	// 列名类型数组
	private String[] colTypes;
	// 列名大小数组
	private int[] colSizes;

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
		new DaoGenerateTool();
	}
	
	/*
	 * 构造函数
	 */
	public DaoGenerateTool() {
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
				colSizes[i] = rsmd.getColumnDisplaySize(i + 1);
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
				outputPath.append("\\" + initcap(entityname) + "Dao.java");
				FileWriter fw = new FileWriter(outputPath.toString());
				PrintWriter pw = new PrintWriter(fw);
				pw.println(content);
				pw.flush();
				pw.close();
				System.out.println("生成文件成功，路径：" + outputPath);
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

		sb.append("import java.util.ArrayList;\r\n");
		sb.append("import java.util.List;\r\n");
		sb.append("import org.springframework.stereotype.Repository;\r\n");
		sb.append("import org.springframework.util.Assert;\r\n");
		sb.append("import hk.linktech.flow.beans.platform." + entityname + ";\r\n");
		sb.append("import hk.linktech.flow.cache.Cache;\r\n");
		sb.append("import hk.linktech.flow.db.query.Page;\r\n");

		sb.append("\r\n");
		// 注释部分
		sb.append("/**\r\n");
		sb.append(" * 项目名称   ：" + comment_projectName + " \r\n");
		sb.append(" * 类名称       ：" + entityname + "Dao.java \r\n");
		sb.append(" * 类描述       ：" + entityname + "数据库访问层  \r\n");
		sb.append(" * 版本号       ：v1.0" + "\r\n");
		sb.append(" * 作者&时间 ：" + this.authorName + " " + df.format(new Date()) + " \r\n");
		sb.append(" */ \r\n");
		sb.append("@Repository\r\n");
		
		// 实体部分
		sb.append("public class " + initcap(entityname) + "Dao extends BaseDao<" + entityname + "> implements Cache {\r\n");
		sb.append("\r\n\t");
		sb.append("// 缓存名称\r\n\t");
		sb.append("private static String CACHE_NAME = \"" + objectname + "\";\r\n\t");
		sb.append("\r\n\t");
		
		List<String> sqlList = new CommonSqlGenerateTool().generateSql(tablename);
		sb.append("// 查询sql\r\n\t");
		sb.append(sqlList.get(0) + "\r\n\t");
		sb.append("// 插入sql\r\n\t");
		sb.append(sqlList.get(1).replace("(_id, ", "(").replace(", ?);", ");") + "\r\n\t");
		sb.append("// 更新sql\r\n\t");
		sb.append("private static String UPDATE_SQL = \"update " + tablename +  " set _createTime = now()\";\r\n\t");
		sb.append("\r\n\t");
		sb.append("@Override\r\n\t");
		sb.append("public String getCacheName() {\r\n\t");
		sb.append("\t" + "return CACHE_NAME;\r\n\t");
		sb.append("}\r\n");
		sb.append("\r\n\t");
		sb.append("/**\r\n\t");
		sb.append(" * 方法说明：通过主键ID查询记录\r\n\t");
		sb.append(" * @param	id 主键\r\n\t");
		sb.append(" * @return 实体对象\r\n\t");
		sb.append(" */ \r\n\t");
		sb.append("public " + entityname + " queryById(int id) {\r\n\t");
		sb.append("\t" + "return load(QUERY_SQL + \" where _id = ?\", id);\r\n\t");
		sb.append("}\r\n");
		sb.append("\r\n\t");
		sb.append("/**\r\n");
		sb.append("	 * 方法说明：分页查询数据结果集\r\n");
		sb.append("	 * @param	page 包含参数等信息\r\n");
		sb.append("	 * @return page对象\r\n\t");
		sb.append(" */ \r\n\t");
		sb.append("public Page searchToPage(Page page) {\r\n\t");
		sb.append("\t" + "return super.searchPageToBean(" + entityname + ".class, QUERY_SQL, page);\r\n\t");
		sb.append("}\r\n");
		sb.append("\r\n\t");
		sb.append("/**\r\n\t");
		sb.append(" * 方法说明：插入记录\r\n\t");
		sb.append(" * @param	" + objectname + " 待插入对象\r\n\t");
		sb.append(" * @return 插入对象(包括主键ID)\r\n\t");
		sb.append(" */ \r\n\t");
		sb.append("public " + entityname + " insert(" + entityname + " " + objectname + ") {\r\n\t");
		sb.append("\t" + "Object[] params = new Object[] { ");
		
		String colname;
		String[] strArr;
		//保存字段名与GET方法的关系
		Map<String, String> map = new TreeMap<String, String>();
		StringBuffer buffer = new StringBuffer();
		String insertData = "";
		for (int i = 0; i < colnames.length; i++) {
			
			// 替换掉 _ 去掉下划线并把第二个单词的首字母转为大写
			strArr = colnames[i].replace("_", "").split("_");
			colname = strArr[0];
			for (int j = 1; j < strArr.length; j++) {
				colname += strArr[j].substring(0, 1).toUpperCase() + strArr[j].substring(1);
			}
			
			if("id".toUpperCase().equals(colname.toUpperCase())){
				continue;
			}
			buffer.append(objectname + ".get");
			buffer.append(initcap(colname) + "(), ");
			
			if(i % 4 == 2){
				buffer.append("\r\n\t\t\t");
			}
			
			if(i == colnames.length - 1){
				insertData = buffer.substring(0, buffer.length() - 2);
				insertData += "};\r\n";
			}

//			colnames[i] = colname;
			//字段名，GET方法
			String getMethod = ".get" + initcap(colname) + "()";
			if(!"_id".toUpperCase().equals(colnames[i].toUpperCase())){
				map.put(colnames[i], getMethod);
			}
		}
		sb.append(insertData);
		
		sb.append("\r\n\t");
		sb.append("\t" + objectname + ".setId(dbUtilsTemplate.insertForKeys(INSERT_SQL, params).intValue());\r\n\t");
		sb.append("\t" + "return " + objectname + ";\r\n\t");
		sb.append("}\r\n");
		sb.append("\r\n\t");
		sb.append("/**\r\n\t");
		sb.append(" * 方法说明：更新记录\r\n\t");
		sb.append(" * @param	" + objectname + " 待更新对象\r\n\t");
		sb.append(" * @return 更新后的对象\r\n\t");
		sb.append(" */\r\n\t");
		sb.append("public " + entityname + " update(" + entityname + " " + objectname + ") {\r\n\t");
		sb.append("\t" + "Assert.notNull(" + objectname + ");\r\n\t");
		sb.append("\t" + "Assert.notNull(" + objectname + ".getId());\r\n");
		sb.append("\r\n\t");
		sb.append("\t" + "StringBuilder sql = new StringBuilder();\r\n\t");
		sb.append("\t" + "sql.append(UPDATE_SQL);\r\n");
		sb.append("\r\n\t");
		sb.append("\t" + "List<Object> paramList = new ArrayList<>();\r\n\t");
		sb.append("\t" + "// 更新非空值属性\r\n\t");

		for(String theKey : map.keySet()){
			sb.append("\t" + "if (" + objectname + map.get(theKey) + " != null) {\r\n\t");
			sb.append("\t\t" + "sql.append(\", " + theKey + " = ? \");\r\n\t");
			sb.append("\t\t" + "paramList.add(" + objectname + map.get(theKey) + ");\r\n\t");
			sb.append("\t" + "}\r\n\t");
		}

		sb.append("\t" + "sql.append(\" where \");\r\n\t");
		sb.append("\t" + "if (" + objectname + ".getId() != null) {\r\n\t");
		sb.append("\t\t" + "sql.append(\"_id = ? \");\r\n\t");
		sb.append("\t\t" + "paramList.add(" + objectname + ".getId());\r\n\t");
		sb.append("\t" + "}\r\n");
		sb.append("\r\n\t");
		sb.append("\t" + "dbUtilsTemplate.update(sql.toString(), paramList.toArray());\r\n\t");
		sb.append("\t" + "return queryById(" + objectname +".getId());\r\n\t");
		sb.append("}\r\n");
		sb.append("\r\n");
		sb.append("}\r\n");

//		System.out.println(sb.toString());
		return sb.toString();
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

}