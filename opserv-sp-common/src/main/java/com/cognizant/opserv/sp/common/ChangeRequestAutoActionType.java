package com.cognizant.opserv.sp.common;
/**
 * ****************************************************************************.
 *
 * @enum ChangeRequestAutoActionType holds data from t_wkflw_status
 * @author Cognizant Technology Solutions
 * @version OpServ 3.0
 * @since 30/03/2016
 * ***************************************************************************
 */
public enum ChangeRequestAutoActionType {
	/**
	 * form table - t_wkflw_status
	 */
	AUTO_APPROVE("approve"),
	AUTO_REJECT("reject");
	
	private String autoAction;
	
	/**
	 * @param autoAction
	 */
	private ChangeRequestAutoActionType(String autoAction) {
		this.autoAction=autoAction;
	}

	/**
	 * @return String
	 */
	public String getAutoAction(){
		return autoAction;
	}
	
	/**
	 * Gets the change request auto action type.
	 *
	 * @param id the id
	 * @return the change request auto action type
	 */
	public static ChangeRequestAutoActionType getChangeRequestAutoActionType(String autoAction) {
		for (ChangeRequestAutoActionType changeRequestAutoActionType : ChangeRequestAutoActionType.values()) {
			if (changeRequestAutoActionType.getAutoAction().equals(autoAction)) {
				return changeRequestAutoActionType;
			}
		}
		return null;
	}
}
