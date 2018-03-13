package com.cognizant.opserv.sp.common;

/**
 * ****************************************************************************.
 *
 * @enum AllocationType  
 * @author Cognizant Technology Solutions
 * @version OpServ 3.0
 * @since 30/03/2016
 * ***************************************************************************
 */
public enum ActionType {
	
	SAVE(1),SUBMIT(2);
	
	private Integer id;
	
	
	ActionType(Integer id){
		this.id=id;
	}
	
	public Integer getId(){
		return id;
	}
	
	/**
	 * Gets the action type.
	 *
	 * @param id the id
	 * @return the action type
	 */
	public static ActionType getActionType(Integer id) {
		for(ActionType actionType : ActionType.values()) { 
	        if(actionType.getId().equals(id)){
	        	return actionType;
	        }
	     }
		return null;
	}
	
}
