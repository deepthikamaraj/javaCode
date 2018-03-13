package com.cognizant.opserv.sp.changerequest.json;

import com.cognizant.opserv.sp.model.GeographyAlignment;

/**
 * The Class ChangeRequestZipAlignmentDetailsBlob.
 */
public class ChangeRequestZipAlignmentDetailsBlob {

	/** The previous value. */
	private GeographyAlignment previousValue;

	/** The updated value. */
	private GeographyAlignment updatedValue;

	/**
	 * Gets the previous value.
	 *
	 * @return the previousValue
	 */
	public GeographyAlignment getPreviousValue() {
		return previousValue;
	}

	/**
	 * Sets the previous value.
	 *
	 * @param previousValue the previousValue to set
	 */
	public void setPreviousValue(GeographyAlignment previousValue) {
		this.previousValue = previousValue;
	}

	/**
	 * Gets the updated value.
	 *
	 * @return the updatedValue
	 */
	public GeographyAlignment getUpdatedValue() {
		return updatedValue;
	}

	/**
	 * Sets the updated value.
	 *
	 * @param updatedValue the updatedValue to set
	 */
	public void setUpdatedValue(GeographyAlignment updatedValue) {
		this.updatedValue = updatedValue;
	}

	

}
