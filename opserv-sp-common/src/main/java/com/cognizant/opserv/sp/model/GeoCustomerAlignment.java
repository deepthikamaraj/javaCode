/**
 * 
 */
package com.cognizant.opserv.sp.model;

/**
 * ****************************************************************************.
 *
 * @class GeoCustomerAlignment
 * @author Cognizant Technology Solutions
 * @version OpServ 1.0
 * @since 11/05/2016
 * ***************************************************************************
 */
public class GeoCustomerAlignment extends GeographyAlignment {

	/**
	 *  ID for serialization
	 */
	private static final long serialVersionUID = -5609394245553150035L;
	
	/**
	 * Provides the total count of compensation eligible customers in a particular postal code
	 */
	private int compEligibleCustomerCount; 

	/**
	 * Provides the total count of compensation eligible geo-aligned customers in a particular postal code
	 */
	private int compGeoAlignedCustomerCount;

	/**
	 * @return the compEligibleCustomerCount
	 */
	public int getCompEligibleCustomerCount() {
		return compEligibleCustomerCount;
	}

	/**
	 * @param compEligibleCustomerCount the compEligibleCustomerCount to set
	 */
	public void setCompEligibleCustomerCount(int compEligibleCustomerCount) {
		this.compEligibleCustomerCount = compEligibleCustomerCount;
	}

	/**
	 * @return the compGeoAlignedCustomerCount
	 */
	public int getCompGeoAlignedCustomerCount() {
		return compGeoAlignedCustomerCount;
	}

	/**
	 * @param compGeoAlignedCustomerCount the compGeoAlignedCustomerCount to set
	 */
	public void setCompGeoAlignedCustomerCount(int compGeoAlignedCustomerCount) {
		this.compGeoAlignedCustomerCount = compGeoAlignedCustomerCount;
	}
}
