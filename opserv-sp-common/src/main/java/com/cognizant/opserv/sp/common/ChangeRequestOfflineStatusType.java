/**
 * 
 */
package com.cognizant.opserv.sp.common;

public enum ChangeRequestOfflineStatusType {
	
	INITIATED(1), PROCESSING(2), COMPLETED(3), CANCELLED(4);
	
	private Integer id;
	
	private ChangeRequestOfflineStatusType(Integer id){
		this.setId(id);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	/**
	 * Gets the Change Request Offline Status Type.
	 *
	 * @param id the id
	 * @return the change request status type
	 */
	public static  ChangeRequestOfflineStatusType getChangeRequestOfflineStatusType(Integer id) {
		for(ChangeRequestOfflineStatusType changeRequestOfflineStatusType : ChangeRequestOfflineStatusType.values()) { 
	        if(changeRequestOfflineStatusType.getId()==id){
	        	return changeRequestOfflineStatusType;
	        }
	     }
		return null;
	}

}
