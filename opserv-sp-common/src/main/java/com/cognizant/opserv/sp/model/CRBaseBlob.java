package com.cognizant.opserv.sp.model;

import com.cognizant.opserv.sp.model.auth.UserDetails;
/**
 * ****************************************************************************.
 *
 * @class CRBaseBlob
 * @author Cognizant Technology Solutions
 * @version OpServ 1.0
 * @since 11/05/2016
 * ***************************************************************************
 */
public class CRBaseBlob {
	
	/**
	 * comments is the comments
	 */
	private String comments;
	
	/**
	 * bussReason is the business reason
	 */
	private BusinessReason bussReason;
	
	/**
	 * userDetails is the userdetails
	 */
	private UserDetails userDetails;
	
	/**
	 * @return the comments
	 * 
	 */
	public String getComments() {
		return comments;
	}
	
	/**
	 * Sets the Comments.
	 *
	 * @param comments is the comments.
	 */
	public void setComments(String comments) {
		this.comments = comments;
	}
	
	
	
	/**
	 * @return the userDetails
	 * 
	 */
	public UserDetails getUserDetails() {
		return userDetails;
	}
	
	/**
	 * Sets the userDetails.
	 *
	 * @param userDetails is the userDetails.
	 */
	public void setUserDetails(UserDetails userDetails) {
		this.userDetails = userDetails;
	}

	/**
	 * @return
	 */
	public BusinessReason getBussReason() {
		return bussReason;
	}

	/**
	 * @param bussReason
	 */
	public void setBussReason(BusinessReason bussReason) {
		this.bussReason = bussReason;
	}

}
