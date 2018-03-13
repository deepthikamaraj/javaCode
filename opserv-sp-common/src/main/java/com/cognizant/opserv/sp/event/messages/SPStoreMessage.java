package com.cognizant.opserv.sp.event.messages;

import java.io.Serializable;

import com.cognizant.opserv.sp.model.auth.UserDetails;

/**
 * ****************************************************************************
 * 
 * @class SPStoreMessage
 * @author Cognizant Technology Solutions
 * @version OpServ 3.0
 * @since 01/04/2016
 * ***************************************************************************
 */
public class SPStoreMessage implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -3912561802902570810L;

	/** SP id. */
	private Long id;
	
	/** type of the business entity. */
	private String type;
	
	/** The user details. */
	private UserDetails userDetails;

	/**
	 * Gets the type.
	 *
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * Sets the type.
	 *
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * Gets the id.
	 *
	 * @return SP id 
	 * @Method getId - getting the id
	 */
	public Long getId() {
		return id;
	}
    
	/**
	 * Sets the id.
	 *
	 * @param id the new id of the SP
	 * @Method setId - setting the id
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Gets the user details.
	 *
	 * @return the user details
	 */
	public UserDetails getUserDetails() {
		return userDetails;
	}

	/**
	 * Sets the user details.
	 *
	 * @param userDetails the new user details
	 */
	public void setUserDetails(UserDetails userDetails) {
		this.userDetails = userDetails;
	}

	
	
}
