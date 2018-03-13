package com.cognizant.opserv.sp.model;

import java.io.Serializable;
import com.cognizant.opserv.sp.common.PriorityType;

/**
 * ****************************************************************************.
 *
 * @class CustomerAlignment
 * @author Cognizant Technology Solutions
 * @version OpServ 1.0
 * @since 11/05/2016
 * ***************************************************************************
 */
public class CustomerAlignment extends BaseSalesAlignment implements
		Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -2506914064974270635L;

	/** customer is the customer data. */
	private Customer customer;

	/** isAffBasedAssignment is the affiliation based assignment flag. */
	private boolean isAffBasedAssignment;

	/** priorityCode is the priority code. */
	private PriorityType priority;

	/** isCustomerTargeted is the targeted customer flag. */
	private boolean isCustomerTargeted;

	/** isImplicitAssignment is the implicit assignment flag. */
	private boolean isImplicitAssignment;
	
	/** isGeoAligned **/
	private boolean isGeoAligned;
	
	/** isCompAligned **/
	private boolean isCompAligned;
	
	/** Customer Call Plan **/
	private CustomerCallPlan customerCallPlan;
	
	private CustomerCallPlan baseCustomerCallPlan;

	private boolean isLocked;
	
	/**
	 * Gets the customer.
	 * 
	 * @return the customer
	 */
	public Customer getCustomer() {
		return customer;
	}

	/**
	 * Sets the customer.
	 * 
	 * @param customer
	 *            the customer to set
	 */
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	/**
	 * Gets the priority code.
	 * 
	 * @return the priorityCode
	 */
	public PriorityType getPriorityCode() {
		return priority;
	}

	/**
	 * Sets the priority code.
	 * 
	 * @param priorityCode
	 *            the priorityCode to set
	 */
	public void setPriorityCode(PriorityType priority) {
		this.priority = priority;
	}

	/**
	 * Checks if is aff based assignment.
	 * 
	 * @return the isAffBasedAssignment
	 */
	public boolean isAffBasedAssignment() {
		return isAffBasedAssignment;
	}

	/**
	 * Sets the aff based assignment.
	 * 
	 * @param isAffBasedAssignment
	 *            the isAffBasedAssignment to set
	 */
	public void setAffBasedAssignment(boolean isAffBasedAssignment) {
		this.isAffBasedAssignment = isAffBasedAssignment;
	}

	/**
	 * Checks if is customer targeted.
	 * 
	 * @return the isCustomerTargeted
	 */
	public boolean isCustomerTargeted() {
		return isCustomerTargeted;
	}

	/**
	 * Sets the customer targeted.
	 * 
	 * @param isCustomerTargeted
	 *            the isCustomerTargeted to set
	 */
	public void setCustomerTargeted(boolean isCustomerTargeted) {
		this.isCustomerTargeted = isCustomerTargeted;
	}

	/**
	 * Checks if is implicit assignment.
	 * 
	 * @return the isImplicitAssignment
	 */
	public boolean isImplicitAssignment() {
		return isImplicitAssignment;
	}

	/**
	 * Sets the implicit assignment.
	 * 
	 * @param isImplicitAssignment
	 *            the isImplicitAssignment to set
	 */
	public void setImplicitAssignment(boolean isImplicitAssignment) {
		this.isImplicitAssignment = isImplicitAssignment;
	}

	/**
	 * @return the priority
	 */
	public PriorityType getPriority() {
		return priority;
	}

	/**
	 * @param priority the priority to set
	 */
	public void setPriority(PriorityType priority) {
		this.priority = priority;
	}

	/**
	 * @return the isGeoAligned
	 */
	public boolean isGeoAligned() {
		return isGeoAligned;
	}

	/**
	 * @param isGeoAligned the isGeoAligned to set
	 */
	public void setGeoAligned(boolean isGeoAligned) {
		this.isGeoAligned = isGeoAligned;
	}
	/**
	 * 
	 * @return
	 */
	public CustomerCallPlan getCustomerCallPlan() {
		return customerCallPlan;
	}
	/**
	 * 
	 * @param customerCallPlan
	 */
	public void setCustomerCallPlan(CustomerCallPlan customerCallPlan) {
		this.customerCallPlan = customerCallPlan;
	}

	public boolean isCompAligned() {
		return isCompAligned;
	}

	public void setCompAligned(boolean isCompAligned) {
		this.isCompAligned = isCompAligned;
	}

	@Override
	public String toString() {
		return "CustomerAlignment [customer=" + customer
				+ ", isAffBasedAssignment=" + isAffBasedAssignment
				+ ", priority=" + priority + ", isCustomerTargeted="
				+ isCustomerTargeted + ", isImplicitAssignment="
				+ isImplicitAssignment + ", isGeoAligned=" + isGeoAligned
				+ ", isCompAligned=" + isCompAligned + ", customerCallPlan="
				+ customerCallPlan + "]";
	}

	public CustomerCallPlan getBaseCustomerCallPlan() {
		return baseCustomerCallPlan;
	}

	public void setBaseCustomerCallPlan(CustomerCallPlan baseCustomerCallPlan) {
		this.baseCustomerCallPlan = baseCustomerCallPlan;
	}
	
	public boolean isLocked() {
		return isLocked;
	}

	public void setLocked(boolean isLocked) {
		this.isLocked = isLocked;
	}
		

}
