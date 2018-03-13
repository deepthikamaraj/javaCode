package com.cognizant.opserv.sp.json;

import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonAutoDetect.Visibility;
// TODO: Auto-generated Javadoc
/**
 * ****************************************************************************.
 *
 * @class GeoInfo
 * @author Cognizant Technology Solutions
 * @version OpServ 1.0
 * @since 11/05/2016
 * ***************************************************************************
 */
@JsonAutoDetect(fieldVisibility = Visibility.ANY, getterVisibility = Visibility.NONE, setterVisibility = Visibility.NONE)	
public class GeoInfo {
	
	/** The zip. */
	@JsonProperty("z")
	private String zip;
	
	/** The geo code. */
	@JsonProperty("ge")
	private long geoCode;
	
	/** The comp eligible customer count. */
	@JsonProperty("cecc")
	private int compEligibleCustomerCount;
	
	/** The comp geo aligned customer count. */
	@JsonProperty("cgacc")
	private int compGeoAlignedCustomerCount;

	/**
	 * Gets the zip.
	 *
	 * @return the zip
	 */
	public String getZip() {
		return zip;
	}

	/**
	 * Sets the zip.
	 *
	 * @param zip the new zip
	 */
	public void setZip(String zip) {
		this.zip = zip;
	}

	/**
	 * Gets the geo code.
	 *
	 * @return the geo code
	 */
	public long getGeoCode() {
		return geoCode;
	}

	/**
	 * Sets the geo code.
	 *
	 * @param geoCode the new geo code
	 */
	public void setGeoCode(long geoCode) {
		this.geoCode = geoCode;
	}

	/**
	 * Gets the comp eligible customer count.
	 *
	 * @return the comp eligible customer count
	 */
	public int getCompEligibleCustomerCount() {
		return compEligibleCustomerCount;
	}

	/**
	 * Sets the comp eligible customer count.
	 *
	 * @param compEligibleCustomerCount the new comp eligible customer count
	 */
	public void setCompEligibleCustomerCount(int compEligibleCustomerCount) {
		this.compEligibleCustomerCount = compEligibleCustomerCount;
	}

	/**
	 * Gets the comp geo aligned customer count.
	 *
	 * @return the comp geo aligned customer count
	 */
	public int getCompGeoAlignedCustomerCount() {
		return compGeoAlignedCustomerCount;
	}

	/**
	 * Sets the comp geo aligned customer count.
	 *
	 * @param compGeoAlignedCustomerCount the new comp geo aligned customer count
	 */
	public void setCompGeoAlignedCustomerCount(int compGeoAlignedCustomerCount) {
		this.compGeoAlignedCustomerCount = compGeoAlignedCustomerCount;
	}
	
}
