package com.cognizant.opserv.sp.vo;

import java.util.List;

/**
 * ****************************************************************************.
 * 
 * @class QuickStats
 * @author Cognizant Technology Solutions
 * @version OpServ 1.0
 * @since 09/06/2016
 *        ************************************************************
 *        ***************
 */
public class QuickStats {

	/** The product count. */
	private int productCount;

	/** The customer count. */
	private int customerCount;

	/** The zip count. */
	private int zipCount;

	/** The geo cust count. */
	private int geoCustCount;

	/** The non geo cust count. */
	private int nonGeoCustCount;

	/** The product names. */
	private List<String> productNames;

	/**
	 * @return the productCount
	 */
	public int getProductCount() {
		return productCount;
	}

	/**
	 * @param productCount the productCount to set
	 */
	public void setProductCount(int productCount) {
		this.productCount = productCount;
	}

	/**
	 * @return the customerCount
	 */
	public int getCustomerCount() {
		return customerCount;
	}

	/**
	 * @param customerCount the customerCount to set
	 */
	public void setCustomerCount(int customerCount) {
		this.customerCount = customerCount;
	}

	/**
	 * @return the zipCount
	 */
	public int getZipCount() {
		return zipCount;
	}

	/**
	 * @param zipCount the zipCount to set
	 */
	public void setZipCount(int zipCount) {
		this.zipCount = zipCount;
	}

	/**
	 * @return the geoCustCount
	 */
	public int getGeoCustCount() {
		return geoCustCount;
	}

	/**
	 * @param geoCustCount the geoCustCount to set
	 */
	public void setGeoCustCount(int geoCustCount) {
		this.geoCustCount = geoCustCount;
	}

	/**
	 * @return the nonGeoCustCount
	 */
	public int getNonGeoCustCount() {
		return nonGeoCustCount;
	}

	/**
	 * @param nonGeoCustCount the nonGeoCustCount to set
	 */
	public void setNonGeoCustCount(int nonGeoCustCount) {
		this.nonGeoCustCount = nonGeoCustCount;
	}

	/**
	 * @return the productNames
	 */
	public List<String> getProductNames() {
		return productNames;
	}

	/**
	 * @param productNames the productNames to set
	 */
	public void setProductNames(List<String> productNames) {
		this.productNames = productNames;
	}

	

}
