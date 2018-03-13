package com.cognizant.opserv.sp.common.communication;


/**
 * The Enum CommType.
 */
public enum CommunicationType {

	/** The announcement. */
	ANNOUNCEMENT(1),
	/** The plandocument. */
	PLANDOCUMENT(2),
	/** The Activity. */
	ACTIVITY(3);

	/** The id. */
	int id;

	/**
	 * The Constructor.
	 * 
	 * @param id
	 *            the id
	 */
	private CommunicationType(int id) {
		this.id = id;
	}

	/**
	 * Gets the value.
	 * 
	 * @return the value
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * Gets the Communication Type.
	 * 
	 * @param id
	 *            the id
	 * @return the Communication Type
	 */
	public static CommunicationType getCommunicationType(Integer id) {
		for (CommunicationType communicationType : CommunicationType.values()) {
			if ((communicationType.getId()).equals(id)) {
				return communicationType;
			}
		}
		return null;
	}

}
