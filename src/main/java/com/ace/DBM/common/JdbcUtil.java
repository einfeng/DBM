package com.ace.DBM.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcUtil {

	private static Connection con;
	private static Statement stmt;

	private String name;
	private String url;
	private String user;
	private String password;
	private String ip;
	private String port;

	public JdbcUtil(String type) {
		
		name = PropertiesUtil.GetValueByKey(type + "_name");
		user = PropertiesUtil.GetValueByKey(type + "_user");
		password = PropertiesUtil.GetValueByKey(type + "_password");
		ip = PropertiesUtil.GetValueByKey(type + "_ip");
		port = PropertiesUtil.GetValueByKey(type + "_port");
		url = PropertiesUtil.GetValueByKey(type + "_url").replace("#server_ip#", ip).replace("#server_port#", port).replace("#server_name#", name);
		
		try {
			con = DriverManager.getConnection(url, user, password);
			stmt = con.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public int executeDdlSql(String sql) {

		int rs = 0;

		try {
			rs = stmt.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return rs;
	}

	public ResultSet executeQuerySql(String sql) {

		ResultSet rs = null;

		try {
			rs = stmt.executeQuery(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return rs;
	}
	
	public void closeJdbc()
	{
		try {
			stmt.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
