package com.revature.project0;

import java.util.Scanner;

public class BankApp {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Create an account: ");
		System.out.println("Enter new user name: ");
		
		String name = sc.nextLine();
		System.out.println("Entered name: " + name);
		
	}
}
