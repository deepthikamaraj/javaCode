package com.cognizant.opserv.sp.common;
/**
 * ****************************************************************************.
 *
 * @enum CustomerUniverseType
 * @author Cognizant Technology Solutions
 * @version OpServ 3.0
 * @since 30/03/2016
 * ***************************************************************************
 */
public enum CustomerUniverseType {
	
	POA(1),NONPOA(2),ALL(3);
	
private Integer id;
	
	private CustomerUniverseType(Integer id) {
		this.id=id;
	}

	public Integer getId(){
		return id;
	}

	/**
	 * Gets the customer universe type.
	 *
	 * @param id the id
	 * @return the customer universe type
	 */
	public static CustomerUniverseType getCustomerUniverseType(Integer id) {
		for (CustomerUniverseType customerUniverseType : CustomerUniverseType
				.values()) {
			if (customerUniverseType.getId().equals(id)) {
				return customerUniverseType;
			}
		}
		return null;
	}

}
