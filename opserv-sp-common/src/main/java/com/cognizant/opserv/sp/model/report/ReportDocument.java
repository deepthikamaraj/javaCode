package com.cognizant.opserv.sp.model.report;

import com.cognizant.opserv.sp.model.BaseModel;
/**
 * ****************************************************************************.
 *
 * @class ReportDocument
 * @author Cognizant Technology Solutions
 * @version OpServ 1.0
 * @since 11/05/2016
 * ***************************************************************************
 */
public class ReportDocument extends BaseModel {

	private String location;

	/**
	 * @return the location
	 */
	public String getLocation() {
		return location;
	}

	/**
	 * @param location
	 *            the location to set
	 */
	public void setLocation(String location) {
		this.location = location;
	}

}
