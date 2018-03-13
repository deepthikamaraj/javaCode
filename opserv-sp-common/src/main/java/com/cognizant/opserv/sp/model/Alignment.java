package com.cognizant.opserv.sp.model;

import java.io.Serializable;
/**
 * ****************************************************************************.
 *
 * @class Alignment
 * @author Cognizant Technology Solutions
 * @version OpServ 1.0
 * @since 11/05/2016
 * ***************************************************************************
 */
public class Alignment extends BaseModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5601952692293031030L;

	private SalesTeam salesTeam;

	/**
	 * @return the salesTeam
	 */
	public SalesTeam getSalesTeam() {
		return salesTeam;
	}

	/**
	 * @param salesTeam
	 *            the salesTeam to set
	 */
	public void setSalesTeam(SalesTeam salesTeam) {
		this.salesTeam = salesTeam;
	}

}
