package com.revature.repositories;


import com.revature.exceptions.NewUserHasNonZeroIdException;
import com.revature.exceptions.RegistrationUnsuccessfulException;
import com.revature.exceptions.UsernameNotUniqueException;
import com.revature.models.Role;
import com.revature.models.User;
import com.revature.util.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDAO {
	

    /**
     * Should retrieve a User from the DB with the corresponding username or an empty optional if there is no match.
     */
    public Optional<User> getByUsername(String username) {
    	try(Connection conn = ConnectionFactory.getInstance().getConnection()){
			String sql = "SELECT * FROM users WHERE user_name = ?";
			
			PreparedStatement statement = conn.prepareStatement(sql);
			
			statement.setString(1, username);
			
			ResultSet result = statement.executeQuery();
			
			while(result.next()) {
				User user = new User();
				user.setId(result.getInt("user_id"));
				user.setUsername(result.getString("user_name"));
				user.setPassword(result.getString("user_password"));
				if(result.getString("user_role").equals("FINANCE_MANAGER")) {
					user.setRole(Role.FINANCE_MANAGER);
				} else if (result.getString("user_role").equals("EMPLOYEE")) {
					user.setRole(Role.EMPLOYEE);
				}
				user.setFirstName(result.getString("user_first_name"));
				user.setLastName(result.getString("user_last_name"));
				user.seteMail(result.getString("user_email"));
				user.setPhoneNumber(result.getString("user_phonenumber"));
				return Optional.of(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
        return Optional.empty();
    }
    
    public List<User> getAllUser(){
	    try(Connection conn = ConnectionFactory.getInstance().getConnection()){
			String sql = "SELECT * FROM users;";
			
			Statement statement = conn.createStatement();
			
			ResultSet result = statement.executeQuery(sql);
			
			List<User> userList = new ArrayList<User>();
			
			while(result.next()) {
				User user = new User();
				user.setId(result.getInt("user_id"));
				user.setUsername(result.getString("user_name"));
				user.setPassword(result.getString("user_password"));
				if(result.getString("user_role").equals("Finance Manager")) {
					user.setRole(Role.FINANCE_MANAGER);
				} else if (result.getString("user_role").equals("Employee")) {
					user.setRole(Role.EMPLOYEE);
				}
				user.setFirstName(result.getString("user_first_name"));
				user.setLastName(result.getString("user_last_name"));
				user.seteMail(result.getString("user_email"));
				user.setPhoneNumber(result.getString("user_phonenumber"));
				
				userList.add(user);
			}
			
			return userList;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
    }

    /**
     * <ul>
     *     <li>Should Insert a new User record into the DB with the provided information.</li>
     *     <li>Should throw an exception if the creation is unsuccessful.</li>
     *     <li>Should return a User object with an updated ID.</li>
     * </ul>
     *
     * Note: The userToBeRegistered will have an id=0, and user name and password will not be null.
     * Additional fields may be null.
     */
    public User create(User userToBeRegistered) {
    	try(Connection conn = ConnectionFactory.getInstance().getConnection()){
			String sql = "INSERT INTO users (user_name, user_password, user_role, user_first_name,"
					+ " user_last_name, user_email, user_phonenumber) VALUES "
					+ "(?, ?, ?, ?, ?, ?, ?);";
			PreparedStatement statement = conn.prepareStatement(sql);
			
			int count = 0;
			
			statement.setString(++count, userToBeRegistered.getUsername());
			statement.setString(++count, userToBeRegistered.getPassword());
			statement.setString(++count, String.valueOf(userToBeRegistered.getRole()));
			statement.setString(++count, userToBeRegistered.getFirstName());
			statement.setString(++count, userToBeRegistered.getLastName());
			statement.setString(++count, userToBeRegistered.geteMail());
			statement.setString(++count, userToBeRegistered.getPhoneNumber());
			
			statement.execute();
			return getByUsername(userToBeRegistered.getUsername()).get();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
    	return null;
    }
    
    public void deleteUser(String username) {
    	try(Connection conn = ConnectionFactory.getInstance().getConnection()){
			String sql = "DELETE FROM users WHERE user_name = ?;";
			
			PreparedStatement statement = conn.prepareStatement(sql);
			
			statement.setString(1, username);
			
			statement.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		}
    }
    
    public void updateUser(User user) {
    	try(Connection conn = ConnectionFactory.getInstance().getConnection()){
			String sql = "UPDATE users SET user_password = ?,"
					+ " user_role = ?, user_first_name = ?,"
					+ " user_last_name = ?, user_email = ?, user_phonenumber = ?,"
					+ " WHERE user_name = ?;";
			PreparedStatement statement = conn.prepareStatement(sql);
			
			int count = 0;
			
			statement.setString(++count, user.getPassword());
			statement.setString(++count, String.valueOf(user.getRole()));
			statement.setString(++count, user.getFirstName());
			statement.setString(++count, user.getLastName());
			statement.setString(++count, user.geteMail());
			statement.setString(++count, user.getPhoneNumber());
			statement.setString(++count, user.getUsername());
			
			statement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
    }
}
