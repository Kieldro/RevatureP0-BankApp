package com.revature.project0;

public class User {
	String name;
	
	public float balance;
	public User(String name) {
		this.name = name;
	}
	
	public void deposit(float deposit) {
		balance += deposit;
	}
	
	public void withdraw(float withdrawal) {
		balance -= withdrawal;
	}
}
