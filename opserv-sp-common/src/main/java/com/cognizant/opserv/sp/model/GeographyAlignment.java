package com.cognizant.opserv.sp.model;

import java.io.Serializable;
/**
 * ****************************************************************************.
 *
 * @class GeographyAlignment
 * @author Cognizant Technology Solutions
 * @version OpServ 1.0
 * @since 11/05/2016
 * ***************************************************************************
 */
public class GeographyAlignment extends BaseSalesAlignment implements
		Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -7616193633356761140L;

	/** The country code. */
	private String countryCode;

	/** The is assigned. */
	private boolean isAssigned;

	/** The postal code. */
	private PostalCode postalCode;
	
	/** The point zip flag. */
	private boolean pointZipFlag;
	
	/** Locked flag */
	private boolean isLocked;
	

	/**
	 * Gets the country code.
	 *
	 * @return the countryCode
	 */
	public String getCountryCode() {
		return countryCode;
	}

	/**
	 * Sets the country code.
	 *
	 * @param countryCode the countryCode to set
	 */
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	/**
	 * Checks if is assigned.
	 *
	 * @return the isAssigned
	 */
	public boolean isAssigned() {
		return isAssigned;
	}

	/**
	 * Sets the assigned.
	 *
	 * @param isAssigned the isAssigned to set
	 */
	public void setAssigned(boolean isAssigned) {
		this.isAssigned = isAssigned;
	}

	/**
	 * Gets the postal code.
	 *
	 * @return the postalCode
	 */
	public PostalCode getPostalCode() {
		return postalCode;
	}

	/**
	 * Sets the postal code.
	 *
	 * @param postalCode the postalCode to set
	 */
	public void setPostalCode(PostalCode postalCode) {
		this.postalCode = postalCode;
	}

	/**
	 * Checks if is point zip flag.
	 *
	 * @return true, if is point zip flag
	 */
	public boolean isPointZipFlag() {
		return pointZipFlag;
	}

	/**
	 * Sets the point zip flag.
	 *
	 * @param pointZipFlag the new point zip flag
	 */
	public void setPointZipFlag(boolean pointZipFlag) {
		this.pointZipFlag = pointZipFlag;
	}

	public boolean isLocked() {
		return isLocked;
	}

	public void setLocked(boolean isLocked) {
		this.isLocked = isLocked;
	}


}
