package com.cognizant.opserv.sp.model.metric;

import java.io.Serializable;

import com.cognizant.opserv.sp.model.BaseModel;

/**
 * ****************************************************************************.
 *
 * @class RangeDetails
 * @author Cognizant Technology Solutions
 * @version OpServ 3.0
 * @since 11/05/2016
 * ***************************************************************************
 */
public class RangeDetails extends BaseModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6825017957462535625L;
	
	/** The lower limit. */
	private Float lowerLimit;
	
	/** The upper limit. */
	private Float upperLimit;
	
	/** The color code. */
	private String colorCode;

	/**
	 * Gets the lower limit.
	 *
	 * @return the lower limit
	 */
	public Float getLowerLimit() {
		return lowerLimit;
	}

	/**
	 * Sets the lower limit.
	 *
	 * @param lowerLimit the new lower limit
	 */
	public void setLowerLimit(Float lowerLimit) {
		this.lowerLimit = lowerLimit;
	}

	/**
	 * Gets the upper limit.
	 *
	 * @return the upper limit
	 */
	public Float getUpperLimit() {
		return upperLimit;
	}

	/**
	 * Sets the upper limit.
	 *
	 * @param upperLimit the new upper limit
	 */
	public void setUpperLimit(Float upperLimit) {
		this.upperLimit = upperLimit;
	}

	/**
	 * Gets the color code.
	 *
	 * @return the color code
	 */
	public String getColorCode() {
		return colorCode;
	}

	/**
	 * Sets the color code.
	 *
	 * @param colorCode the new color code
	 */
	public void setColorCode(String colorCode) {
		this.colorCode = colorCode;
	}

}
