package com.design.framework.template;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.design.framework.utils.PropertyUtils;

/**
 * 
 * @author JohnDeng
 * @datatime 2018年7月6日下午6:58:36
 */
public class DBConnection {
	private static final String path="/config/jdbc.properties";
	
	static String url;
	static String username;
	static String password ;
	static Connection connection = null;
	
	static{
		try {
			Class.forName("com.mysql.jdbc.Driver");
			setUsername(PropertyUtils.getStringPropertiesByFiltePath("username",path));
			setUrl(PropertyUtils.getStringPropertiesByFiltePath("url",path));
			setPassword(PropertyUtils.getStringPropertiesByFiltePath("password",path));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static Connection getConnection(){
		try {
			if (null == connection) {
				connection = DriverManager.getConnection(url, username,password);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return connection;
	}
	
	public static void doClose() {
		if (null != connection) {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}


	public static String getUrl() {
		return url;
	}


	public static void setUrl(String url) {
		DBConnection.url = url;
	}


	public static String getUsername() {
		return username;
	}


	public static void setUsername(String username) {
		DBConnection.username = username;
	}


	public static String getPassword() {
		return password;
	}


	public static void setPassword(String password) {
		DBConnection.password = password;
	}


	public static void setConnection(Connection connection) {
		DBConnection.connection = connection;
	}
	
	
}
