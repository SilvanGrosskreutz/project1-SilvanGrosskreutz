package com.revature;

import com.revature.controllers.MenuController;

public class Driver {

	public static void main(String[] args) {
		System.out.println("Welcome to the Project1 Application!");
		MenuController menuController = new MenuController();
		menuController.welcomeMenu();
	}
}
