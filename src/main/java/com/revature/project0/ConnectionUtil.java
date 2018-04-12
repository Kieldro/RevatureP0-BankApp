package com.revature.project0;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {
//	private static ConnectionUtil cu;

	private ConnectionUtil() {
	}

	public static void main(String[] args) {
		System.out.println(ConnectionUtil.getConnection());
	}

	public static Connection getConnection() {
		try {
			return DriverManager.getConnection(
					"jdbc:oracle:thin:@keodb.cgd7zwirysy1.us-east-2.rds.amazonaws.com:1521:orcl",
					"chinook", "p4ssw0rd");
		} catch (SQLException e) {
			System.err.println(e.getMessage()); // prints in red!:D
			System.err.println("SQL State: " + e.getSQLState());
			System.err.println("Error code: " + e.getErrorCode());

		}
		return null;
	}
}
