package com.cognizant.opserv.sp.model;

import java.io.Serializable;

/**
 * The Class Country.
 */
public class Country extends BaseModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3766963208943305127L;
	
	/** The postal code name. */
	private String postalCodeName;
	
	/**
	 * Gets the postal code name.
	 *
	 * @return the postal code name
	 */
	public String getPostalCodeName() {
		return postalCodeName;
	}

	/**
	 * Sets the postal code name.
	 *
	 * @param postalCodeName the new postal code name
	 */
	public void setPostalCodeName(String postalCodeName) {
		this.postalCodeName = postalCodeName;
	}

}
