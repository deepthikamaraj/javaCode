package com.cognizant.opserv.sp.model.cr;

import java.io.Serializable;
import java.util.List;

import com.cognizant.opserv.sp.model.CustomerAlignment;
import com.cognizant.opserv.sp.model.CustomerProduct;
import com.cognizant.opserv.sp.model.CustomerProductAlignment;
import com.cognizant.opserv.sp.common.ChangeRequestStatusType;
/**
 * ****************************************************************************.
 *
 * @class CustomerAlignmentChangeRequestDetails
 * @author Cognizant Technology Solutions
 * @version OpServ 1.0
 * @since 11/05/2016
 * ***************************************************************************
 */
public class CustomerAlignmentChangeRequestDetails 
											extends ChangeRequestLineItem 
											implements Serializable {
	

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -49695967746998093L;
	
	/** The old customer alignment. */
	private CustomerAlignment oldCustomerAlignment;
	
	/** The new customer alignment. */
	private CustomerAlignment newCustomerAlignment;
	
	
	/** The change request business reason. */
	private ChangeRequestBusinessReason changeRequestBusinessReason;
	
	
	/** The customer product. */
	private CustomerProduct customerProduct;
	
	
	/** The old customer product alignments. */
	private List<CustomerProductAlignment> oldCustomerProductAlignments;
	
	/** The new customer product alignments. */
	private List<CustomerProductAlignment> newCustomerProductAlignments;
	

	/**
	 * Gets the old customer product alignments.
	 *
	 * @return the oldCustomerProductAlignments
	 */
	public List<CustomerProductAlignment> getOldCustomerProductAlignments() {
		return oldCustomerProductAlignments;
	}

	/**
	 * Sets the old customer product alignments.
	 *
	 * @param oldCustomerProductAlignments the oldCustomerProductAlignments to set
	 */
	public void setOldCustomerProductAlignments(
			List<CustomerProductAlignment> oldCustomerProductAlignments) {
		this.oldCustomerProductAlignments = oldCustomerProductAlignments;
	}

	/**
	 * Gets the new customer product alignments.
	 *
	 * @return the newCustomerProductAlignments
	 */
	public List<CustomerProductAlignment> getNewCustomerProductAlignments() {
		return newCustomerProductAlignments;
	}

	/**
	 * Sets the new customer product alignments.
	 *
	 * @param newCustomerProductAlignments the newCustomerProductAlignments to set
	 */
	public void setNewCustomerProductAlignments(
			List<CustomerProductAlignment> newCustomerProductAlignments) {
		this.newCustomerProductAlignments = newCustomerProductAlignments;
	}

	/**
	 * Gets the old customer alignment.
	 *
	 * @return the oldCustomerAlignment
	 */
	public CustomerAlignment getOldCustomerAlignment() {
		return oldCustomerAlignment;
	}
	
	/**
	 * Sets the old customer alignment.
	 *
	 * @param oldCustomerAlignment the oldCustomerAlignment to set
	 */
	public void setOldCustomerAlignment(CustomerAlignment oldCustomerAlignment) {
		this.oldCustomerAlignment = oldCustomerAlignment;
	}
	
	/**
	 * Gets the new customer alignment.
	 *
	 * @return the newCustomerAlignment
	 */
	public CustomerAlignment getNewCustomerAlignment() {
		return newCustomerAlignment;
	}
	
	/**
	 * Sets the new customer alignment.
	 *
	 * @param newCustomerAlignment the newCustomerAlignment to set
	 */
	public void setNewCustomerAlignment(CustomerAlignment newCustomerAlignment) {
		this.newCustomerAlignment = newCustomerAlignment;
	}
	
	/**
	 * Gets the change request business reason.
	 *
	 * @return the changeRequestBusinessReason
	 */
	public ChangeRequestBusinessReason getChangeRequestBusinessReason() {
		return changeRequestBusinessReason;
	}
	
	/**
	 * Sets the change request business reason.
	 *
	 * @param changeRequestBusinessReason the changeRequestBusinessReason to set
	 */
	public void setChangeRequestBusinessReason(ChangeRequestBusinessReason changeRequestBusinessReason) {
		this.changeRequestBusinessReason = changeRequestBusinessReason;
	}
	
	/**
	 * Gets the customer product.
	 *
	 * @return the customer product
	 */
	public CustomerProduct getCustomerProduct() {
		return customerProduct;
	}
	
	/**
	 * Sets the customer product.
	 *
	 * @param customerProduct the new customer product
	 */
	public void setCustomerProduct(CustomerProduct customerProduct) {
		this.customerProduct = customerProduct;
	}

	
}

