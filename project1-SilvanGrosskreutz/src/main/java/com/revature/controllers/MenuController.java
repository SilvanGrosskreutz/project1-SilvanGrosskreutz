package com.revature.controllers;

import java.util.Scanner;

import com.revature.repositories.UserDAO;

public class MenuController {
	
	private static Scanner scan = new Scanner(System.in);
	private UserDAO userDAO = new UserDAO();
	
	
	public void welcomeMenu() {
		
		printHomeMenu();
		
		String response = scan.nextLine();
		
		while(response != "0") {
			
		}
		
	}


	private void printHomeMenu() {
		System.out.println("What do you want to do?" +
				"1) See all Users. \n" +
				"2) See one User. \n" +
				"3) Add one User. \n" +
				"4) Update one User. \n" +
				"5) Delete one User. \n" +
				"0) Close the Application.");
		
	}
	
	
	
	
	
}
