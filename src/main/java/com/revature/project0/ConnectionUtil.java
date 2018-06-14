package com.revature.project0;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

//import org.apache.log4j.Logger;		// old log4j 1.2

public class ConnectionUtil {
	public final static Logger log = LogManager.getLogger();

	private ConnectionUtil() { // static methods only, no instantiation needed
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

	public static void printRS(ResultSet rs) throws SQLException {
		log.debug("Printing ResultSet");
		ResultSetMetaData rsmd = rs.getMetaData();
		int columnsNumber = rsmd.getColumnCount();
		while (rs.next()) {
			for (int i = 1; i <= columnsNumber; i++) {
				if (i > 1)
					System.out.print(",  ");
				String columnValue = rs.getString(i);
				System.out.print(columnValue + "\t " + rsmd.getColumnName(i));
			}
			System.out.println("");
		}
	}
}
