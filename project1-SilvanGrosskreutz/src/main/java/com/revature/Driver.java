package com.revature;

import java.util.Scanner;

import com.revature.models.*;
import com.revature.services.AuthService;
import com.revature.services.UserService;

public class Driver {

    public static void main(String[] args) {
    	Scanner scan = new Scanner(System.in);
    	boolean looping = true;
    	while(looping) { 
    		System.out.println("Choose your E-Mail: ");
	    	String eMail = scan.nextLine();
	    	System.out.println("Choose your Username: ");
	    	String username = scan.nextLine();
	    	System.out.println("Choose your Password: ");
	    	String password = scan.nextLine();
	    	// TODO: Change Role to 1 and 2 to simplify
	    	System.out.println("Choose your Role between Employee(1) and Finance Manager(2): ");
	    	int role = Integer.valueOf(scan.nextLine());
	    	Role role1 = null;
	    	if(role == 1) {
	    		role1 = Role.EMPLOYEE;
	    	} else if(role == 2) {
	    		role1 = Role.FINANCE_MANAGER;
	    	}
	    	User user = new User(0,username, password, role1, eMail);
	    	AuthService authService = new AuthService();
	    	try {
				authService.register(user);
			} catch (Exception e) {
				e.printStackTrace();
			}
	    	System.out.println("Do you want to register more? (y/n)");
	    	String answer = scan.nextLine();
	    	if(answer.equals("n")) {
	    		looping = false;
	    	}
    	}
    	UserService userService = new UserService();
    	userService.printUsers();
    }
}
