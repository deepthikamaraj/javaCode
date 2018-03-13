package com.cognizant.opserv.sp.model.communication;

import com.cognizant.opserv.sp.common.NotificationType;
import com.cognizant.opserv.sp.common.communication.CommunicationType;


/**
 * ****************************************************************************.
 * Model class for Announcement
 * @class Announcement
 * @author Cognizant Technology Solutions
 * @version OpServ 3.0
 * @since 23/06/2016
 * ***************************************************************************
 */
public class Announcement  extends Communication {
	
	private boolean isAckRequired;
	private CommunicationType type;
	private boolean isPriority;
	private NotificationType notification;
	private Document  document;
	
	/**
	 * @return the document
	 */
	public Document getDocument() {
		return document;
	}
	
	/**
	 * @param document
	 *            the document to set
	 */
	public void setDocument(Document document) {
		this.document = document;
	}
	
	/**
	 * @return the isAckRequired
	 */
	public boolean isAckRequired() {
		return isAckRequired;
	}
	
	/**
	 * @param isAckRequired
	 *            the isAckRequired to set
	 */
	public void setAckRequired(boolean isAckRequired) {
		this.isAckRequired = isAckRequired;
	}
	
	/**
	 * @return the type
	 */
	public CommunicationType getType() {
		return type;
	}
	
	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(CommunicationType type) {
		this.type = type;
	}
	
	/**
	 * @return the isPriority
	 */
	public boolean isPriority() {
		return isPriority;
	}
	
	/**
	 * @param isPriority
	 *            the isPriority to set
	 */
	public void setPriority(boolean isPriority) {
		this.isPriority = isPriority;
	}
	
	/**
	 * @return the notification
	 */
	public NotificationType getNotification() {
		return notification;
	}
	
	/**
	 * @param notification
	 *            the notification to set
	 */
	public void setNotification(NotificationType notification) {
		this.notification = notification;
	}

}
