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
 *     <li>Create User</li>
 *     <li>Update User Information</li>
 *     <li>Get Users by ID</li>
 *     <li>Get Users by Email</li>
 *     <li>Get All Users</li>
 * </ul>
 */
public class UserService {
	
	public List<User> userList = new ArrayList<User>();

	/**
	 *     Should retrieve a User with the corresponding username or an empty optional if there is no match.
     */
	public Optional<User> getByUsername(String username) {
		return Optional.empty();
	}
	
	public void createUser(int id, String username, String password, Role role,
    		String firstName, String lastName, String eMail, String phoneNumber, String address) {
		for (int i = 0; i < userList.size(); i++) {
			
		}
		User user = new User(id, username, password, role, firstName, lastName, eMail, phoneNumber, address);
		addUser(user);
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
}
