package com.revature.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import com.revature.models.Role;
import com.revature.models.User;

/**
 * The AuthService should handle login and registration for the ERS application.
 *
 * {@code login} and {@code register} are the minimum methods required; however, additional methods can be added.
 *
 * Examples:
 * <ul>
 *     <li>Retrieve Currently Logged-in User</li>
 *     <li>Change Password</li>
 *     <li>Logout</li>
 * </ul>
 */
public class AuthService {

    /**
     * <ul>
     *     <li>Needs to check for existing users with username/email provided.</li>
     *     <li>Must throw exception if user does not exist.</li>
     *     <li>Must compare password provided and stored password for that user.</li>
     *     <li>Should throw exception if the passwords do not match.</li>
     *     <li>Must return user object if the user logs in successfully.</li>
     * </ul>
     * FINISHED
     */
    public User login(String username, String password) throws Exception {
    	List<User> userList = new ArrayList<User>();
    	userList = UserService.getUserList();
    	
    	for (User user : userList) {
    		if(user.getUsername().equals(username) || user.geteMail().equals(username)) {
    			if (user.getPassword().equals(password)) {
    				return user;
				} else throw new Exception("Wrong password.");
    		} else throw new Exception("Username doesn't exist.");
		}
    	
        return null;
    }
    

    /**
     * <ul>
     *     <li>Should ensure that the username/email provided is unique.</li> 		FINISHED
     *     <li>Must throw exception if the username/email is not unique.</li>		FINISHED
     *     <li>Should persist the user object upon successful registration.</li>	FINISHED
     *     <li>Must throw exception if registration is unsuccessful.</li>			FINISHED
     *     <li>Must return user object if the user registers successfully.</li>		FINISHED
     *     <li>Must throw exception if provided user has a non-zero ID</li>			FINISHED
     * </ul>
     *
     * Note: userToBeRegistered will have an id=0, additional fields may be null.
     * After registration, the id will be a positive integer.
     * 
     */
    public User register(User userToBeRegistered) throws Exception {
    	List<User> userList = new ArrayList<User>();
    	userList = UserService.getUserList();
    	
//    	int userID = userList.size();
//    	Scanner scan = new Scanner(System.in);
//    	System.out.println("Choose your Username: ");
//    	String username = scan.nextLine();
//    	System.out.println("Choose your Password: ");
//    	String password = scan.nextLine();
//    	System.out.println("Choose your Role between Employee and Finance Manager: ");
//    	String role = scan.nextLine();
//    	// TODO: Fix that the role String converts into a Role & ask if I can change that
//    	// 		 to create a user with Input
    	
    	for (User user : userList) {
    		if(user.getUsername().equals(userToBeRegistered.getUsername()) ||
    				user.geteMail().equals(userToBeRegistered.geteMail())) {
    			throw new Exception("Username or E-Mail already exists.");
    		}
    		if(user.getId() != 0) {
    			throw new Exception("The ID is not 0.");
    		}
    		if(user.getPassword().equals(null) || user.getRole().equals(null)) {
    			throw new Exception("You forgot to provide a Password or a Role.");
    		}
		}
    	
    	userToBeRegistered.setId(userList.size());
    	userList.add(userToBeRegistered);
    	System.out.println("User registration successful!");
    	return userToBeRegistered;
    }

    /**
     * This is an example method signature for retrieving the currently logged-in user.
     * It leverages the Optional type which is a useful interface to handle the
     * possibility of a user being unavailable.
     */
    public Optional<User> exampleRetrieveCurrentUser() {
        return Optional.empty();
    }
}