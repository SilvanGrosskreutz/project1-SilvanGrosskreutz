package com.revature.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import com.revature.exceptions.NewUserHasNonZeroIdException;
import com.revature.exceptions.RegistrationUnsuccessfulException;
import com.revature.exceptions.UsernameNotUniqueException;
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
     * 
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
    	
    	for (User user : userList) {
			if(userToBeRegistered.getUsername().equals(user.getUsername())) {
				throw new UsernameNotUniqueException("Username already exists.");
			}
		}
    	if(userToBeRegistered.getId() != 0) {
    		throw new NewUserHasNonZeroIdException("User has to have a ID of 0.");
    	}
    	int n = userList.size() + 1;
    	User user = new User(n,userToBeRegistered.getUsername(),
    			userToBeRegistered.getPassword(), userToBeRegistered.getRole());
    	userList.add(user);
    	System.out.println("User registration successful!");
    	return user;
    }
    
    public User infoRegister() {
    	Scanner scan = new Scanner(System.in);
    	System.out.println("Choose your Username: ");
    	String username = scan.nextLine();
    	System.out.println("Choose your Password: ");
    	String password = scan.nextLine();
    	System.out.println("Choose your Role between Employee(1) and Finance Manager(2): ");
    	int role = Integer.valueOf(scan.nextLine());
    	Role role1 = null;
    	if(role == 1) {
    		role1 = Role.EMPLOYEE;
    	} else if(role == 2) {
    		role1 = Role.FINANCE_MANAGER;
    	}
    	User user = new User(0,username, password, role1);
    	AuthService authService = new AuthService();
    	try {
			authService.register(user);
		} catch (UsernameNotUniqueException e) {
			e.printStackTrace();
		} catch(NewUserHasNonZeroIdException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return user;
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
