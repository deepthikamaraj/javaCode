package com.cognizant.opserv.sp.model;

import java.io.Serializable;
/**
 * ****************************************************************************.
 *
 * @class CallPlanItem
 * @author Cognizant Technology Solutions
 * @version OpServ 1.0
 * @since 11/05/2016
 * ***************************************************************************
 */
public class CallPlanItem extends BaseModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6888783742902549381L;

	/** production information **/
	private Product product;

	/** planned calls for the product **/
	private int plannedCalls;

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
	 * @return the plannedCalls
	 */
	public int getPlannedCalls() {
		return plannedCalls;
	}

	/**
	 * @param plannedCalls
	 *            the plannedCalls to set
	 */
	public void setPlannedCalls(int plannedCalls) {
		this.plannedCalls = plannedCalls;
	}

}
