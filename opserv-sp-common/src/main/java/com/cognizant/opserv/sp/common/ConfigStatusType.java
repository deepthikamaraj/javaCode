package com.cognizant.opserv.sp.common;
/**
 * ****************************************************************************.
 *
 * @enum ConfigStatusType 
 * @author Cognizant Technology Solutions
 * @version OpServ 3.0
 * @since 30/03/2016
 * ***************************************************************************
 */
public enum ConfigStatusType {

	DRAFT(1), SCHEDULED(2), HALTED(3), DELETED(4);

	private Integer id;
	
	/**
	 * @param id
	 */
	private ConfigStatusType(Integer id) {
		this.id=id;
	}

	/**
	 * @return Integer
	 */
	public Integer getId(){
		return id;
	}
	
	/**
	 * Gets the config status type.
	 *
	 * @param id the id
	 * @return the config status type
	 */
	public static ConfigStatusType getConfigStatusType(Integer id) {
		for(ConfigStatusType configStatusType : ConfigStatusType.values()) { 
	        if(configStatusType.getId().equals(id)){
	        	return configStatusType;
	        }
	     }
		return null;
	}
}
