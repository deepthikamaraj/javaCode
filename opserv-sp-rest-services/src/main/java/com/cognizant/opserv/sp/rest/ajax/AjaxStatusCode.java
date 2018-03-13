package com.cognizant.opserv.sp.rest.ajax;

import org.codehaus.jackson.annotate.JsonValue;
/**
 * ****************************************************************************.
 *
 * @class AjaxStatusCode class defines the code based on the ajax status
 * @author Cognizant Technology Solutions
 * @version OpServ 1.0
 * @since 27/11/2014
 * ***************************************************************************
 */
public enum AjaxStatusCode{

	/** The ok. */
	OK(200),
	/** The server error. */
	SERVER_ERROR(500),
	/** The ajax exception. */
	AJAX_EXCEPTION(100),
	/** The validation message. */
	VALIDATION_MESSAGE(201),
	/** The session expired. */
	SESSION_EXPIRED(300);

	/** The code. */
	private int code;

	/**
	 * The Constructor.
	 *
	 * @param code the code
	 * @Method AjaxStatusCode - Instantiates a new ajax status code.
	 */
	private AjaxStatusCode(int code) {
		this.code = code;
	}
    
	/**
	 * Gets the code.
	 *
	 * @return the code
	 * @Method getCode - Gets the code.
	 */
	@JsonValue
	public int getCode() {
		return code;
	}
	
	/**
	 * To string.
	 *
	 * @return the string
	 * @Method toString - to convert to string
	 */
	@Override
	public String toString() {		
		//return code+"";
		return String.valueOf(code);
	}
}

