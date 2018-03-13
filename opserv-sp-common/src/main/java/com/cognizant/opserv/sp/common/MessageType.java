package com.cognizant.opserv.sp.common;

/**
 * ****************************************************************************.
 *
 * @enum MessageType  
 * @author Cognizant Technology Solutions
 * @version OpServ 3.0
 * @since 30/03/2016
 * ***************************************************************************
 */
public enum MessageType {

	VIEW(1), SCR(2);

	private Integer id;

	private MessageType(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return this.id;
	}
	
	/**
	 * Gets the message type.
	 *
	 * @param id the id
	 * @return the message type
	 */
	public static MessageType getMessageType(Integer id) {
		for (MessageType messageType : MessageType.values()) {
			if (messageType.getId() == id) {
				return messageType;
			}
		}
		return null;
	}
	
}
