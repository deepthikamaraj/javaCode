package com.cognizant.opserv.sp.common;

public enum RuleType {

	INTRANGE(1),DATERANGE(2),REGULAREXPRESSON(3),DATETIMERANGE(4);
	
private Integer id;
	
	RuleType(Integer id){
		this.id=id;
	}

	public Integer getId(){
		return id;
	}
	
	/**
	 * Gets the RULE type.
	 *
	 * @param id the id
	 * @return the Rule type
	 */
	public static RuleType getRuleType(Integer id) {
		for(RuleType ruleType : RuleType.values()) { 
	        if(ruleType.getId().equals(id)){
	        	return ruleType;
	        }
	     }
		return null;
	}
}
