package com.pwa.data.model.user;

/**
 * User entity. @author MyEclipse Persistence Tools
 */
public class User extends AbstractUser implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public User() {
	}

	/** minimal constructor */
	public User(String name) {
		super(name);
	}

	/** full constructor */
	public User(String name, String password, String role) {
		super(name, password, role);
	}

}
