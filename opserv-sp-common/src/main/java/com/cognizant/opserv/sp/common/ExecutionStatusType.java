package com.cognizant.opserv.sp.common;
/**
 * ****************************************************************************.
 *
 * @enum ExecutionStatusType 
 * @author Cognizant Technology Solutions
 * @version OpServ 3.0
 * @since 30/03/2016
 * ***************************************************************************
 */
public enum ExecutionStatusType {

	PASS("PASS", 1), FAIL("FAIL", 2), PROCESSING("PROCESSING", 3), COMPLETED_SUCCESS(
			"COMPLETED-SUCCESS", 4), COMPLETED_FAIL("COMPLETED-FAIL", 5), ABORTED(
			"ABORTED", 6);

	private String title;
	private Integer id;

	private ExecutionStatusType(String title, Integer id) {
		this.title = title;
		this.id = id;

	}

	public String getTitle() {
		return title;
	}

	public Integer getId() {
		return this.id;
	}
	
	/**
	 * Gets the execution status type.
	 *
	 * @param id the id
	 * @return the execution status type
	 */
	public static ExecutionStatusType getExecutionStatusType(Integer id) {
		for(ExecutionStatusType executionStatusType : ExecutionStatusType.values()) { 
	        if(executionStatusType.getId().equals(id)){
	        	return executionStatusType;
	        }
	     }
		return null;
	}

}
