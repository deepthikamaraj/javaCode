package com.cognizant.opserv.sp.model;

import java.io.Serializable;

/**
 * ****************************************************************************.
 *
 * @class CustomerProductAlignment
 * @author Cognizant Technology Solutions
 * @version OpServ 1.0
 * @since 11/05/2016
 * ***************************************************************************
 */
public class CustomerProductAlignment extends BaseSalesAlignment implements Serializable{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 4908015910758905155L;

	private Customer customer;

	private Product product;
	
	private Integer allocationPercentage;
	
	private boolean isCompAvailable;
	
	private Long customerAlgmntId;// added as Customer Alignment Id need to be set
	
	private Long productAlgmntId;// added as Product Alignment Id need to be set
	
	private String prdFlag;
	
	/**
	 * @return the customer
	 */
	public Customer getCustomer() {
		return customer;
	}

	/**
	 * @param customer
	 *            the customer to set
	 */
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	/**
	 * @return the product
	 */
	public Product getProduct() {
		return product;
	}

	/**
	 * @param product
	 *            the product to set
	 */
	public void setProduct(Product product) {
		this.product = product;
	}

	/**
	 * @return the allocation percentage
	 */
	public Integer getAllocationPercentage() {
		return allocationPercentage;
	}

	/**
	 * @param allocationpercentage
	 *            the allocationpercentage to set
	 */
	public void setAllocationPercentage(Integer allocationPercentage) {
		this.allocationPercentage = allocationPercentage;
	}
	
	/**
	 * @return the isCompAvailable
	 */
	public boolean isCompAvailable() {
		return isCompAvailable;
	}

	/**
	 * @param isCompAvailable
	 *            the isCompAvailable to set
	 */
	public void setCompAvailable(boolean isCompAvailable) {
		this.isCompAvailable = isCompAvailable;
	}

	/**
	 * @return customer Alignment Id
	 */
	public Long getCustomerAlgmntId() {
		return customerAlgmntId;
	}

	/**
	 * @param customerAlgmntId 
	 */
	public void setCustomerAlgmntId(Long customerAlgmntId) {
		this.customerAlgmntId = customerAlgmntId;
	}

	/**
	 * @return product Alignment Id
	 */
	public Long getProductAlgmntId() {
		return productAlgmntId;
	}

	/**
	 * @param productAlgmntId
	 */
	public void setProductAlgmntId(Long productAlgmntId) {
		this.productAlgmntId = productAlgmntId;
	}

	/**
	 * @return prdFlag
	 */
	public String getPrdFlag() {
		return prdFlag;
	}

	/**
	 * @param prdFlag
	 */
	public void setPrdFlag(String prdFlag) {
		this.prdFlag = prdFlag;
	}

}
