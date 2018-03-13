package com.cognizant.opserv.sp.common;
/**
 * ****************************************************************************.
 *
 * @enum ChangeRequestTriggerType holds data from t_chng_req_trigger
 * value[feature_id]_value[type_id]
 * `t_chng_req_feature`_`t_feature_type`
 * @author Cognizant Technology Solutions
 * @version OpServ 3.0
 * @since 30/03/2016
 * ***************************************************************************
 */
public enum ChangeRequestTriggerType {
	/**
	 * values from table -t_chng_req_trigger 
	 */
	UNASSIGN_CUSTOMER(3),
	PUSH_CUSTOMER(16),
	PULL_CUSTOMER(17),
	PUSH_ZIP(18),
	UNASSIGN_ZIP(15),
	EDIT_CUSTOMER(2);

	private Integer id;

	/**
	 * @param id
	 */
	private ChangeRequestTriggerType(Integer id) {
		this.id = id;
	}

	/**
	 * @return Integer
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * Gets the changeRequest trigger type.
	 *
	 * @param id
	 * @return changeRequestTriggerType
	 */
	public static ChangeRequestTriggerType getChangeRequestTriggerType(Integer id) {
		for (ChangeRequestTriggerType changeRequestTriggerType : ChangeRequestTriggerType.values()) {
			if (changeRequestTriggerType.getId().equals(id)) {
				return changeRequestTriggerType;
			}
		}
		return null;
	}
}
