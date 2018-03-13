package com.cognizant.opserv.sp.model;

import java.io.Serializable;

import com.cognizant.opserv.sp.model.attrb.BaseExtdModel;


/**
 * ****************************************************************************.
 *
 * @class Product
 * @author Cognizant Technology Solutions
 * @version OpServ 1.0
 * @since 11/05/2016
 * ***************************************************************************
 */
public class Product extends BaseExtdModel implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 5527452693096504589L;

	/**  ProductId is unique product id which is generated from database. */
	private long productId;
	
	
	/** A short description of the product */
	private String description;

	/**
	 * Gets the product id.
	 *
	 * @return the productId
	 */
	
	public long getProductId() {
		return productId;
	}

	
	/**
	 * Sets the product id.
	 *
	 * @param productId the productId to set
	 */
	public void setProductId(long productId) {
		this.productId = productId;
	}

	/**
	 * Gets the description.
	 *
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Sets the description.
	 *
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

}
