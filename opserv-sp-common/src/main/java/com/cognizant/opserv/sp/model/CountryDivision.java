package com.cognizant.opserv.sp.model;

import java.io.Serializable;

/**
 * The Class CountryDivision.
 */
public class CountryDivision extends BaseModel implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5905542774590960725L;
	
	/** The country. */
	private Country country;
	
	/** The parent country division. */
	private CountryDivision parentCountryDivision;
	
	/** The lookup table. */
	private String lookupTable;
	
	/** The lowest div flag. */
	private boolean lowestDivFlag;
	
	/** The extents div flag. */
	private boolean extentsDivFlag;
	
	/** The lookup column. */
	private String lookupColumn;
	
	/** The point lookup table. */
	private String pointLookupTable;
	
	/** The point lookup column. */
	private String pointLookupColumn;

	/**
	 * Gets the country.
	 *
	 * @return the country
	 */
	public Country getCountry() {
		return country;
	}

	/**
	 * Sets the country.
	 *
	 * @param country the new country
	 */
	public void setCountry(Country country) {
		this.country = country;
	}

	/**
	 * Gets the parent country division.
	 *
	 * @return the parent country division
	 */
	public CountryDivision getParentCountryDivision() {
		return parentCountryDivision;
	}

	/**
	 * Sets the parent country division.
	 *
	 * @param parentCountryDivision the new parent country division
	 */
	public void setParentCountryDivision(CountryDivision parentCountryDivision) {
		this.parentCountryDivision = parentCountryDivision;
	}

	/**
	 * Gets the lookup table.
	 *
	 * @return the lookup table
	 */
	public String getLookupTable() {
		return lookupTable;
	}

	/**
	 * Sets the lookup table.
	 *
	 * @param lookupTable the new lookup table
	 */
	public void setLookupTable(String lookupTable) {
		this.lookupTable = lookupTable;
	}

	/**
	 * Checks if is lowest div flag.
	 *
	 * @return true, if is lowest div flag
	 */
	public boolean isLowestDivFlag() {
		return lowestDivFlag;
	}

	/**
	 * Sets the lowest div flag.
	 *
	 * @param lowestDivFlag the new lowest div flag
	 */
	public void setLowestDivFlag(boolean lowestDivFlag) {
		this.lowestDivFlag = lowestDivFlag;
	}

	/**
	 * Checks if is extents div flag.
	 *
	 * @return true, if is extents div flag
	 */
	public boolean isExtentsDivFlag() {
		return extentsDivFlag;
	}

	/**
	 * Sets the extents div flag.
	 *
	 * @param extentsDivFlag the new extents div flag
	 */
	public void setExtentsDivFlag(boolean extentsDivFlag) {
		this.extentsDivFlag = extentsDivFlag;
	}

	/**
	 * Gets the lookup column.
	 *
	 * @return the lookup column
	 */
	public String getLookupColumn() {
		return lookupColumn;
	}

	/**
	 * Sets the lookup column.
	 *
	 * @param lookupColumn the new lookup column
	 */
	public void setLookupColumn(String lookupColumn) {
		this.lookupColumn = lookupColumn;
	}

	/**
	 * Gets the point lookup table.
	 *
	 * @return the point lookup table
	 */
	public String getPointLookupTable() {
		return pointLookupTable;
	}

	/**
	 * Sets the point lookup table.
	 *
	 * @param pointLookupTable the new point lookup table
	 */
	public void setPointLookupTable(String pointLookupTable) {
		this.pointLookupTable = pointLookupTable;
	}

	/**
	 * Gets the point lookup column.
	 *
	 * @return the point lookup column
	 */
	public String getPointLookupColumn() {
		return pointLookupColumn;
	}

	/**
	 * Sets the point lookup column.
	 *
	 * @param pointLookupColumn the new point lookup column
	 */
	public void setPointLookupColumn(String pointLookupColumn) {
		this.pointLookupColumn = pointLookupColumn;
	}
}
