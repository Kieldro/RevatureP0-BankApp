package com.revature.project0;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBAccessor implements DBAccess {
	private static DBAccessor instance;

	private DBAccessor() {
	}

	public static DBAccessor getInstance() {
		if (instance == null) {
			instance = new DBAccessor();
		}
		return instance;
	}

	public User getUser(String name) {
		try (Connection con = ConnectionUtil.getConnection()) {
			PreparedStatement ps = con
					.prepareStatement("SELECT name, balance, admin, approved " + "FROM user_account WHERE name = ?");
			ps.setString(1, name);
			BankApp.logger.trace("query executing...");
			ResultSet rs = ps.executeQuery();

			BankApp.logger.trace("query done.");
			if (rs.next()) {
				return new User(rs.getString("name"), rs.getFloat("balance"), rs.getBoolean("admin"),
						rs.getBoolean("approved"));
			}
		} catch (SQLException e) {
			System.err.println(e.getMessage());
			System.err.println("SQL State: " + e.getSQLState());
			System.err.println("Error code: " + e.getErrorCode());
		}

		BankApp.logger.debug("No user found (Result set empty)");
		return null;

	}

	public boolean insertUser(User u) {
		try (Connection con = ConnectionUtil.getConnection()) {
			// INSERT INTO user_account VALUES ('Ian', 123.45, 1, 1);
			int idx = 0;
			// CallableStatement stmt = conn.prepareCall("{CALL update_pokemon(?, ?, ?, ?, ?)}");
			PreparedStatement ps = con
					.prepareStatement("INSERT INTO user_account VALUES (?, ?, ?, ?)");
			ps.setString (++idx, u.name);
			ps.setFloat  (++idx, u.balance);
			ps.setBoolean(++idx, u.admin);
			ps.setBoolean(++idx, u.approved);
			BankApp.logger.trace("executing insert...");
			
			return ps.executeUpdate() > 0;
		} catch (SQLException e) {
			System.err.println(e.getMessage());
			System.err.println("SQL State: " + e.getSQLState());
			System.err.println("Error code: " + e.getErrorCode());
		}

		// BankApp.logger.debug("No user found (Result set empty)");

		return false;
	}
}