package com.cognizant.opserv.sp.model;

import java.io.Serializable;


/**
 * ****************************************************************************.
 *
 * @class ProductCallPlan
 * @author Cognizant Technology Solutions
 * @version OpServ 1.0
 * @since 11/05/2016
 * ***************************************************************************
 */
public class ProductCallPlan extends BaseModel implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -6888783742902549381L;

	/** The planned calls. */
	private int plannedCalls;

	/**
	 * Gets the planned calls.
	 *
	 * @return the planned calls
	 */
	public int getPlannedCalls() {
		return plannedCalls;
	}

	/**
	 * Sets the planned calls.
	 *
	 * @param plannedCalls the new planned calls
	 */
	public void setPlannedCalls(int plannedCalls) {
		this.plannedCalls = plannedCalls;
	}

}
