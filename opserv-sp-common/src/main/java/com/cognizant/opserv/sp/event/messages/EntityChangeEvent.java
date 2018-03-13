package com.cognizant.opserv.sp.event.messages;

import java.io.Serializable;

import com.cognizant.opserv.sp.common.EventType;
import com.cognizant.opserv.sp.common.MessageType;
import com.cognizant.opserv.sp.model.auth.UserDetails;

/**
 * ****************************************************************************
 * 
 * @class EntityChangeEvent
 * @author Cognizant Technology Solutions
 * @version OpServ 3.0
 * @since 01/04/2016
 * ***************************************************************************
 */
public class EntityChangeEvent implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -406813555555213912L;

	/** sales pos id */
	private Long salesPosid;
	
	/** entity id. */
	private Long entityId;
	
	/** zip code. */
	private String zipCode;
	
	/** type of the business entity. */
	private String bussObjName;
	
	/** userDetails. */
	private UserDetails userDetails;
	
	/** message Type. */
	private MessageType messageType;
	
	/** eventType. */
	private EventType eventType;
	
	
	/**
	 * @return - the eventType
	 */
	public EventType getEventType() {
		return eventType;
	}

	/**
	 * @param eventType
	 */
	public void setEventType(EventType eventType) {
		this.eventType = eventType;
	}

	/**
	 * @return the bussObjName
	 */
	public String getBussObjName() {
		return bussObjName;
	}

	/**
	 * @param bussObjName the bussObjName to set
	 */
	public void setBussObjName(String bussObjName) {
		this.bussObjName = bussObjName;
	}

	/**
	 * @return salesPosid - the sales pos id
	 */
	public Long getSalesPosid() {
		return salesPosid;
	}

	/**
	 * @param salesPosid - the sales pos id
	 */
	public void setSalesPosid(Long salesPosid) {
		this.salesPosid = salesPosid;
	}

	/**
	 * @return entityId - the entityId
	 */
	public Long getEntityId() {
		return entityId;
	}

	/**
	 * @param entityId - the entityId
	 */
	public void setEntityId(Long entityId) {
		this.entityId = entityId;
	}

	/**
	 * @return userDetails - the userDetails obj
	 */
	public UserDetails getUserDetails() {
		return userDetails;
	}

	/**
	 * @param userDetails - the userDetails obj
	 */
	public void setUserDetails(UserDetails userDetails) {
		this.userDetails = userDetails;
	}

	/**
	 * @return MessageType - the messageType obj
	 */
	public MessageType getMessageType() {
		return messageType;
	}

	/**
	 * @param MessageType - the messageType obj
	 */
	public void setMessageType(MessageType messageType) {
		this.messageType = messageType;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	
	

	
}
