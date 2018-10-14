package com.information.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
//import com.mysql.jdbc.Statement;
//import com.sun.corba.se.pept.transport.Connection;

public class JDBC {
	private static String driver="com.mysql.jdbc.Driver";	// 数据库驱动
	private static String url="jdbc:mysql://localhost:3306/information";	// 数据库地址
	private static String username="root";	// 数据库账号
	private static String password="root";	// 数据库密码
	
	// 静态代码块反射出 mysql.jdbc.Driver 这个类
	static {
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException("数据库驱动加载失败");
		}
	}
	
	// 获得一个 Connection
	public static Connection getConnection() {
		Connection con = null;
		try {
			con = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("数据库地址或者账号密码错误");
		}
		
		return con;
	}
	
	/**
	 * 	增删改
	 **/
	public static int executeUpdate(String sql,Object[] param) {
		int number = 0;
		Connection con = null;
		PreparedStatement pt = null;
		
		try {
			 con = getConnection();
			 pt = con.prepareStatement(sql);
			 for (int i = 0; i < param.length; i++) {
				 pt.setObject(i+1, param[i]);
			 }
			 number = pt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("增删改出现错误，检查 sql 代码");
		} finally {
			close(con);
			close(pt);
			return number;
		}
		
	}
	
	
	
	/*
	 * con 关闭
	 */
	public static void close(Connection con) {
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("con 关闭错误");
		}
	}
	/*
	 * sta 关闭
	 */
	public static void close(PreparedStatement pt) {
		try {
			pt.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("pt 关闭错误");
		}
	}
	/*
	 * res 关闭
	 */
	public static void close(ResultSet res) {
		try {
			res.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("res 关闭错误");
		}
	}
	
}
