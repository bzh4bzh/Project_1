package com.revature.util;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnFactory {
//Singleton factory
//create one object , use it, get rid of it. 
	//private static instance of self
	private static ConnFactory cf= new ConnFactory(); 
	//private constructor
	private ConnFactory() {
		super(); 
	}
	//public static synchronized "getter" method
	public static synchronized ConnFactory getInstance() {
		if(cf == null) {
			cf = new ConnFactory();
		}
		return cf; 
	}
	public Connection getConnection() {
		Connection conn = null;
		Properties prop = new Properties(); 
		try {
			InputStream input = ConnFactory.class.getClassLoader().getResourceAsStream("Database.properties");
			prop.load(input);
			Class.forName(prop.getProperty("driver"));
			conn = DriverManager.getConnection("jdbc:oracle:thin:@pega1907.ccnrdso913p3.us-east-2.rds.amazonaws.com:1521:ORCL",
					"tuitionreimburse", "project1");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
		
	}
}
