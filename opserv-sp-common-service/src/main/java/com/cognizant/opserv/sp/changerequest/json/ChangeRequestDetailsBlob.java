package com.cognizant.opserv.sp.changerequest.json;

import java.util.List;

import com.cognizant.opserv.sp.model.CustomerAlignment;
import com.cognizant.opserv.sp.model.CustomerProductAlignment;

public class ChangeRequestDetailsBlob {
	
	
	private CustomerAlignment previousValue;
	
	private CustomerAlignment updatedValue;
	
	private List<CustomerProductAlignment> oldCustomerProductAlignments;
	
	private List<CustomerProductAlignment> newCustomerProductAlignments;
	
	

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

	/**
	 * @return the previousValue
	 */
	public CustomerAlignment getPreviousValue() {
		return previousValue;
	}

	/**
	 * @param previousValue the previousValue to set
	 */
	public void setPreviousValue(CustomerAlignment previousValue) {
		this.previousValue = previousValue;
	}

	/**
	 * @return the updatedValue
	 */
	public CustomerAlignment getUpdatedValue() {
		return updatedValue;
	}

	/**
	 * @param updatedValue the updatedValue to set
	 */
	public void setUpdatedValue(CustomerAlignment updatedValue) {
		this.updatedValue = updatedValue;
	}

}
