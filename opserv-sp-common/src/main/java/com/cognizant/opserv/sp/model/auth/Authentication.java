/* COPYRIGHT (C) 2014 Cognizant, all rights reserved.*/
package com.cognizant.opserv.sp.model.auth;


// TODO: Auto-generated Javadoc
/**
 * ****************************************************************************.
 *
 * @class Authentication
 * @author Cognizant Technology Solutions
 * @version OpServ 1.0
 * @since 27/11/2014
 * ***************************************************************************
 */

public class Authentication {
	
	/**username - username. */
	private String username;
	
	/**password - password. */
	private String password;
	
	/** newPassword- new password. */
	private String newPassword;
	
	/**confrimPassword - confirm password. */
	private String confrimPassword;
	
	/** emailId- email id. */
	public String emailId;



	/**
	 * @method getEmailId- Gets the email id.
	 *
	 * @return the email id
	 */
	public String getEmailId() {
		return emailId;
	}

	/**
	 * @method setEmailId- Sets the email id.
	 *
	 * @param emailId the new email id
	 * 
	 * @return void
	 */
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	/**
	 * @method getUsername- Gets the username.
	 *
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @method setUsername- Sets the username.
	 *
	 * @param username the new username
	 * 
	 * @return void
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @method getPassword- Gets the password.
	 *
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @method setPassword- Sets the password.
	 *
	 * @param password the new password
	 * 
	 * @return void
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @method setNewPassword- Sets the new password.
	 *
	 * @param newPassword the new new password
	 * 
	 * @return void
	 */
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	/**
	 * @method getNewPassword- Gets the new password.
	 *
	 * @return the new password
	 */
	public String getNewPassword() {
		return newPassword;
	}

	/**
	 * @method setConfrimPassword- Sets the confrim password.
	 *
	 * @param confrimPassword the new confrim password
	 * 
	 * @return void
	 */
	public void setConfrimPassword(String confrimPassword) {
		this.confrimPassword = confrimPassword;
	}

	/**
	 * @method getConfrimPassword- Gets the confrim password.
	 *
	 * @return the confrim password
	 */
	public String getConfrimPassword() {
		return confrimPassword;
	}

}
