package com.cognizant.opserv.sp.model.communication;

public class RecipientsAttribute {

	/** attrId - attr id. */
	private Integer attrId;
	
	/** compareFactor - compare factor. */
	private String compareFactor;
	
	/** attrSelValue - attr sel value. */
	private String attrSelValue;
	
	/** attrTextValue - attr text value. */
	private String attrTextValue;
	
	/** logicalOperator - logical operator. */
	
	private String logicalOperator;

	/**
	 * @method getAttrSelValue- Gets the attr sel value.
	 *
	 * @return the attr sel value
	 */
	public String getAttrSelValue() {
		return attrSelValue;
	}
	
	/**
	 * @method setAttrSelValue- Sets the attr sel value.
	 *
	 * @param attrSelValue the new attr sel value
	 * 
	 * @return void
	 */
	public void setAttrSelValue(String attrSelValue) {
		this.attrSelValue = attrSelValue;
	}
	
	/**
	 * @method getAttrTextValue- Gets the attr text value.
	 *
	 * @return the attr text value
	 */
	public String getAttrTextValue() {
		return attrTextValue;
	}
	
	/**
	 * @method setAttrTextValue- Sets the attr text value.
	 *
	 * @param attrTextValue the new attr text value
	 * 
	 * @return void
	 */
	public void setAttrTextValue(String attrTextValue) {
		this.attrTextValue = attrTextValue;
	}
	
	
	/**
	 * @method getAttrId- Gets the attr id.
	 *
	 * @return the attr id
	 */
	public Integer getAttrId() {
		return attrId;
	}
	
	/**
	 * @method setAttrId- Sets the attr id.
	 *
	 * @param attrId the new attr id
	 * 
	 * @return void
	 */
	public void setAttrId(Integer attrId) {
		this.attrId = attrId;
	}
	
	/**
	 * @method getCompareFactor- Gets the compare factor.
	 *
	 * @return the compare factor
	 */
	public String getCompareFactor() {
		return compareFactor;
	}
	
	/**
	 * @method setCompareFactor- Sets the compare factor.
	 *
	 * @param compareFactor the new compare factor
	 * 
	 * @return void
	 */
	public void setCompareFactor(String compareFactor) {
		this.compareFactor = compareFactor;
	}
	
	
	
	/**
	 * @method getLogicalOperator- Gets the logical operator.
	 *
	 * @return the logical operator
	 */
	public String getLogicalOperator() {
		return logicalOperator;
	}
	
	/**
	 * @method setLogicalOperator- Sets the logical operator.
	 *
	 * @param logicalOperator the new logical operator
	 * 
	 * @return void
	 */
	public void setLogicalOperator(String logicalOperator) {
		this.logicalOperator = logicalOperator;
	}
}
