package com.javawebdemo.bigbest.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import com.javawebdemo.bigbest.entities.User;

public class UserDao {
	String sql;
	User user = new User();
	ResultSet resultset = null;

	public User userLogin(User user) throws SQLException {
		sql = "select * from user where name='" + user.getName()
				+ "' and password='" + user.getPassword() + "'";
		resultset = jdbcExecute(sql);
		if (resultset.next()) {
			try {

				this.user.setId(resultset.getInt("id"));
				this.user.setName(resultset.getString("name"));
				this.user.setPassword(resultset.getString("password"));
				this.user.setAge(resultset.getInt("age"));
				this.user.setPrice(resultset.getDouble("price"));

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		else {
			this.user=null;
		}
		return this.user;
	}

	private ResultSet jdbcExecute(String sql) {
		String user = "root";
		String password = "200777";
		String url = "jdbc:mysql://localhost:3306/javaweb";
		String driver = "com.mysql.jdbc.Driver";
		ResultSet resultset = null;
		Statement statement;
		try {
			try {
				Class.forName(driver);
			} catch (ClassNotFoundException e) {
				System.out.println("驱动加载错误:" + e.getMessage());
				e.printStackTrace();
			}
			Connection connection = DriverManager.getConnection(url, user,
					password);
			statement = connection.createStatement();
			resultset = statement.executeQuery(sql);
		} catch (SQLException e) {
			System.out.println("数据库操作错误:：" + e.getMessage());
			e.printStackTrace();
		}
		
		return resultset;

	}
}
