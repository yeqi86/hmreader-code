package com.iflytek.hmreader.code.utils;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * jdbc工具类
 * Created: zhangj on 2016/10/28
 * @versionv 1.0
 */
public class JdbcTool {
	/**
	 * 读取表数据
	 * @param dataBase 数据库名
	 * @param tableName 表名
	 * @return
	 */
	public static List<JdbcBean> readData(String dataBase, String username, String password, String tableName){
		List<JdbcBean> properties = new ArrayList<JdbcBean>();
		JdbcBean jdbcBean = null;
		Connection conn = null;
		ResultSet rs = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/"+dataBase+"?useUnicode=true&characterEncoding=UTF-8",username, password);
			DatabaseMetaData dbmd = conn.getMetaData();
			rs = dbmd.getColumns(null, null, tableName, null);
			while (rs.next()) {
				jdbcBean = new JdbcBean();
				jdbcBean.setFieldName(genFieldName(rs.getString("COLUMN_NAME")));
				jdbcBean.setFieldType(genFieldType(rs.getString("TYPE_NAME")));
				jdbcBean.setFieldNameReal(rs.getString("COLUMN_NAME"));
				jdbcBean.setFieldTypeReal(genFieldTypeReal(rs.getString("TYPE_NAME")));
				properties.add(jdbcBean);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				if(conn != null){
					conn.close();
				}
				if(rs != null){
					rs.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return properties;
	}
	
	/**
	 * 根据表字段名获取java中的字段名
	 * @param field 字段名
	 * @return
	 */
	public static String genFieldName(String field) {
		String result = "";
		String lowerFeild = field.toLowerCase();
		String[] fields = lowerFeild.split("_");
		result += fields[0];
		if (fields.length > 1) {
			for(int i=1;i<fields.length;i++){
				result += fields[i].substring(0,1).toUpperCase() + fields[i].substring(1, fields[i].length());
			}
		}
		return result;
	}
	
	/**
	 * 根据表字段的类型生成对应的java的属性类型
	 * @param type 字段类型
	 * @return
	 */
	public static String genFieldType(String type){
		String result = "String";
		if(type.toLowerCase().indexOf("varchar") != -1){
			result = "String";
		}else if(type.toLowerCase().indexOf("bigint") != -1){
			result = "Long";
		}else if(type.toLowerCase().indexOf("int") != -1){
			result = "Integer";
		}else if(type.toLowerCase().indexOf("date") != -1 || type.toLowerCase().indexOf("time") != -1){
			result = "Date";
		}
		return result;
	}
	
	/**
	 * 根据表字段的类型生成对应的java的属性类型
	 * @param type 字段类型
	 * @return
	 */
	public static String genFieldTypeReal(String type){
		String result = "VARCHAR";
		if(type.toLowerCase().indexOf("varchar") != -1){
			result = "VARCHAR";
		}else if(type.toLowerCase().indexOf("int") != -1){
			result = "INTEGER";
		}
		return result;
	}
	

	/**
	 * 根据表面获取类名
	 * @param tableName 表名
	 * @return
	 */
	public static String getClassName(String tableName){
		String result = "";
		String lowerFeild = tableName.toLowerCase();
		String[] fields = lowerFeild.split("_");
		if (fields.length > 1) {
			for(int i=0;i<fields.length;i++){
				result += fields[i].substring(0,1).toUpperCase() + fields[i].substring(1, fields[i].length());
			}
		}
		return result;
	}

}
