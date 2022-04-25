package com.revature.models;

import java.util.Objects;

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
    
    public User(int id, String username, String password, Role role, String eMail) {
        super(id, username, password, role);
        this.eMail = eMail;
    }
    
    public User(int id, String username, String password, Role role, String eMail,
    		String firstName) {
        super(id, username, password, role);
        this.eMail = eMail;
        this.firstName = firstName;
    }
    
    public User(int id, String username, String password, Role role,
    		String firstName, String lastName, String eMail, String phoneNumber, String address) {
        super(id, username, password, role);
        this.firstName = firstName;
        this.lastName = lastName;
        this.seteMail(eMail);
        this.setPhoneNumber(phoneNumber);
        this.setAddress(address);
    }

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String geteMail() {
		return eMail;
	}

	public void seteMail(String eMail) {
		this.eMail = eMail;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(address, eMail, firstName, lastName, phoneNumber);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(address, other.address) && Objects.equals(eMail, other.eMail)
				&& Objects.equals(firstName, other.firstName) && Objects.equals(lastName, other.lastName)
				&& Objects.equals(phoneNumber, other.phoneNumber);
	}

	@Override
	public String toString() {
		return "User [firstName=" + firstName + ", lastName=" + lastName + ", eMail=" + eMail + ", phoneNumber="
				+ phoneNumber + ", address=" + address + "]";
	}
	
	
}
