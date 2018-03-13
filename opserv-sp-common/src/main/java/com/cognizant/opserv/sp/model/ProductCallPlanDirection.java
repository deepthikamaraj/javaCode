package com.cognizant.opserv.sp.model;

import java.io.Serializable;

import com.cognizant.opserv.sp.common.ProductPriorityType;
/**
 * ****************************************************************************.
 *
 * @class ProductCallPlanDirection
 * @author Cognizant Technology Solutions
 * @version OpServ 1.0
 * @since 11/05/2016
 * ***************************************************************************
 */
public class ProductCallPlanDirection extends BaseModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8683369571368901337L;

	/** product information **/
	private Product product;

	/** product priority **/
	private ProductPriorityType priority;

	private float weightage;

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
	 * @return the priority
	 */
	public ProductPriorityType getPriority() {
		return priority;
	}

	/**
	 * @param priority
	 *            the priority to set
	 */
	public void setPriority(ProductPriorityType priority) {
		this.priority = priority;
	}

	/**
	 * @return the weightage
	 */
	public float getWeightage() {
		return weightage;
	}

	/**
	 * @param weightage
	 *            the weightage to set
	 */
	public void setWeightage(float weightage) {
		this.weightage = weightage;
	}

}
