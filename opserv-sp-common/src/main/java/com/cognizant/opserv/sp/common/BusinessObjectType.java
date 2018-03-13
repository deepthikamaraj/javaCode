package com.cognizant.opserv.sp.common;
/**
 * ****************************************************************************.
 *
 * @enum BusinessObjectType holds data from t_buss_obj
 * @author Cognizant Technology Solutions
 * @version OpServ 3.0
 * @since 30/03/2016
 * ***************************************************************************
 */
public enum BusinessObjectType {

	/**
	 * values from table -t_buss_obj
	 */
	CUSTOMER(2),
	PRODUCT(3),
	EMPLOYEE(4),
	SALESPOSITION(5),
	CALLPLANNING(6),
	SALESPOSITION_CUSTOMER(9),
	SALESPOSITION_PRODUCT(10),
	SALESPOSITION_EMPLOYEE(11);

	private Integer id;

	private BusinessObjectType(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return this.id;
	}
	
	/**
	 * Gets the entity type.
	 *
	 * @param id the id
	 * @return the entity type
	 */
	public static BusinessObjectType getEntityType(Integer id) {
		for(BusinessObjectType businessObjectType : BusinessObjectType.values()) { 
	        if(businessObjectType.getId().equals(id)){
	        	return businessObjectType;
	        }
	     }
		return null;
	}
}
