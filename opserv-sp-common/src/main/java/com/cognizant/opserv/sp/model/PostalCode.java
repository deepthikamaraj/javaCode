package com.cognizant.opserv.sp.model;

import java.io.Serializable;
/**
 * ****************************************************************************.
 *
 * @class PostalCode
 * @author Cognizant Technology Solutions
 * @version OpServ 1.0
 * @since 11/05/2016
 * ***************************************************************************
 */
public class PostalCode extends BaseModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 955089018054467181L;

	private boolean isShapeAvailable;

	private long geoCode;

	/**
	 * @return the isShapeAvailable
	 */
	public boolean isShapeAvailable() {
		return isShapeAvailable;
	}

	/**
	 * @param isShapeAvailable
	 *            the isShapeAvailable to set
	 */
	public void setShapeAvailable(boolean isShapeAvailable) {
		this.isShapeAvailable = isShapeAvailable;
	}

	/**
	 * @return the geoCode
	 */
	public long getGeoCode() {
		return geoCode;
	}

	/**
	 * @param geoCode
	 *            the geoCode to set
	 */
	public void setGeoCode(long geoCode) {
		this.geoCode = geoCode;
	}

}
