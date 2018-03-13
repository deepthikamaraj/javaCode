/* COPYRIGHT (C) 2014 Cognizant, all rights reserved.*/
package com.cognizant.opserv.sp.model.auth;

import org.springframework.security.core.GrantedAuthority;


// TODO: Auto-generated Javadoc
/**
 * ****************************************************************************.
 *
 * @class Privileges
 * @author Cognizant Technology Solutions
 * @version OpServ 1.0
 * @since 27/11/2014
 * ***************************************************************************
 */
public class Privileges implements GrantedAuthority{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4882675885942920750L;
	
	/** privilName - privil name. */
	private String privilName;


	/** getAuthority 
	 * @method getAuthority - method getAuthority
	 * @return String - returns String	 */
	@Override
	public String getAuthority() {
		return privilName;
	}

	/**
	 * @method getPrivilName- Gets the privil name.
	 *
	 * @return the privil name
	 */
	public String getPrivilName() {
		return privilName;
	}

	/**
	 * @method setPrivilName- Sets the privil name.
	 *
	 * @param privilName the new privil name
	 * 
	 * @return void
	 */
	public void setPrivilName(String privilName) {
		this.privilName = privilName.toUpperCase();
	}

	/** Method toString
	 * @method toString - toString
	 * @return String - returns string
	 **/
	@Override
	public String toString() {
		return "Privileges [privilName=" + privilName + "]";
	}
	
	/** Method hashCode
	 * @method hashCode - hashCode
	 * @return integer - returns the result 
	 **/
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((privilName == null) ? 0 : privilName.hashCode());
		return result;
	}

	/** Method equals
	 * @method equals
	 * @param Object - holds object
	 * @return boolean - returns true/false
	 **/
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Privileges other = (Privileges) obj;
		if (privilName == null) {
			if (other.privilName != null)
				return false;
		} else if (!privilName.equals(other.privilName))
			return false;
		return true;
	}	
}
