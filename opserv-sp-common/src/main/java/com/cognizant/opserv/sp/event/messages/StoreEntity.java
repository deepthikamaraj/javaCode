/**
 * 
 */
package com.cognizant.opserv.sp.event.messages;

/**
 * ****************************************************************************
 * 
 * @class SPEntityType
 * @author Cognizant Technology Solutions
 * @version OpServ 1.0
 * @since 01/04/2016
 * ***************************************************************************
 */
public enum StoreEntity {
	PRODUCT(1,"PRODUCT"),PRODUCT_ALIGNMENT(2, "PRODUCT_ALIGNMENT"),CUSTOMER(3, "CUSTOMER"),CUSTOMER_ALIGNMENT(4, "CUSTOMER_ALIGNMENT"),
	EMPLOYEE(5,"EMPLOYEE"),EMPLOYEE_ALIGNMENT(6,"EMPLOYEE_ALIGNMENT"),CUSTOMER_PRODUCT(7,"CUSTOMER_PRODUCT"),CALL_PLAN(8,"CALL_PALN"),
	GEO_ALIGNMENT(9,"GEO_ALIGNMENT"),CUSTOMER_ALIGNMENT_NON_POA(10,"CUSTOMER_ALIGNMENT_NON_POA"), CUSTOMER_PRODUCT_NON_POA(11,"CUSTOMER_PRODUCT_NON_POA");
	
	
	/** The type id. */
	private int typeId;
	
	/** The type name. */
	private String typeName;
	
	
	/**
	 * @param typeId
	 * @param typeName
	 */
	StoreEntity(int typeId, String typeName){
		this.typeId = typeId;
		this.typeName = typeName;
	}

	
	/**
	 * @return typeId
	 */
	public int getTypeId() {
		return typeId;
	}

	
	/**
	 * @return typeName
	 */
	public String getTypeName() {
		return typeName;
	}
}
