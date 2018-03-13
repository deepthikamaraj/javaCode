package com.cognizant.opserv.sp.model;

import java.io.Serializable;

/**
 * ****************************************************************************.
 *
 * @class BusinessUnit
 * @author Cognizant Technology Solutions
 * @version OpServ 1.0
 * @since 11/05/2016
 * ***************************************************************************
 */
public class BusinessUnit extends BaseModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5392268342808122086L;

	private SalesOrg salesOrg;
	
	private String description;
	

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the salesOrg
	 */
	public SalesOrg getSalesOrg() {
		return salesOrg;
	}

	/**
	 * @param salesOrg the salesOrg to set
	 */
	public void setSalesOrg(SalesOrg salesOrg) {
		this.salesOrg = salesOrg;
	}

}
