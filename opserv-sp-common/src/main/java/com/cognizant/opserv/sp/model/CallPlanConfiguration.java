package com.cognizant.opserv.sp.model;

import java.io.Serializable;
/**
 * ****************************************************************************.
 *
 * @class CallPlanConfiguration
 * @author Cognizant Technology Solutions
 * @version OpServ 1.0
 * @since 11/05/2016
 * ***************************************************************************
 */
public class CallPlanConfiguration extends BaseModel implements Serializable {

	/**
	 * The Class CustomerCallPlan.
	 */
	private static final long serialVersionUID = 455069992748207908L;

	/**
	 * alignment is the alignment data
	 */
	private Alignment alignment;

	/**
	 * isAffiliationBased indicates affiliation based or not
	 */
	private boolean isAffiliationBased;

	/**
	 * isDirectionBased indicates direction or product based
	 */
	private boolean isDirectionBased;

	/**
	 * isProductSplit is the product split allowed or not
	 */
	private boolean isProductSplit;

	/**
	 * maxProducts is the maximum number of products
	 */
	private int maxProducts;

	/**
	 * @return the alignment
	 */
	public Alignment getAlignment() {
		return alignment;
	}

	/**
	 * @param alignment
	 *            the alignment to set
	 */
	public void setAlignment(Alignment alignment) {
		this.alignment = alignment;
	}

	/**
	 * @return the isAffiliationBased
	 */
	public boolean isAffiliationBased() {
		return isAffiliationBased;
	}

	/**
	 * @param isAffiliationBased
	 *            the isAffiliationBased to set
	 */
	public void setAffiliationBased(boolean isAffiliationBased) {
		this.isAffiliationBased = isAffiliationBased;
	}

	/**
	 * @return the isDirectionBased
	 */
	public boolean isDirectionBased() {
		return isDirectionBased;
	}

	/**
	 * @param isDirectionBased
	 *            the isDirectionBased to set
	 */
	public void setDirectionBased(boolean isDirectionBased) {
		this.isDirectionBased = isDirectionBased;
	}

	/**
	 * @return the isProductSplit
	 */
	public boolean isProductSplit() {
		return isProductSplit;
	}

	/**
	 * @param isProductSplit
	 *            the isProductSplit to set
	 */
	public void setProductSplit(boolean isProductSplit) {
		this.isProductSplit = isProductSplit;
	}

	/**
	 * @return the maxProducts
	 */
	public int getMaxProducts() {
		return maxProducts;
	}

	/**
	 * @param maxProducts
	 *            the maxProducts to set
	 */
	public void setMaxProducts(int maxProducts) {
		this.maxProducts = maxProducts;
	}

}
