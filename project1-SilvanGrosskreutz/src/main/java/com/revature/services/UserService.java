package com.revature.services;

import java.util.List;
import java.util.Optional;

import com.revature.exceptions.NewUserHasNonZeroIdException;
import com.revature.exceptions.RegistrationUnsuccessfulException;
import com.revature.exceptions.UsernameNotUniqueException;
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

	protected UserDAO userDAO = new UserDAO();

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

	public void createUser(User user) {	
		userDAO.create(user);	
	}

	
	public void deleteUser(String username) {
		userDAO.deleteUser(username);
	}
	
	public void updateUser(User user) {
		userDAO.updateUser(user);
	}

	

}
