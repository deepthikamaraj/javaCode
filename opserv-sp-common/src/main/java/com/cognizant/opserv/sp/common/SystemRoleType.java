package com.cognizant.opserv.sp.common;
/**
 * ****************************************************************************.
 *
 * @enum StatusType
 * @author Cognizant Technology Solutions
 * @version OpServ 3.0
 * @since 30/03/2016
 * ***************************************************************************
 */
public enum SystemRoleType {

	ADMIN(1), HOMEOFFICE(2), MANAGER(3), REPRESENTATIVE(4);

	private Integer id;

	private SystemRoleType(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return this.id;
	}

	/**
	 * Gets the system role type.
	 *
	 * @param id the id
	 * @return the system role type
	 */
	public static SystemRoleType getSystemRoleType(Integer id) {
		for (SystemRoleType systemRoleType : SystemRoleType.values()) {
			if (systemRoleType.getId().equals(id)) {
				return systemRoleType;
			}
		}
		return null;
	}

}
