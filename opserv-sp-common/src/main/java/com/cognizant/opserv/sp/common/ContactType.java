package com.cognizant.opserv.sp.common;
/**
 * ****************************************************************************.
 *
 * @enum ContactType 
 * @author Cognizant Technology Solutions
 * @version OpServ 3.0
 * @since 30/03/2016
 * ***************************************************************************
 */
public enum ContactType {

	EMAIL(1), PHONE(2), MOBILE(4), FAX(3);

	private Integer id;
	
	private ContactType(Integer id) {
		this.id=id;
	}

	public Integer getId(){
		return id;
	}
	
	/**
	 * Gets the contact type.
	 *
	 * @param id the id
	 * @return the contact type
	 */
	public static ContactType getContactType(Integer id) {
		for(ContactType contactType : ContactType.values()) { 
	        if(contactType.getId().equals(id)){
	        	return contactType;
	        }
	     }
		return null;
	}
}
