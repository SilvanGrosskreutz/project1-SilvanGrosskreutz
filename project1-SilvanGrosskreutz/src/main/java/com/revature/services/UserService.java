package com.revature.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.revature.models.Role;
import com.revature.models.User;

/**
 * The UserService should handle the processing and retrieval of Users for the ERS application.
 *
 * {@code getByUsername} is the only method required;
 * however, additional methods can be added.
 *
 * Examples:
 * <ul>
 *     <li>Create User</li>					FINISHED
 *     <li>Update User Information</li>		FINISHED
 *     <li>Get Users by ID</li>				FINISHED
 *     <li>Get Users by Email</li>			FINISHED
 *     <li>Get All Users</li>				FINISHED			
 * </ul>
 */
public class UserService {
	
	public static List<User> userList = new ArrayList<User>();

	/**
	 *     Should retrieve a User with the corresponding username or an empty optional if there is no match.
     */
	public Optional<User> getByUsername(String username) {
		for (User user : userList) {
			if(user.getUsername().equals(username)) {
				return Optional.of(user);
			}
		}
		return Optional.empty();
	}
	
	public void createUser(int id, String username, String password, Role role,
    		String firstName, String lastName, String eMail, String phoneNumber, String address) {
		if(!getByUsername(username).equals(null)) {
			User user = new User(id, username, password, role, firstName, lastName, eMail, phoneNumber, address);
			addUser(user);
		} else System.out.println("There already exists a User with that Username!");
	}
	
	public void createUser(int id, String username, String password, Role role) {
		if(!getByUsername(username).equals(null)) {
			User user = new User(id, username, password, role);
			addUser(user);
		}else System.out.println("There already exists a User with that Username!");
		
	}
	
	public void addUser(User user) {
		if(isUser(user)) {
			System.out.println(user + "is already in the Database!");
		} else userList.add(user);
	}
	
	public boolean isUser(User user) {
		for (int i = 0; i < userList.size(); i++) {
			if(userList.get(i).equals(user)) {
				return true;
			} 
		}
		return false;
	}
	
	public void printUsers() {
		for (User user : userList) {
			System.out.println("User ID: \t" + user.getId());
			System.out.println("Username: \t" + user.getUsername());
			System.out.println("Role: \t\t" + user.getRole());
			System.out.println("Name: \t\t" + user.getFirstName() + " " + user.getLastName());
			System.out.println("E-mail: \t" + user.geteMail());
			System.out.println("=========================");
		}
	}
	
	public User getUserById(int id) {
		for (User user : userList) {
			if(user.getId() == id) {
				return user;
			}
		}
		System.out.println("A User with this ID doesn't exist!");
		return null;
	}
	
	public User getUserByEMail(String eMail) {
		for (User user : userList) {
			if(user.geteMail() == eMail) {
				return user;
			}
		}
		System.out.println("User with this E-Mail doesn't exist!");
		return null;
	}
	
	public static List<User> getUserList(){
		return userList;
	}
	
	public void changeUsername(User user, String username) {
		if(isUser(user)) {
			user.setUsername(username);
		} else System.out.println("There is no User with "
			+ user.getUsername() 
			+ ". So you can't change the username!");
	}
	
	public void changePassword(User user, String password) {
		if(isUser(user)) {
			user.setPassword(password);
		} else System.out.println("There is no User with "
			+ user.getUsername() 
			+ ". So you can't change the password!");
	}
	
	public void changeRole(User user, Role role) {
		if(isUser(user)) {
			user.setRole(role);
		} else System.out.println("There is no User with "
			+ user.getUsername() 
			+ ". So you can't change the role!");
	}
	
	public void changeFirstName(User user, String firstName) {
		if(isUser(user)) {
			user.setFirstName(firstName);
		} else System.out.println("There is no User with the name "
			+ user.getUsername());
	}
	
	public void changeLastName(User user, String lastName) {
		if(isUser(user)) {
			user.setLastName(lastName);
		} else System.out.println("There is no User with the name "
			+ user.getUsername());
	}
	
	
	
	
	
}
