package com.cognizant.opserv.sp.model;

import java.util.List;

import com.cognizant.opserv.sp.model.cr.ChangeRequest;

/**
 * ****************************************************************************.
 *
 * @class CustomerBlob
 * @author Cognizant Technology Solutions
 * @version OpServ 1.0
 * @since 11/05/2016
 * ***************************************************************************
 */
public class CustomerBlob extends CRBaseBlob{

	/**
	 * srcCustomerAlignment is the sorce Customer Alignment
	 */
	private CustomerAlignment srcCustomerAlignment;
	
	/**
	 * targetCustomerAlignment is the target Customer Alignment
	 */
	private CustomerAlignment targetCustomerAlignment;
	
	
	/**
	 * customerProduct
	 */
	private List<CustomerProductAlignment> oldCustomerProductAlignments;
	
	private List<CustomerProductAlignment> newCustomerProductAlignments;
	
	private ChangeRequest changeRequest;
	
	/**
	 * @return the srcCustomerAlignment
	 * 
	 */
	public CustomerAlignment getSrcCustomerAlignment() {
		return srcCustomerAlignment;
	}
	
	/**
	 * Sets the Source Customer Alignment.
	 *
	 * @param srcCustomerAlignment is the Source Customer Alignment.
	 */
	public void setSrcCustomerAlignment(CustomerAlignment srcCustomerAlignment) {
		this.srcCustomerAlignment = srcCustomerAlignment;
	}
	
	/**
	 * @return the targetCustomerAlignment
	 * 
	 */
	public CustomerAlignment getTargetCustomerAlignment() {
		return targetCustomerAlignment;
	}
	
	/**
	 * Sets the Target Customer Alignment.
	 *
	 * @param targetCustomerAlignment is the target Customer Alignment.
	 */
	public void setTargetCustomerAlignment(CustomerAlignment targetCustomerAlignment) {
		this.targetCustomerAlignment = targetCustomerAlignment;
	}

	/**
	 * @return the oldCustomerProductAlignments
	 */
	public List<CustomerProductAlignment> getOldCustomerProductAlignments() {
		return oldCustomerProductAlignments;
	}

	/**
	 * @param oldCustomerProductAlignments the oldCustomerProductAlignments to set
	 */
	public void setOldCustomerProductAlignments(
			List<CustomerProductAlignment> oldCustomerProductAlignments) {
		this.oldCustomerProductAlignments = oldCustomerProductAlignments;
	}

	/**
	 * @return the newCustomerProductAlignments
	 */
	public List<CustomerProductAlignment> getNewCustomerProductAlignments() {
		return newCustomerProductAlignments;
	}

	/**
	 * @param newCustomerProductAlignments the newCustomerProductAlignments to set
	 */
	public void setNewCustomerProductAlignments(
			List<CustomerProductAlignment> newCustomerProductAlignments) {
		this.newCustomerProductAlignments = newCustomerProductAlignments;
	}

	public ChangeRequest getChangeRequest() {
		return changeRequest;
	}

	public void setChangeRequest(ChangeRequest changeRequest) {
		this.changeRequest = changeRequest;
	}

	
	

	
}
