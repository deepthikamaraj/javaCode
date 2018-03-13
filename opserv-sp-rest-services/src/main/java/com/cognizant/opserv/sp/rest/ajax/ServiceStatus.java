package com.cognizant.opserv.sp.rest.ajax;

import java.io.Serializable;

/**
 * The Class ServiceStatus.
 *
 * @class ServiceStatus class defines the service status
 * @author Cognizant Technology Solutions
 * @version OpServ 1.0
 * @since 27/11/2014
 */
public class ServiceStatus implements Serializable {

	/** serialVersionUID. */
	private static final long serialVersionUID = -4129013592286405742L;

	/** The code for ajax status. */
	private AjaxStatusCode code;
	
	/** The message. */
	private String message;

	/**
	 * The Constructor.
	 *
	 * @Method ServiceStatus - Instantiates a new service status.
	 */
	public ServiceStatus() {

	}

	/**
	 * The Constructor.
	 *
	 * @param code the code
	 * @param message the message
	 * @Method ServiceStatus -Instantiates a new service status.
	 */
	public ServiceStatus(AjaxStatusCode code, String message) {
		this.code = code;
		this.message = message;
	}

	// getter and setter
	/**
	 * Gets the code.
	 *
	 * @return the code
	 * @Method getCode - Gets the code.
	 */
	public AjaxStatusCode getCode() {
		return code;
	}

	/**
	 * Sets the code.
	 *
	 * @param code the new code
	 * @Method Sets the code.
	 */
	public void setCode(AjaxStatusCode code) {
		this.code = code;
	}

	/**
	 * Gets the message.
	 *
	 * @return the message
	 * @Method Gets the message.
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * Hash code.
	 *
	 * @return the int
	 * @Method hashCode
	 */
	@Override
	public int hashCode() {
	    int prime = 31;
		int result = 1;
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		result = prime * result + ((message == null) ? 0 : message.hashCode());
		return result;
	}

	/**
	 * equals.
	 *
	 * @param obj the obj
	 * @return true, if equals
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ServiceStatus other = (ServiceStatus) obj;
		if (code != other.code)
			return false;
		if (message == null) {
			if (other.message != null)
				return false;
		} else if (!message.equals(other.message))
			return false;
		return true;
	}

	/**
	 * To string.
	 *
	 * @return the string
	 * @Method toString - converts to string
	 */
	@Override
	public String toString() {
		return "ServiceStatus [code=" + code + ", message=" + message + "]";

	}

}
