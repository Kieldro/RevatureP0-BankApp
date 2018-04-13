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

	@Override
	public boolean insertUser(User user) {
		// TODO Auto-generated method stub

		return false;
	}

	@Override
	public User getUser(String name) {
		try (Connection con = ConnectionUtil.getConnection()) {
			PreparedStatement ps = con
					.prepareStatement("SELECT name, balance, admin, approved " 
									+ "FROM user WHERE name = ?");
			ps.setString(1, name);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				return new User(rs.getString("name"), rs.getFloat("balance"), 
						rs.getBoolean("admin"), rs.getBoolean("approved"));
			}
		} catch (SQLException e) {
			System.err.println(e.getMessage());
			System.err.println("SQL State: " + e.getSQLState());
			System.err.println("Error code: " + e.getErrorCode());
		}
		return null;

	}
}