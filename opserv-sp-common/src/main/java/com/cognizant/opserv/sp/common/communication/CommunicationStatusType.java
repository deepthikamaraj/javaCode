package com.cognizant.opserv.sp.common.communication;

/**
 * The Enum StatusType.
 */
public enum CommunicationStatusType {

	PUBLISHED(5), /** The published. */
	DRAFT(2), /** The draft. */
	RECEIVED(3); /** The received. */

	/** The id. */
	Integer id;

	/**
	 * The Constructor.
	 * 
	 * @param id
	 *            the id
	 */
	private CommunicationStatusType(int id) {
		this.id = id;
	}

	/**
	 * Gets the id
	 * 
	 * @return the id
	 */
	public Integer getId() {
		return this.id;
	}

	/**
	 * Gets the CommunicationStatus Type.
	 * 
	 * @param id
	 *            the id
	 * @return the CommunicationStatus Type
	 */	
	public static CommunicationStatusType getCommunicationStatusType(Integer id) {
		for (CommunicationStatusType communicationStatusType : CommunicationStatusType.values()) {
			if ((communicationStatusType.getId()).equals(id)) {
				return communicationStatusType;
			}
		}
		return null;
	}
}
