package com.cognizant.opserv.sp.common;
/**
 * ****************************************************************************.
 *
 * @enum AttributeType 
 * @author Cognizant Technology Solutions
 * @version OpServ 3.0
 * @since 30/03/2016
 * ***************************************************************************
 */
public enum AttributeType {

	NUMBER(1), DATE(2), TEXT(3), CHECKBOX(5), RADIO(6), LIST(7), LONGTEXT(8), DATETIME(4);
	
	private Integer id;
	/**
	 * @param id
	 */
	AttributeType(Integer id){
		this.id=id;
	}

	public Integer getId(){
		return id;
	}
	
	/**
	 * Gets the attribute type.
	 *
	 * @param id the id
	 * @return the attribute type
	 */
	public static AttributeType getAttributeType(Integer id) {
		for(AttributeType attributeType : AttributeType.values()) { 
	        if(attributeType.getId().equals(id)){
	        	return attributeType;
	        }
	     }
		return null;
	}
}
