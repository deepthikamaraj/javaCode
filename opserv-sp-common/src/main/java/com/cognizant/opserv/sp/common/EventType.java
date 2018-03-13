package com.cognizant.opserv.sp.common;
/**
 * ****************************************************************************.
 *
 * @enum EntityStatus
 * @author Cognizant Technology Solutions
 * @version OpServ 3.0
 * @since 30/03/2016
 * ***************************************************************************
 */
public enum EventType {
	
	/**
	 * the status
	 */
	LOCKED("Locked"),
	UNLOCKED("Unlocked"),
	SUBMITTED("Submitted"),
	APPROVED("Approved"),
	WITHDRAWN("Withdrawn"),
	REJECTED("Rejected");
	
	/**
	 * @param event
	 */
	private EventType(String event){
		this.event = event;
	}
	
	/**
	 * the event
	 */
	private String event;
	
	/**
	 * @return event
	 */
	public String getEvent() {
		return event;
	}

	
	/**
	 * @param event
	 * @return
	 */
	public static EventType getTriggeringEvent(String event) {
		for (EventType triggeringEventObject : EventType.values()) {
			if (triggeringEventObject.equals(event)) {
				return triggeringEventObject;
			}
		}
		return null;
	}
	
}
