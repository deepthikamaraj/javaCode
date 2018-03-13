/* COPYRIGHT (C) 2014 Cognizant, all rights reserved.*/
package com.cognizant.opserv.sp.model.auth;

import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;


// TODO: Auto-generated Javadoc
/**
 * ****************************************************************************.
 *
 * @class SSOUser
 * @author Cognizant Technology Solutions
 * @version OpServ 1.0
 * @since 27/11/2014
 * ***************************************************************************
 */
public class SSOUser extends User {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -5316636707484375811L;
	
	
	/** userDetails - user details. */
	private UserDetails userDetails;

	/**
	 * Instantiates a new sSO user.
	 *
	 * @param userName the user name
	 * @param password the password
	 * @param authorities the authorities
	 */
	public SSOUser(String userName, String password,
			List<GrantedAuthority> authorities) {
		super(userName, password, authorities);
	}

	/**
	 * @method setUserDetails-Sets the user details.
	 *
	 * @param userDetails the new user details
	 * 
	 * @return void
	 */
	public void setUserDetails(UserDetails userDetails) {
		this.userDetails = userDetails;
	}

	/**
	 * @method getUserDetails-Gets the user details.
	 *
	 * @return the user details
	 */
	public UserDetails getUserDetails() {
		return userDetails;
	}
	
}
