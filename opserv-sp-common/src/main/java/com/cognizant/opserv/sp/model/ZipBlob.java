package com.cognizant.opserv.sp.model;

import java.util.List;

/**
 * The Class ZipBlob.
 */
public class ZipBlob extends CRBaseBlob{
	
	/**
	 * srcGeographyAlignment is the Source Customer Alignment
	 */
	private GeographyAlignment srcGeographyAlignment;
	
	/**
	 * targetGeographyAlignment is the Target Customer Alignment
	 */
	private GeographyAlignment targetGeographyAlignment;
	
	/**
	 * customerAlignmentList is the List of Customer Alignment
	 */
	private List<CustomerAlignment> customerAlignmentList;
	
	
	public GeographyAlignment getSrcGeographyAlignment() {
		return srcGeographyAlignment;
	}
	
	/**
	 * Sets the Source Geography Alignment.
	 *
	 * @param srcGeographyAlignment is the Source Geography Alignment.
	 */
	public void setSrcGeographyAlignment(GeographyAlignment srcGeographyAlignment) {
		this.srcGeographyAlignment = srcGeographyAlignment;
	}
	
	/**
	 * @return the targetGeographyAlignment
	 * 
	 */	
	public GeographyAlignment getTargetGeographyAlignment() {
		return targetGeographyAlignment;
	}
	

	/**
	 * Sets the Target Geography Alignment.
	 *
	 * @param targetGeographyAlignment is the Target Geography Alignment.
	 */
	public void setTargetGeographyAlignment(
			GeographyAlignment targetGeographyAlignment) {
		this.targetGeographyAlignment = targetGeographyAlignment;
	}
	
	/**
	 * @return the customerAlignmentList
	 * 
	 */
	public List<CustomerAlignment> getCustomerAlignmentList() {
		return customerAlignmentList;
	}
	
	/**
	 * Sets the Customer Alignment List.
	 *
	 * @param customerAlignmentList is the customer Alignment List.
	 */
	public void setCustomerAlignmentList(
			List<CustomerAlignment> customerAlignmentList) {
		this.customerAlignmentList = customerAlignmentList;
	}

}
