package com.cognizant.opserv.sp.model.communication;

import java.util.Date;

import com.cognizant.opserv.sp.common.NoteStatus;
import com.cognizant.opserv.sp.common.NotificationType;
import com.cognizant.opserv.sp.common.communication.AckStatusType;
/**
 * ****************************************************************************.
 * Model class for Recipients
 * @class Recipients
 * @author Cognizant Technology Solutions
 * @version OpServ 3.0
 * @since 23/06/2016
 * ***************************************************************************
 */
public class Recipient {
	
	private String rejectReason;
	private boolean isFavourite;	
	private Date readDate;
	private Date ackDate;
	private AckStatusType ackStatus;
	private NotificationType notifyStatus;
	private Integer recipientId;
	private boolean isTargetRecipient;
	private NoteStatus noteStatus;
	
	/**
	 * @return the rejectReason
	 */
	public String getRejectReason() {
		return rejectReason;
	}
	
	/**
	 * @param rejectReason
	 *            the rejectReason to set
	 */
	public void setRejectReason(String rejectReason) {
		this.rejectReason = rejectReason;
	}
	
	/**
	 * @return the isFavourite
	 */
	public boolean isFavourite() {
		return isFavourite;
	}
	
	/**
	 * @param isFavourite
	 *            the isFavourite to set
	 */
	public void setFavourite(boolean isFavourite) {
		this.isFavourite = isFavourite;
	}
	
	/**
	 * @return the readDate
	 */
	public Date getReadDate() {
		return readDate;
	}
	
	/**
	 * @param readDate
	 *            the readDate to set
	 */
	public void setReadDate(Date readDate) {
		this.readDate = readDate;
	}
	
	/**
	 * @return the ackDate
	 */
	public Date getAckDate() {
		return ackDate;
	}
	
	/**
	 * @param ackDate
	 *            the ackDate to set
	 */
	public void setAckDate(Date ackDate) {
		this.ackDate = ackDate;
	}
	
	/**
	 * @return the ackStatus
	 */
	public AckStatusType getAckStatus() {
		return ackStatus;
	}
	
	/**
	 * @param ackStatus
	 *            the ackStatus to set
	 */
	public void setAckStatus(AckStatusType ackStatus) {
		this.ackStatus = ackStatus;
	}
	
	/**
	 * @return the notifyStatus
	 */
	public NotificationType getNotifyStatus() {
		return notifyStatus;
	}
	
	/**
	 * @param notifyStatus
	 *            the notifyStatus to set
	 */
	public void setNotifyStatus(NotificationType notifyStatus) {
		this.notifyStatus = notifyStatus;
	}
	
	/**
	 * @return the recipientId
	 */
	public Integer getRecipientId() {
		return recipientId;
	}
	
	/**
	 * @param recipientId
	 *            the recipientId to set
	 */
	public void setRecipientId(Integer recipientId) {
		this.recipientId = recipientId;
	}

	/**
	 * @return the isTargetRecipient;
	 */
	public boolean isTargetRecipient() {
		return isTargetRecipient;
	}

	/**
	 * @param isTargetRecipient
	 *            the isTargetRecipient to set
	 */
	public void setTargetRecipient(boolean isTargetRecipient) {
		this.isTargetRecipient = isTargetRecipient;
	}

	public NoteStatus getNoteStatus() {
		return noteStatus;
	}

	public void setNoteStatus(NoteStatus noteStatus) {
		this.noteStatus = noteStatus;
	}
	
	
	
}