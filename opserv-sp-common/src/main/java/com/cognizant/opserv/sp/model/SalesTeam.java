package com.cognizant.opserv.sp.model;

import java.io.Serializable;
/**
 * ****************************************************************************.
 *
 * @class SalesTeam
 * @author Cognizant Technology Solutions
 * @version OpServ 1.0
 * @since 11/05/2016
 * ***************************************************************************
 */
public class SalesTeam extends BaseModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5941591262610835331L;

	private BusinessUnit businessUnit;

	/**
	 * @return the businessUnit
	 */
	public BusinessUnit getBusinessUnit() {
		return businessUnit;
	}

	/**
	 * @param businessUnit
	 *            the businessUnit to set
	 */
	public void setBusinessUnit(BusinessUnit businessUnit) {
		this.businessUnit = businessUnit;
	}

}
