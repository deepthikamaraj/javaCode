package com.cognizant.opserv.sp.common;
/**
 * ****************************************************************************.
 *
 * @enum ChangeRequestStatusType holds data from t_wkflw_status
 * @author Cognizant Technology Solutions
 * @version OpServ 3.0
 * @since 30/03/2016
 * ***************************************************************************
 */
public enum ChangeRequestStatusType {
	/**
	 * values from table - t_wkflw_status
	 */
	PENDING_FOR_SUBMISSION(1), 
	PENDING_FOR_APPROVAL(2), 
	APPROVED(3), 
	REJECTED(4),
	DELEGATED(6),
	CANCELLED(5),
	AUTO_APPROVED(8),
	AUTO_REJECTED(7),
	NA(9),
	WORK_IN_PROGRESS(10),
	SUBMISSION_IN_PROGRESS(11),
	APPROVAL_IN_PROGRESS(12),
	REJECTION_IN_PROGRESS(13),
	WITHDRAWL_IN_PROGRESS(14);
	
	
	private Integer id;
	
	/**
	 * @param id
	 */
	private ChangeRequestStatusType(Integer id) {
		this.id=id;
	}

	/**
	 * @return Integer
	 */
	public Integer getId(){
		return id;
	}
	
	/**
	 * Gets the change request status type.
	 *
	 * @param id the id
	 * @return the change request status type
	 */
	public static ChangeRequestStatusType getChangeRequestStatusType(Integer id) {
		for(ChangeRequestStatusType changeRequestStatusType : ChangeRequestStatusType.values()) { 
	        if(changeRequestStatusType.getId().equals(id)){
	        	return changeRequestStatusType;
	        }
	     }
		return null;
	}
	
}
