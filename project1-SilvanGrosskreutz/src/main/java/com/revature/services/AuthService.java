package com.revature.services;

import java.util.List;
import java.util.Optional;

import com.revature.exceptions.NewUserHasNonZeroIdException;
import com.revature.exceptions.RegistrationUnsuccessfulException;
import com.revature.exceptions.UsernameNotUniqueException;
import com.revature.exceptions.WrongPasswordOrUsernameException;
import com.revature.models.User;
import com.revature.repositories.UserDAO;

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
	
	protected UserDAO userDAO = new UserDAO();

    /**
     * <ul>
     *     <li>Needs to check for existing users with username/email provided.</li>		FINISHED
     *     <li>Must throw exception if user does not exist.</li>						FINISHED
     *     <li>Must compare password provided and stored password for that user.</li>	FINISHED
     *     <li>Should throw exception if the passwords do not match.</li>				FINISHED
     *     <li>Must return user object if the user logs in successfully.</li>			FINISHED
     * </ul>
     * 
     */
    public User login(String username, String password){
    	List<User> userList = userDAO.getAllUser();
    	
    	for (User user : userList) {
    		if(user.getUsername().equals(username)) {
    			if (user.getPassword().equals(password)) {
    				return user;
				} else throw new WrongPasswordOrUsernameException("Wrong password.");
    		} else throw new WrongPasswordOrUsernameException("Username doesn't exist.");
		}
    	
        return null;
    }
    

    /**
     * <ul>
     *     <li>Should ensure that the username/email provided is unique.</li> 		FINISHED	
     *     <li>Must throw exception if the username/email is not unique.</li>		FINISHED
     *     <li>Should persist the user object upon successful registration.</li>	
     *     <li>Must throw exception if registration is unsuccessful.</li>			
     *     <li>Must return user object if the user registers successfully.</li>		FINISHED
     *     <li>Must throw exception if provided user has a non-zero ID</li>			FINISHED
     * </ul>
     *
     * Note: userToBeRegistered will have an id=0, additional fields may be null.
     * After registration, the id will be a positive integer.
     * 
     */
    
    public User register(User userToBeRegistered){
    	if(userToBeRegistered.getId() != 0) {
			throw new NewUserHasNonZeroIdException("User should have a ID of zero!");
		}
		String username = userToBeRegistered.getUsername();
		if(userDAO.getByUsername(username).isPresent()) {
			throw new UsernameNotUniqueException();
		}
		userDAO.create(userToBeRegistered);
	    System.out.println("User registration successful!");
	    return userDAO.getByUsername(userToBeRegistered.getUsername()).get();
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
