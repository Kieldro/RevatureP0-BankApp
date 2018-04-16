package com.revature.project0;

import java.io.IOException;
import java.util.Map;
import java.util.Scanner;

import org.apache.log4j.Logger;

public class BankApp {
	private static Scanner sc = new Scanner(System.in);
	private static User u = null;
	final static DBAccessor dao = DBAccessor.getInstance();
	final static Logger logger = Logger.getLogger(BankApp.class);

	public static void main(String[] args) throws Exception {
		// ALL, TRACE, DEBUG, INFO, WARN, ERROR and FATAL
		logger.info("info level - System start");

		// input loop
		loop: while (true) {
			System.out.println("Options:");
			if (u == null) {
				System.out.println("1 to log into an account");
			}

			else {
				System.out.println("1 to log out of: " + u.name);
				System.out.println("4 to deposit or withdraw");

			}
			System.out.println("2 to create a customer account");
			System.out.println("3 to create an admin account");

			System.out.println("0 to exit system");
			System.out.println("Enter option: ");
			int option = sc.nextInt();
			sc.nextLine();

			logger.trace("option entered: " + option);
			switch (option) {
			// Login log out
			case 1:
				if (u != null) {
					System.out.println(u.name + " logging out...");
					u = null;
				} else {
					login();
				}
				break;
			// create customer account
			case 2:
				createCustomer();
				break;
			// create admin account
			case 3:
				createAdmin();
				break;
			// create admin account
			case 4:
				if (u == null) {
					System.out.println("invalid option, not logged in");
					break;
				}
				changeBal();
				break;
			// exit system
			case 0:
				break loop;
			}
		}

		sc.close();
		logger.trace("scanner closed");
		logger.trace("end of main.");
	}

	public static void login() throws IOException {
		System.out.println("Log in...");

		boolean authenticated = false;
		while (!authenticated) {
			System.out.println("Enter your user name: ");
			String name = sc.nextLine();
			u = dao.getUser(name);
			if (u == null) {
				System.out.println("Invalid user name: " + name);
				continue;
			}

			System.out.println("Enter your password: ");
			String password = sc.nextLine();

			if (!u.password.equals(password)) {
				System.out.println("Invalid password for : " + name);
				logger.trace("password entered : " + password);
				logger.trace("password expected: " + u.password);
			} else
				authenticated = true;
		}
		// Runtime.getRuntime().exec("clear");
		System.out.println("Welcome " + u.name);
		logger.trace("User logged in: " + u);
	}

	public static void createCustomer() {
		System.out.println("Create a customer account");
		createUser();
		System.out.println(u.name + " your balance is : $" + u.balance);

		changeBal();
	}

	public static void createAdmin() {
		System.out.println("Create an admin account");
		createUser();
		u.admin = true;
		System.out.println("Admin account created username: " + u.name);

		approveUsers();
	}

	public static void createUser() {
		String name = null;
		while (true) {
			System.out.println("Enter a new user name: ");
			name = sc.nextLine();
			if (dao.getUser(name) == null)
				break;
			System.out.println("User name already exists!");
		}
		// logger.debug("Entered name: " + name);
		// logger.trace("name length: " + name.length());
		System.out.println("Enter a password: ");
		String password = sc.nextLine();

		u = new User(name, password, 0, false, false);
		boolean inserted = dao.insertUser(u);
		if (inserted) {
			logger.debug("User created: " + u);
		} else {
			logger.debug("User could NOT be created: " + u);
		}
	}

	private static void approveUsers() {
		System.out.println("Approve users");

		Map<String, User> m = dao.getAllUsers();
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
		System.out.println(u.name + " your current balance is : $" + u.balance);
		if (!u.approved) {
			System.out.println(u.name + " are not approved for deposits and withdrawals by an admin.");
			return;
		}
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

	// @Override
	protected void finalize() throws Throwable {
		super.finalize();

	}
}
