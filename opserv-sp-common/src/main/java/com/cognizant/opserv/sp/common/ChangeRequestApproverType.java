package com.cognizant.opserv.sp.common;
/**
 * ****************************************************************************.
 *
 * @enum ChangeRequestApproverType holds data from t_chng_req_appr_type
 * @author Cognizant Technology Solutions
 * @version OpServ 3.0
 * @since 30/03/2016
 * ***************************************************************************
 */
public enum ChangeRequestApproverType {
	
	OWNER(1),
	PEER(4),
	IMMEDIATE(3),
	WHOLEHIERARCHY(2),
	PARTIALHIERARCHY(5);
	
	private Integer id;
	
	/**
	 * @param id
	 */
	private ChangeRequestApproverType(Integer id) {
		this.id=id;
	}

	/**
	 * @return Integer
	 */
	public Integer getId(){
		return id;
	}
	
	/**
	 * Gets the change request approver type.
	 *
	 * @param id the id
	 * @return the change request approver type
	 */
	public static ChangeRequestApproverType getChangeRequestApproverType(Integer id) {
		for(ChangeRequestApproverType changeRequestApproverType : ChangeRequestApproverType.values()) { 
	        if(changeRequestApproverType.getId().equals(id)){
	        	return changeRequestApproverType;
	        }
	     }
		return null;
	}

}
