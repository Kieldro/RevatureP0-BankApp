package com.revature.project0;

import org.apache.log4j.Logger;

import java.util.Scanner;

public class BankApp {
	private static Scanner sc = new Scanner(System.in);
	private static User u;
	final static Logger logger = Logger.getLogger(BankApp.class);

	public static void main(String[] args) {
//		logger.all("all level");
		logger.trace("trace level");
		logger.debug("debug level");
		logger.info("info level - System start");
		logger.warn("warn level");
		logger.error("error level");
		logger.fatal("fatal level");
//		ALL, TRACE, DEBUG, INFO, WARN, ERROR and FATAL
		
		System.out.println("Enter 1 to create a customer account or 2 to create an " + "admin account: ");
		int option = sc.nextInt();

		if (option == 1) {
			createUser();
		} else if (option == 2) {
			createAdmin();
		}
		changeBal();

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
		sc.close();
		logger.trace("scanner closed");

	}
}
