package com.cognizant.opserv.sp.rest.ajax;

import java.io.Serializable;


/**
 * The Class ServiceRequest.
 *
 * @param <T> the generic type
 * @class ServiceRequest class defines the service request
 * @author Cognizant Technology Solutions
 * @version OpServ 1.0
 * @since 27/11/2014
 */
public class ServiceRequest<T> implements Serializable {

	/** serialVersionUID. */
	private static final long serialVersionUID = -4765532707828542962L;

	/** The token. */
	private String token;
	
	/** The data. */
	private T data;
	
	/** The index. */
	private int index;
	
	/** The no of results. */
	private int noOfResults;

	/**
	 * Gets the token.
	 *
	 * @return the token
	 * @Method getToken - getters and setters.
	 */
	
	public String getToken() {
		return token;
	}

	/**
	 * Sets the token.
	 *
	 * @param token the new token
	 * @Method setToken - Sets the token.
	 */
	public void setToken(String token) {
		this.token = token;
	}

	/**
	 * Gets the data.
	 *
	 * @return the data
	 * @Method getData - Gets the data.
	 */
	public T getData() {
		return data;
	}

	/**
	 * Sets the data.
	 *
	 * @param data the new data
	 * @Method setData - Sets the data.
	 */
	public void setData(T data) {
		this.data = data;
	}

	/**
	 * Gets the index.
	 *
	 * @return the index
	 * @Method getIndex - Gets the index.
	 */
	public int getIndex() {
		return index;
	}

	/**
	 * Sets the index.
	 *
	 * @param index the new index
	 * @Method setIndex - Sets the index.
	 */
	public void setIndex(int index) {
		this.index = index;
	}

	/**
	 * Gets the no of results.
	 *
	 * @return the no of results
	 * @Method getNoOfResults - Gets the no of results.
	 */
	public int getNoOfResults() {
		return noOfResults;
	}

	/**
	 * Sets the no of results.
	 *
	 * @param noOfResults the new no of results
	 * @Method setNoOfResults- Sets the no of results.
	 */
	public void setNoOfResults(int noOfResults) {
		this.noOfResults = noOfResults;
	}

}