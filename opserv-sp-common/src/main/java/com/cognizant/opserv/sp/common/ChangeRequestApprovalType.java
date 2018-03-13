package com.cognizant.opserv.sp.common;

/**
 * ****************************************************************************.
 *
 * @enum ChangeRequestApprovalType holds data from t_wkflw_status
 * @author Cognizant Technology Solutions
 * @version OpServ 3.0
 * @since 30/03/2016
 * ***************************************************************************
 */
public enum ChangeRequestApprovalType {
	/**
	 * values from table - t_wkflw_status
	 */
	APPROVED(3), REJECTED(4), CANCELLED(5), DELEGATED(6), AUTO_REJECTED(7), AUTO_APPROVED(8), 
	PENDING_SUBMISSION(1), PENDING_APPROVAL(2);

	private Integer id;
	
	/**
	 * @param id
	 */
	private ChangeRequestApprovalType(Integer id) {
		this.id=id;
	}

	/**
	 * @return Integer
	 */
	public Integer getId(){
		return id;
	}
	
	/**
	 * Gets the change request approval type.
	 *
	 * @param id the id
	 * @return the change request approval type
	 */
	public static ChangeRequestApprovalType getChangeRequestApprovalType(Integer id) {
		for(ChangeRequestApprovalType changeRequestApprovalType : ChangeRequestApprovalType.values()) { 
	        if(changeRequestApprovalType.getId().equals(id)){
	        	return changeRequestApprovalType;
	        }
	     }
		return null;
	}
}
