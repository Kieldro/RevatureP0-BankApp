package com.revature.project0;

import java.util.Map;
import java.util.Scanner;

import org.apache.log4j.Logger;

public class BankApp {
	private static Scanner sc = new Scanner(System.in);
	private static User u;

	final static Logger logger = Logger.getLogger(BankApp.class);

	public static void main(String[] args) throws Exception {
		// logger.all("all level");
		// logger.trace("trace level");
		// logger.debug("debug level");
		logger.info("info level - System start");
		// logger.warn("warn level");
		// logger.error("error level");
		// logger.fatal("fatal level");
		// ALL, TRACE, DEBUG, INFO, WARN, ERROR and FATAL

		loop: while (true) {
			System.out.println("Options:\n" + "1 to create a customer account\n" + "2 to create an admin account\n"
					+ "0 to exit system\n" + "Enter option: ");
			int option = sc.nextInt();
			sc.nextLine();
			logger.trace("option entered: " + option);

			switch (option) {
			case 1:
				System.out.println("Create a user account");
				createUser();
				System.out.println(u.name + " your balance is : $" + u.balance);
				changeBal();
				break;
			case 2:
				System.out.println("Create an admin account");
				createUser();
				u.admin = true;
				System.out.println("Admin account created username: " + u.name);
				approveUsers();
				break;
			case 0:
				break loop;
			}
		}

		sc.close();
		logger.trace("scanner closed");
		logger.trace("end of main.");
	}

	public static void createUser() {
		System.out.println("Enter new user name: ");

		String name = sc.nextLine();
		logger.debug("Entered name: " + name);
		logger.trace("name length: " + name.length());
		u = new User(name);
		final DBAccessor doa = DBAccessor.getInstance();
		boolean inserted = doa.insertUser(u);
		if (inserted) {
			logger.debug("User created: " + u.name);
		}else {
			logger.debug("User could NOT be created: " + u.name);
			}

	}

	private static void approveUsers() {
		System.out.println("Approve users");

		Map<String, User> m = User.users;
		if (m.isEmpty()) {
			System.out.println("No users in the system.");
			return;
		}
		System.out.println("Users in the system:");
		for (String k : m.keySet()) {
			User t = m.get(k);
			System.out.println(k + " " + t.admin);
		}

		System.out.println("Enter the name of the user to approve: ");
		String name = sc.nextLine();
		logger.debug("name: " + name);
		m.get(name).approved = true;
		System.out.println(name + " approved.");

	}

	public static void changeBal() {
		System.out.println(u.name + " your options are:");
		System.out.println("-Enter 1 for deposits");
		System.out.println("-Enter 2 for withdrawals");

		int prompt = sc.nextInt();
		logger.trace("prompt entered: " + prompt);
		if (prompt == 1) {
			System.out.println("Enter the amount to deposit: ");
			System.out.print("$");
			float deposit = sc.nextFloat();
			logger.trace("amount entered: " + deposit);
			u.deposit(deposit);
		} else if (prompt == 2) {
			System.out.println("Enter the amount to withdraw: ");
			System.out.print("$");
			float withdrawal = sc.nextFloat();
			logger.trace("amount entered: " + withdrawal);
			u.withdraw(withdrawal);
		}
		System.out.println(u.name + " your new balance is : $" + u.balance);
	}

	public static void createAdmin() {
		System.out.println("Enter new user name: ");

		String name = sc.nextLine();
		logger.trace("name entered: " + name);
		u = new User(name);
	}

	// @Override
	protected void finalize() throws Throwable {
		super.finalize();

	}
}
