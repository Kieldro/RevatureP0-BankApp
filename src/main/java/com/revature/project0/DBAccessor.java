package com.revature.project0;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

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
					.prepareStatement("SELECT name, password, balance, admin, approved " + "FROM user_account WHERE name = ?");
			ps.setString(1, name);
			BankApp.logger.trace("getUser query executing...");
			ResultSet rs = ps.executeQuery();
			BankApp.logger.trace("query done.");
			if (rs.next()) {
				return new User(rs.getString("name"), rs.getString("password"), rs.getFloat("balance"), rs.getBoolean("admin"),
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
			// CallableStatement stmt = conn.prepareCall("{CALL update_pokemon(?, ?, ?, ?,
			// ?)}");
			PreparedStatement ps = con.prepareStatement("INSERT INTO user_account (name, password, balance, admin, approved) "
					+ "VALUES (?, ?, ?, ?, ?)");
			ps.setString(++idx, u.name);
			ps.setString(++idx, u.password);
			ps.setFloat(++idx, u.balance);
			ps.setBoolean(++idx, u.admin);
			ps.setBoolean(++idx, u.approved);

			BankApp.logger.trace("executing INSERT...");
			return ps.executeUpdate() > 0;
		} catch (SQLException e) {
			System.err.print(e.getMessage());
			System.err.println("SQL State: " + e.getSQLState());
			System.err.println("Error code: " + e.getErrorCode());
		}

		BankApp.logger.debug("INSERT user failed: " + u);
		return false;
	}

	public boolean deleteUser(User u) {
		// DELETE FROM user_account WHERE name = 'Ian B';
		try (Connection con = ConnectionUtil.getConnection()) {
			PreparedStatement ps = con.prepareStatement("DELETE FROM user_account WHERE name = ?");
			ps.setString(1, u.name);

			BankApp.logger.trace("executing DELETE...");
			return ps.executeUpdate() > 0;
		} catch (SQLException e) {
			System.err.println(e.getMessage());
			System.err.println("SQL State: " + e.getSQLState());
			System.err.println("Error code: " + e.getErrorCode());
		}

		BankApp.logger.debug("DELETE user failed: " + u);
		return false;
	}

	public boolean updateUser(User u) {
		// UPDATE user_account SET balance = 4.0, approved = 0 WHERE name = ?;
		try (Connection con = ConnectionUtil.getConnection()) {
			int idx = 0;
			PreparedStatement ps = con.prepareStatement("UPDATE user_account SET "
					+ "balance = ?, approved = ?, admin = ? WHERE name = ?");
			ps.setFloat  (++idx, u.balance);
			ps.setBoolean(++idx, u.approved);
			ps.setBoolean(++idx, u.admin);
			ps.setString (++idx, u.name);

			BankApp.logger.trace("executing UPDATE to User..." + u);
			return ps.executeUpdate() > 0;
		} catch (SQLException e) {
			System.err.println(e.getMessage());
			System.err.println("SQL State: " + e.getSQLState());
			System.err.println("Error code: " + e.getErrorCode());
		}

		BankApp.logger.debug("UPDATE user modified 0 rows: " + u);
		return false;
	}

	@Override
	public Map<String, User> getAllUsers() {
		Map<String, User> um = new HashMap<>();
		try (Connection con = ConnectionUtil.getConnection()) {
			PreparedStatement ps = con
					.prepareStatement("SELECT name, password, balance, admin, approved "
							+ "FROM user_account");
			BankApp.logger.trace("getAllUsers query executing...");
			ResultSet rs = ps.executeQuery();
			BankApp.logger.trace("query done.");
			while (rs.next()) {
				User u = new User(rs.getString("name"), 
							rs.getString("password"), 
							rs.getFloat("balance"),
							rs.getBoolean("admin"),
							rs.getBoolean("approved"));
				um.put(u.name, u);
			}
		} catch (SQLException e) {
			System.err.println(e.getMessage());
			System.err.println("SQL State: " + e.getSQLState());
			System.err.println("Error code: " + e.getErrorCode());
		}

		BankApp.logger.debug("getAll elements in map: " + um.size());
		return um;
	}
}