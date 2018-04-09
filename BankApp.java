package com.revature.project0;

import java.util.Scanner;

public class BankApp {
	private static Scanner sc = new Scanner(System.in);
	private static User u;

	public static void main(String[] args) {
		createUser();
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
		// System.out.println("Enter 1 to create a customer account or 2 to create an
		// admin account: ");
		System.out.println("Create a user account: ");
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
	}
}
