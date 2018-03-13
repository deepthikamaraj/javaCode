package com.cognizant.opserv.sp.common;
/**
 * ****************************************************************************.
 *
 * @enum EmployeeStatusType
 * @author Cognizant Technology Solutions
 * @version OpServ 3.0
 * @since 30/03/2016
 * ***************************************************************************
 */
public enum EmployeeStatusType {

	ACTIVE(1), INACTIVE(2), UNASSIGNED(3), ASSIGNED(4), TERMINATED(5);

	private Integer id;

	private EmployeeStatusType(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return this.id;
	}
	
	/**
	 * Gets the employee status type.
	 *
	 * @param id the id
	 * @return the employee status type
	 */
	public static EmployeeStatusType getEmployeeStatusType(Integer id) {
		for(EmployeeStatusType employeeStatusType : EmployeeStatusType.values()) { 
	        if(employeeStatusType.getId().equals(id)){
	        	return employeeStatusType;
	        }
	     }
		return null;
	}

}
