package com.revature.services;

import java.util.List;
import java.util.Optional;

import com.revature.models.Role;
import com.revature.models.User;
import com.revature.repositories.UserDAO;

/**
 * The UserService should handle the processing and retrieval of Users for the
 * ERS application.
 *
 * {@code getByUsername} is the only method required; however, additional
 * methods can be added.
 *
 * Examples:
 * <ul>
 * <li>Create User</li> 				FINISHED
 * <li>Update User Information</li> 
 * <li>Get Users by ID</li> 
 * <li>Get Users by Email</li> 
 * <li>Get All Users</li> 				FINISHED
 * </ul>
 */
public class UserService {

	private UserDAO userDAO = new UserDAO();

	/**
	 * Should retrieve a User with the corresponding username or an empty optional
	 * if there is no match.
	 */

	public List<User> getUserList() {
		return userDAO.getAllUser();
	}

	public Optional<User> getByUsername(String username) {
		return userDAO.getByUsername(username);
	}

	public void createUser(int id, String username, String password, Role role, String firstName, String lastName,
			String eMail, String phoneNumber, String address) {
		User user = new User(id, username, password, role, firstName, lastName, eMail, phoneNumber, address);
		if (!user.equals(null)) {
			userDAO.create(user);
		} else {
			System.out.println("Something went wrong with the Creation of a new User.");
		}

	}

	public void createUser(int id, String username, String password, Role role) {
		User user = new User(id, username, password, role);
		if (!user.equals(null)) {
			userDAO.create(user);
		} else {
			System.out.println("Something went wrong with the Creation of a new User.");
		}
	}
	
	public void deleteUser(String username) {
		userDAO.deleteUser(username);
	}
	
	public void updateUser(User user) {
		userDAO.updateUser(user);
	}

}
