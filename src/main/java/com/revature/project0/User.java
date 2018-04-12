package com.revature.project0;

import java.util.HashMap;
import java.util.Map;

public class User {
	String name;
	public float balance;
	public boolean admin = false;
	public boolean approved = false;
	public static Map<String, User> users = new HashMap<>(2);
	
	
	
	public User(String name) {
		this.name = name;
		users.put(name, this);
	}
	
	public void deposit(float deposit) {
		balance += deposit;
	}
	
	public void withdraw(float withdrawal) {
		balance -= withdrawal;
	}
}
