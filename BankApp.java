package com.revature.project0;

import java.util.Scanner;

public class BankApp {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Create an account: ");
		System.out.println("Enter new user name: ");
		
		String name = sc.nextLine();
		System.out.println("Entered name: " + name);
		User u = new User(name);
		System.out.println(name + " your options are:");
		System.out.println(name + " your balance is : $" + u.balance);
		System.out.println("-Enter 1 for deposits");
		System.out.println("-Enter 2 for withdrawals");
		
		int  prompt = sc.nextInt();
		if (prompt == 1) {
			System.out.println("Enter the amount to deposit: ");
			System.out.print("$");
			float deposit = sc.nextFloat();
			u.deposit(deposit);
		}else if (prompt == 2) {
			System.out.println("Enter the amount to withdraw: ");
			System.out.print("$");
			float withdrawal = sc.nextFloat();
			u.withdraw(withdrawal);
		}
		System.out.println(name + " your balance is : $" + u.balance);
		
		
	}
}
