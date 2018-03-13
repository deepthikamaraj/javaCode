package com.cognizant.opserv.sp.model.cr;

import com.cognizant.opserv.sp.model.BaseModel;
/**
 * ****************************************************************************.
 *
 * @class ChangeRequestLineItem
 * @author Cognizant Technology Solutions
 * @version OpServ 1.0
 * @since 11/05/2016
 * ***************************************************************************
 */
public abstract class ChangeRequestLineItem extends BaseModel{
	
	/**
	 * 
	 */
	private ChangeRequest parentChangeRequest;
	
	/**
	 * comments
	 */
	private String comments;

	/**
	 * @return the parentChangeRequest
	 */
	public ChangeRequest getParentChangeRequest() {
		return parentChangeRequest;
	}

	/**
	 * @param parentChangeRequest the parentChangeRequest to set
	 */
	public void setParentChangeRequest(ChangeRequest parentChangeRequest) {
		this.parentChangeRequest = parentChangeRequest;
	}

	/**
	 * @return the comments
	 */
	public String getComments() {
		return comments;
	}

	/**
	 * @param comments the comments to set
	 */
	public void setComments(String comments) {
		this.comments = comments;
	}
	

}
