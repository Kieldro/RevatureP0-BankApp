package com.revature.project0;

public interface DBAccess {
	public boolean insertUser(User user) ;

	public User getUser(String name) ;

	// public abstract List<User> getAllCustomers();

	// public boolean updateCustomer(User user);

	// public boolean deleteCustomer(User user);
}
