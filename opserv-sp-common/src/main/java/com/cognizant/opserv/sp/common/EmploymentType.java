package com.cognizant.opserv.sp.common;
/**
 * ****************************************************************************.
 *
 * @enum EmploymentType
 * @author Cognizant Technology Solutions
 * @version OpServ 3.0
 * @since 30/03/2016
 * ***************************************************************************
 */
public enum EmploymentType {

	STANDARD(3), PERAMANENT(1), TEMPORARY(2);

	private Integer id;

	private EmploymentType(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return this.id;
	}
	
	/**
	 * Gets the employment type.
	 *
	 * @param id the id
	 * @return the employment type
	 */
	public static EmploymentType getEmploymentType(Integer id) {
		for(EmploymentType employmentType : EmploymentType.values()) { 
	        if(employmentType.getId().equals(id)){
	        	return employmentType;
	        }
	     }
		return null;
	}

}
