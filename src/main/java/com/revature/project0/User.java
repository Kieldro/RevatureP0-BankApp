package com.revature.project0;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;


// bean with hash and toString()
public class User implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5539802788837931219L;
	public String name;
	public String password = "ttt";
	public float balance = 0;
	public boolean admin = false;
	public boolean approved = false;
	public static Map<String, User> users = new HashMap<>(2);		// persistent storage :P
	
	public User(String name) {
		this.name = name;
		users.put(name, this);
	}
	
	public User(String name, String password,float balance, boolean admin, boolean approved) {
		this.name = name;
		this.password = password;
		this.balance = balance;
		this.admin = admin;
		this.approved = approved;
		users.put(name, this);
	}
	
	public void deposit(float deposit) {
		balance += deposit;
	}
	
	public void withdraw(float withdrawal) {
		balance -= withdrawal;
	}

	@Override
	public String toString() {
		return "User [name=" + name + ", password=" + password + ", balance=" + balance + ", admin=" + admin
				+ ", approved=" + approved + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (admin ? 1231 : 1237);
		result = prime * result + (approved ? 1231 : 1237);
		result = prime * result + Float.floatToIntBits(balance);
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (admin != other.admin)
			return false;
		if (approved != other.approved)
			return false;
		if (Float.floatToIntBits(balance) != Float.floatToIntBits(other.balance))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		return true;
	}
	
	
}
