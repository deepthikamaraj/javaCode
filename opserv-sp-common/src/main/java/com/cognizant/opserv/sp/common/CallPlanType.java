package com.cognizant.opserv.sp.common;
/**
 * ****************************************************************************.
 *
 * @enum CallPlanType holds data from t_call_plan_type
 * @author Cognizant Technology Solutions
 * @version OpServ 3.0
 * @since 30/03/2016
 * ***************************************************************************
 */
public enum CallPlanType {

	BASE(1), PLANNED(2);
	
	private Integer id;
	
	private CallPlanType(Integer id) {
		this.id=id;
	}

	public Integer getId(){
		return id;
	}
	
	/**
	 * Gets the attribute type.
	 *
	 * @param id the id
	 * @return the attribute type
	 */
	public static CallPlanType getAttributeType(Integer id) {
		for(CallPlanType callPlanType : CallPlanType.values()) { 
	        if(callPlanType.getId().equals(id)){
	        	return callPlanType;
	        }
	     }
		return null;
	}
}
