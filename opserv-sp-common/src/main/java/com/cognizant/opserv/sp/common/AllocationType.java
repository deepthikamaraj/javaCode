package com.cognizant.opserv.sp.common;
/**
 * ****************************************************************************.
 *
 * @enum AllocationType  
 * @author Cognizant Technology Solutions
 * @version OpServ 3.0
 * @since 30/03/2016
 * ***************************************************************************
 */
public enum AllocationType {
	
	PRIMARY(1),SECONDARY(2);
	
	private Integer id;
	
	private AllocationType(Integer id) {
		this.id=id;
	}

	public Integer getId(){
		return id;
	}
	
	/**
	 * Gets the allocation type.
	 *
	 * @param id the id
	 * @return the allocation type
	 */
	public static AllocationType getAllocationType(Integer id) {
		for(AllocationType allocationType : AllocationType.values()) { 
	        if(allocationType.getId().equals(id)){
	        	return allocationType;
	        }
	     }
		return null;
	}
}
