package com.cognizant.opserv.sp.common;
/**
 * ****************************************************************************.
 *
 * @enum EntityType - ENum class for different entity
 * @author Cognizant Technology Solutions
 * @version OpServ 3.0
 * @since 30/03/2016
 * ***************************************************************************
 */
public enum EntityType {

	/**
	 * values from table -t_buss_entity
	 */
	CUSTOMER(2,"CUSTOMER"),
	PRODUCT(3, "PRODUCT"),
	EMPLOYEE(4, "EMPLOYEE"),
	SALESPOSITION(8, "SALESPOSITION"),
	CALLPLANNING(17, "CALLPLANNING"),
	SALESPOSITION_CUSTOMER(14, "SALESPOSITION_CUSTOMER"),
	SALESPOSITION_PRODUCT(15, "SALESPOSITION_PRODUCT"),
	SALESPOSITION_EMPLOYEE(16, "SALESPOSITION_EMPLOYEE"),
	SALES_TEAM_CUSTOMER(24, "SALES_TEAM_CUSTOMER"),
	SALES_TEAM_PRODUCT(25, "SALES_TEAM_PRODUCT"),
	SALES_TEAM_CUSTOMER_PRODUCT(26, "SALES_TEAM_CUSTOMER_PRODUCT"),
	SALESPOSITION_CUSTOMER_VIEW(28,"SALESPOSITION_CUSTOMER_VIEW"),
	CHANGE_REQUEST_VIEW(30,"CHANGE_REQUEST_VIEW");

	/**
	 * The id
	 */
	private Integer id;
	
	/**
	 * The name
	 */
	private String name;

	/**
	 * @param id
	 * @param name
	 */
	private EntityType(Integer id, String name) {
		this.id = id;
		this.name = name;
	}

	/**
	 * @return
	 */
	public Integer getId() {
		return this.id;
	}
	
	/**
	 * @return
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Gets the entity type.
	 *
	 * @param id the id
	 * @return the entity type
	 */
	public static EntityType getEntityType(Integer id) {
		for(EntityType entityType : EntityType.values()) { 
	        if(entityType.getId().equals(id)){
	        	return entityType;
	        }
	     }
		return null;
	}

}
