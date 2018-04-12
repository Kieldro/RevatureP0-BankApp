package com.revature.project0;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionUtil {
	// private static ConnectionUtil cu;

	private ConnectionUtil() {
	}

	public static void main(String[] args) {
		System.out.println(ConnectionUtil.getConnection());
	}

	public static Connection getConnection() {
		InputStream in = null;
		Properties p = new Properties();
		try {
			in = new FileInputStream("src/main/resources/db.properties");
			p.load(in);
			return DriverManager.getConnection(p.getProperty("jdbc.url"), p.getProperty("jdbc.username"),
					p.getProperty("jdbc.password"));
		} catch (SQLException e) {
			System.err.println(e.getMessage()); // prints in red!:D
			System.err.println("SQL State: " + e.getSQLState());
			System.err.println("Error code: " + e.getErrorCode());

		} catch (FileNotFoundException e) {
			System.err.println(e.getMessage());

		} catch (IOException e) {
			System.err.println(e.getMessage());

		} finally {
			try {
				in.close();
			} catch (IOException e) {
				System.err.println(e.getMessage());
			}
		}
		return null;
	}
}
