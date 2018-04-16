package com.revature.project0;

public interface DBAccess {
	public boolean insertUser(User user);

	public User getUser(String name);

	public boolean updateUser(User user);

	public boolean deleteUser(User user);

	// public abstract List<User> getAllCustomers();
}
