package com.weibomovie.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
	
	private static final String URL="jdbc:mysql://127.0.0.1:3306/movie?useUnicode=true&amp;characterEncoding=UTF-8";
	private static final String USER="root";
	private static final String PASSWORD="12345678";
	
	private static Connection conn=null;
	
	static {
		try { 
			Class.forName("com.mysql.jdbc.Driver"); 
			conn=DriverManager.getConnection(URL, USER, PASSWORD);
			}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}public static Connection getConnection(){
		return conn;
	}
}
