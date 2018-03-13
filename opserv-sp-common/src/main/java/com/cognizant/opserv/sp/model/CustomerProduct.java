package com.cognizant.opserv.sp.model;

import java.io.Serializable;

import com.cognizant.opserv.sp.model.attrb.BaseExtdModel;

/**
 * ****************************************************************************.
 *
 * @class CustomerProduct
 * @author Cognizant Technology Solutions
 * @version OpServ 1.0
 * @since 11/05/2016
 * ***************************************************************************
 */
public class CustomerProduct extends BaseExtdModel implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4497496065629164713L;

	/** customer is the customer data. */
	private Customer customer;

	/** products is the product data. */
	private Product product;

	/** timePeriod is the time period. */
	private int timePeriod;

	/** brandDollarSaleValue is the brand dollar sale value. */
	private double brandDollarSaleValue;

	/** brandUnitSaleValue is the brand unit sale value. */
	private long brandUnitSaleValue;

	/** marketDollarSaleValue is the market dollar sale value. */
	private String marketDollarSaleValue;

	/** marketUnitSaleValue is the market unit sale value. */
	private long marketUnitSaleValue;

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
	 * @return the products
	 */
	public Product getProduct() {
		return product;
	}

	/**
	 * @param products
	 *            the products to set
	 */
	public void setProduct(Product product) {
		this.product = product;
	}

	/**
	 * @return the timePeriod
	 */
	public int getTimePeriod() {
		return timePeriod;
	}

	/**
	 * @param timePeriod
	 *            the timePeriod to set
	 */
	public void setTimePeriod(int timePeriod) {
		this.timePeriod = timePeriod;
	}

	/**
	 * @return the brandDollarSaleValue
	 */
	public double getBrandDollarSaleValue() {
		return brandDollarSaleValue;
	}

	/**
	 * @param brandDollarSaleValue
	 *            the brandDollarSaleValue to set
	 */
	public void setBrandDollarSaleValue(double brandDollarSaleValue) {
		this.brandDollarSaleValue = brandDollarSaleValue;
	}

	/**
	 * @return the brandUnitSaleValue
	 */
	public long getBrandUnitSaleValue() {
		return brandUnitSaleValue;
	}

	/**
	 * @param brandUnitSaleValue
	 *            the brandUnitSaleValue to set
	 */
	public void setBrandUnitSaleValue(long brandUnitSaleValue) {
		this.brandUnitSaleValue = brandUnitSaleValue;
	}

	/**
	 * @return the marketDollarSaleValue
	 */
	public String getMarketDollarSaleValue() {
		return marketDollarSaleValue;
	}

	/**
	 * @param marketDollarSaleValue
	 *            the marketDollarSaleValue to set
	 */
	public void setMarketDollarSaleValue(String marketDollarSaleValue) {
		this.marketDollarSaleValue = marketDollarSaleValue;
	}

	/**
	 * @return the marketUnitSaleValue
	 */
	public long getMarketUnitSaleValue() {
		return marketUnitSaleValue;
	}

	/**
	 * @param marketUnitSaleValue
	 *            the marketUnitSaleValue to set
	 */
	public void setMarketUnitSaleValue(long marketUnitSaleValue) {
		this.marketUnitSaleValue = marketUnitSaleValue;
	}

}
