package com.revature.models;

/**
 * This concrete User class can include additional fields that can be used for
 * extended functionality of the ERS application.
 *
 * Example fields:
 * <ul>
 *     <li>First Name</li>
 *     <li>Last Name</li>
 *     <li>Email</li>
 *     <li>Phone Number</li>
 *     <li>Address</li>
 * </ul>
 *
 */
public class User extends AbstractUser {
	
	private String firstName;
	private String lastName;
	private String eMail;
	private String phoneNumber;
	private String address;

    public User() {
        super();
    }

    /**
     * This includes the minimum parameters needed for the {@link com.revature.models.AbstractUser} class.
     * If other fields are needed, please create additional constructors.
     */
    public User(int id, String username, String password, Role role) {
        super(id, username, password, role);
    }
    
    public User(int id, String username, String password, Role role,
    		String firstName, String lastName, String eMail, String phoneNumber, String address) {
        super(id, username, password, role);
        this.firstName = firstName;
        this.lastName = lastName;
        this.eMail = eMail;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }
}
