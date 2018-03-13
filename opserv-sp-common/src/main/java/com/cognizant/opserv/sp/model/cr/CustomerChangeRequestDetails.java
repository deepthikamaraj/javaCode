package com.cognizant.opserv.sp.model.cr;

import java.io.Serializable;

import com.cognizant.opserv.sp.model.Customer;
/**
 * ****************************************************************************.
 *
 * @class CustomerChangeRequestDetails
 * @author Cognizant Technology Solutions
 * @version OpServ 1.0
 * @since 11/05/2016
 * ***************************************************************************
 */
public class CustomerChangeRequestDetails 
									extends ChangeRequestLineItem 
									implements Serializable 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7652714136730274871L;
	
	/**
	 * 
	 */
	private Customer oldCustomerObject;
	/**
	 * 
	 */
	private Customer newCustomerObject;
	/**
	 * @return the oldCustomerObject
	 */
	public Customer getOldCustomerObject() {
		return oldCustomerObject;
	}
	/**
	 * @param oldCustomerObject the oldCustomerObject to set
	 */
	public void setOldCustomerObject(Customer oldCustomerObject) {
		this.oldCustomerObject = oldCustomerObject;
	}
	/**
	 * @return the newCustomerObject
	 */
	public Customer getNewCustomerObject() {
		return newCustomerObject;
	}
	/**
	 * @param newCustomerObject the newCustomerObject to set
	 */
	public void setNewCustomerObject(Customer newCustomerObject) {
		this.newCustomerObject = newCustomerObject;
	}


}

