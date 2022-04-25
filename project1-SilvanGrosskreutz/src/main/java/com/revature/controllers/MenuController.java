package com.revature.controllers;

import java.util.List;
import java.util.Scanner;

import com.revature.models.Role;
import com.revature.models.User;
import com.revature.repositories.UserDAO;

public class MenuController {
	
	private static Scanner scan = new Scanner(System.in);
	private UserDAO userDAO = new UserDAO();
	
	
	public void welcomeMenu() {
		
		printHomeMenu();
		
		String response = scan.nextLine();
		
		while(!response.equals("0")) {
			switch(response) {
				case "1":
					System.out.println("Here is a list of all Users:");
					List<User> userList = userDAO.getAllUser();
					for (User user : userList) {
						System.out.println(user);
					}
					printHomeMenu();
					response = scan.nextLine();
					break;
				case "2":
					System.out.println("What is the Username of the user you want to see?");
					response = scan.nextLine();
					System.out.println(userDAO.getByUsername(response));
					printHomeMenu();
					response = scan.nextLine();
					break;
				case "3":
					User user = userRegistration();
					userDAO.create(user);
					printHomeMenu();
					response = scan.nextLine();
					break;
				case "4":
					System.out.println("What is the Username of the user you want to update?");
					String name = scan.nextLine();
					System.out.println("What is the Password for that User?");
					String password = scan.nextLine();
					userUpdate(name, password);
					printHomeMenu();
					response = scan.nextLine();
					break;
				case "5":
					System.out.println("What is the Username of the User you want to delete?");
					String username = scan.nextLine();
					List<User> userList2 = userDAO.getAllUser();
					for (User user2 : userList2) {
						if(user2.getUsername() == username) {
							userDAO.deleteUser(username);
						} else System.out.println("User doesnt exist.");
					}
					System.out.println("User successfully deleted");
					printHomeMenu();
					response = scan.nextLine();
					break;
				default:
					System.out.println("Invalid Input!");
					printHomeMenu();
					response = scan.nextLine();
					break;
			}	
		}	
	}


	private User userUpdate(String username, String password) {
		List<User> userList = userDAO.getAllUser();
		for (User user : userList) {
			if(user.getUsername() == username) {
				if(user.getPassword() == password) {
					System.out.println("You successfully proved that you are the user!");
					// TODO Methods for changing firstname, lastname, etc.
				} 
			} else System.out.println("Either the User doesn't exist or the password is wrong.");
		}
		return null;
		
		
	}

	// TODO: userRegistration output void, user zur datenbank in der methode
	private User userRegistration() {
		System.out.println("What is your Username?");
		String username = scan.nextLine();
		List<User> userList = userDAO.getAllUser();
		for (User user : userList) {
			if(user.getUsername().equals(username)) {
				System.out.println("This username already exists. Choose another one!");
				return null;
			}
		}
		User userToBeRegistered = new User();
		userToBeRegistered.setUsername(username);
		System.out.println("What is your password?");
		String password = scan.nextLine();
		userToBeRegistered.setPassword(password);
			
		boolean loop = true;
		while(loop) {
			System.out.println("What is your role? 1) Employee, 2) Finance Manager");
			String role = scan.nextLine();	
			if(role.equals("1")) {
				userToBeRegistered.setRole(Role.EMPLOYEE);
				loop = false;
			} else if (role.equals("2")) {
				userToBeRegistered.setRole(Role.FINANCE_MANAGER);
				loop = false;
			} else {
				System.out.println("Invalid Input.");
			}
		}	
		System.out.println("Registration successfully!");
		return userToBeRegistered;
	}


	private void printHomeMenu() {
		System.out.println("What do you want to do?\n" +
				"1) See all Users. \n" +
				"2) See one User. \n" +
				"3) Add one User. \n" +
				"4) Update one User. \n" +
				"5) Delete one User. \n" +
				"0) Close the Application.");
		
	}
	
	
	
	
	
}
