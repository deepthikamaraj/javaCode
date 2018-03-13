package com.cognizant.opserv.sp.common.communication;

/**
 * The Enum ActivityStatusType.
 */
public enum ActivityStatusType {

	/** The published. */
	PUBLISHED(5), /** The draft. */
	DRAFT(2), /** The received. */
	RECEIVED(3), /** The deleted. */
	DELETED(4), /** The readytopublish. */
	READYTOPUBLISH(1);

	/** The id. */
	Integer id;

	/**
	 * The Constructor.
	 * 
	 * @param id
	 *            the id
	 */
	private ActivityStatusType(int id) {
		this.id = id;
	}

	/**
	 * Gets the value.
	 * 
	 * @return the value
	 */
	public Integer getId() {
		return this.id;
	}
	
	/**
	 * Gets the ActivityStatus Type.
	 * 
	 * @param id
	 *            the id
	 * @return the ActivityStatus Type
	 */	
	public static ActivityStatusType getActivityStatusType(Integer id) {
		for(ActivityStatusType activityStatusType : ActivityStatusType.values()) { 
	        if((activityStatusType.getId()).equals(id)) {
	        	return activityStatusType;
	        }
	     }
		return null;
	}

}
