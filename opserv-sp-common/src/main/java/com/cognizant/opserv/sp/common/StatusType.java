package com.cognizant.opserv.sp.common;
/**
 * ****************************************************************************.
 *
 * @enum StatusType - values from table t_wkflw_status
 * @author Cognizant Technology Solutions
 * @version OpServ 3.0
 * @since 30/03/2016
 * ***************************************************************************
 */
public enum StatusType {
	/**
	 * values from table -t_wkflw_status
	 */
	APPROVED(3), REJECTED(4), CANCELLED(5), DELEGATED(6), AUTO_REJECTED(7), AUTO_APPROVED(
			8), PENDING_SUBMISSION(1), PENDING_APPROVAL(2);

	private Integer id;

	private StatusType(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return this.id;
	}

	/**
	 * Gets the status type.
	 *
	 * @param id the id
	 * @return the status type
	 */
	public static StatusType getStatusType(Integer id) {
		for (StatusType statusType : StatusType.values()) {
			if (statusType.getId().equals(id)) {
				return statusType;
			}
		}
		return null;
	}

}
