package com.cognizant.opserv.sp.model;

import java.io.Serializable;

/**
 * ****************************************************************************.
 *
 * @class Product
 * @author Cognizant Technology Solutions
 * @version OpServ 1.0
 * @since 11/05/2016
 * ***************************************************************************
 */
/**
 * @author 429433
 * 
 */

public class CustomerProductSalesTeam extends BaseSalesAlignment implements
		Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2925713984937046835L;

	/**
	 * customer
	 */
	private Customer customer;

	/** product **/
	private Product product;

	/** isCompAligned **/
	private boolean isCompEligible;

	/**
	 * customer
	 */
	private boolean isActive;

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public boolean isCompEligible() {
		return isCompEligible;
	}

	public void setCompEligible(boolean isCompEligible) {
		this.isCompEligible = isCompEligible;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

}
