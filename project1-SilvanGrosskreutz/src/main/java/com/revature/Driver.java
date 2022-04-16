package com.revature;

import java.util.Scanner;

import com.revature.services.AuthService;
import com.revature.services.UserService;

public class Driver {
	
	public static AuthService authService = new AuthService();

    public static void main(String[] args) {
    	Scanner scan = new Scanner(System.in);
    	boolean looping = true;
    	while(looping) { 
    		authService.infoRegister();
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
