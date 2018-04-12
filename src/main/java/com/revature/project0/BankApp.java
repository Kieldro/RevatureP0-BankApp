package com.revature.project0;

import java.sql.*;
import java.sql.Connection;
//import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import org.apache.log4j.Logger;

public class BankApp {
	private static Scanner sc = new Scanner(System.in);
	private static User u;
	final static Logger logger = Logger.getLogger(BankApp.class);
	static Connection con;

	static {
		logger.trace("static block executing");
		logger.trace("creating connection");
		con = ConnectionUtil.getConnection();

		System.out.println(con);
	}

	public static void main(String[] args) {
		// logger.all("all level");
		// logger.trace("trace level");
		// logger.debug("debug level");
		logger.info("info level - System start");
		// logger.warn("warn level");
		// logger.error("error level");
		// logger.fatal("fatal level");
		// ALL, TRACE, DEBUG, INFO, WARN, ERROR and FATAL

		db();

		// System.out.println("Enter 1 to create a customer account or 2 to create an "
		// + "admin account: ");
		// int option = sc.nextInt();
		//
		// if (option == 1) {
		// createUser();
		// } else if (option == 2) {
		// createAdmin();
		// }
		// changeBal();

		// sc.close();
		// logger.trace("scanner closed");
		logger.trace("end of main.");
	}

	public static void db() {
		logger.debug("db() running");
		Statement stmt = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery("SELECT firstname, lastname FROM customer");
			printRS(rs);

			ps = con.prepareStatement("UPDATE customer SET firstname = ?, lastname = ? "
					+ "WHERE firstname = 'Emma' AND lastname = 'Jones'");
			ps.setString(1, "Julie");
			ps.setString(2, "Smith");
			int rowsModified = ps.executeUpdate();
			if (rowsModified > 0) {
				System.out.println("Update successful.");
			} else {
				System.out.println("Update failed.");
			}

			rs = stmt.executeQuery("SELECT firstname, lastname FROM customer " + "WHERE firstname ='julie'");
			printRS(rs);

		} catch (SQLException e) {
			System.err.println(e.getMessage()); // prints in red!:D
			System.err.println("SQL State: " + e.getSQLState());
			System.err.println("Error code: " + e.getErrorCode());
		} finally {
			try {
				rs.close();
				stmt.close();
				ps.close();
				con.close();

			} catch (SQLException e) {
				System.err.println(e.getMessage());
				System.err.println("SQL State: " + e.getSQLState());
				System.err.println("Error code: " + e.getErrorCode());
			}
		}

		logger.debug("db() ending");
	}

	public static void printRS(ResultSet rs) throws SQLException {
		logger.debug("Printing ResultSet");
		ResultSetMetaData rsmd = rs.getMetaData();
		int columnsNumber = rsmd.getColumnCount();
		while (rs.next()) {
			for (int i = 1; i <= columnsNumber; i++) {
				if (i > 1)
					System.out.print(",  ");
				String columnValue = rs.getString(i);
				// System.out.println(rs.getString("Firstname"));
				System.out.print(columnValue + "\t " + rsmd.getColumnName(i));
			}
			System.out.println("");
		}
	}

	public static void changeBal() {
		System.out.println(u.name + " your options are:");
		System.out.println("-Enter 1 for deposits");
		System.out.println("-Enter 2 for withdrawals");

		int prompt = sc.nextInt();
		if (prompt == 1) {
			System.out.println("Enter the amount to deposit: ");
			System.out.print("$");
			float deposit = sc.nextFloat();
			u.deposit(deposit);
		} else if (prompt == 2) {
			System.out.println("Enter the amount to withdraw: ");
			System.out.print("$");
			float withdrawal = sc.nextFloat();
			u.withdraw(withdrawal);
		}
		System.out.println(u.name + " your new balance is : $" + u.balance);
	}

	public static void createUser() {
		System.out.println("Create a user account: ");
		System.out.println("Enter new user name: ");

		String name = sc.nextLine();
		System.out.println("Entered name: " + name);
		u = new User(name);
		logger.debug("User created" + u.name);

		System.out.println(u.name + " your balance is : $" + u.balance);
	}

	public static void createAdmin() {
		System.out.println("Create an admin account: ");
		System.out.println("Enter new user name: ");

		String name = sc.nextLine();
		System.out.println("Entered name: " + name);
		u = new User(name);

		System.out.println(u.name + " your balance is : $" + u.balance);
	}

	// @Override
	protected void finalize() throws Throwable {
		super.finalize();

	}
}
