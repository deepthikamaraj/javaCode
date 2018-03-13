package com.cognizant.opserv.sp.common.communication;

public enum AckStatusType {


	
	/** The PENDING , The ACCEPTED , The REJECTED , The NOTAPPLICABLE */
	PENDING(1),ACCEPTED(2),REJECTED(3),NOTAPPLICABLE(4);
	
	/** The id */
	Integer id;

	/**
	 * @method AcknowledgementStatusType- Instantiates a new AcknowledgementStatusType
	 * @param id - the id
	 */
	private AckStatusType(int id) {
		this.id = id;
	}
	
	/**
	 * @method getId
	 * @return Integer - the id
	 */
	public Integer getId() {
		return this.id;
	}	
	
	/**
	 * Gets the AckStatus Type.
	 * 
	 * @param id
	 *            the id
	 * @return the AckStatus Type
	 */
	public static AckStatusType getAckStatusType(Integer id) {
		for(AckStatusType ackStatusType : AckStatusType.values()) { 
	        if((ackStatusType.getId()).equals(id)) {
	        	return ackStatusType;
	        }
	     }
		return null;
	}


}
