package com.cognizant.opserv.sp.model;

import java.io.Serializable;
/**
 * ****************************************************************************.
 *
 * @class ProductAlignment
 * @author Cognizant Technology Solutions
 * @version OpServ 1.0
 * @since 11/05/2016
 * ***************************************************************************
 */
public class ProductAlignment extends BaseSalesAlignment implements
		Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5991387815043932535L;

	/* This contains the product details of the product contained in the sales position of the alignment */
	private Product product;

	/* Weight given to the product in the given sales position */
	private String weightage;
	
	

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
	 * @return the weightage
	 */
	public String getWeightage() {
		return weightage;
	}

	/**
	 * @param weightage
	 *            the weightage to set
	 */
	public void setWeightage(String weightage) {
		this.weightage = weightage;
	}

	
	
}
