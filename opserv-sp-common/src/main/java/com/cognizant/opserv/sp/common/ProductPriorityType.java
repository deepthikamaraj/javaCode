package com.cognizant.opserv.sp.common;
/**
 * ****************************************************************************.
 *
 * @enum ProductPriorityType
 * @author Cognizant Technology Solutions
 * @version OpServ 3.0
 * @since 30/03/2016
 * ***************************************************************************
 */
public enum ProductPriorityType {

	PRIMARY(1), SECONDARY(2), TERTIARY(3), QUARTERNARY(4);

	private int id;

	private ProductPriorityType(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
