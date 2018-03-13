package com.cognizant.opserv.sp.model;

import com.cognizant.opserv.sp.common.ChangeRequestOfflineStatusType;
/**
 * ****************************************************************************.
 *
 * @class TempCR
 * @author Cognizant Technology Solutions
 * @version OpServ 1.0
 * @since 11/05/2016
 * ***************************************************************************
 */
public class OfflineRequest {
	
	/** The temp cr id. */
	private Long tempCRId;
	
	/** The trigger id. */
	private Integer triggerId;
	
	/** The cr id. */
	private Long crId;
	
	/** The json string. */
	private String jsonString;
	
	/** The offline status. */
	private ChangeRequestOfflineStatusType offlineStatus;

	/** The failed reason. */
	private String failedReason;
	/**
	 * Gets the temp cr id.
	 *
	 * @return the temp cr id
	 */
	public Long getTempCRId() {
		return tempCRId;
	}

	/**
	 * Sets the temp cr id.
	 *
	 * @param tempCRId the new temp cr id
	 */
	public void setTempCRId(Long tempCRId) {
		this.tempCRId = tempCRId;
	}

	/**
	 * Gets the trigger id.
	 *
	 * @return the trigger id
	 */
	public Integer getTriggerId() {
		return triggerId;
	}

	/**
	 * Sets the trigger id.
	 *
	 * @param triggerId the new trigger id
	 */
	public void setTriggerId(Integer triggerId) {
		this.triggerId = triggerId;
	}

	/**
	 * Gets the cr id.
	 *
	 * @return the cr id
	 */
	public Long getCrId() {
		return crId;
	}

	/**
	 * Sets the cr id.
	 *
	 * @param crId the new cr id
	 */
	public void setCrId(Long crId) {
		this.crId = crId;
	}

	/**
	 * Gets the json string.
	 *
	 * @return the json string
	 */
	public String getJsonString() {
		return jsonString;
	}

	/**
	 * Sets the json string.
	 *
	 * @param jsonString the new json string
	 */
	public void setJsonString(String jsonString) {
		this.jsonString = jsonString;
	}
	
	
	/**
	 * Gets the offline status.
	 *
	 * @return the offline status
	 */
	public ChangeRequestOfflineStatusType getOfflineStatus() {
		return offlineStatus;
	}

	/**
	 * Sets the offline status.
	 *
	 * @param offlineStatus the new offline status
	 */
	public void setOfflineStatus(ChangeRequestOfflineStatusType offlineStatus) {
		this.offlineStatus = offlineStatus;
	}
	
	/**
	 * Gets the failed reason.
	 *
	 * @return the failed reason
	 */
	public String getFailedReason() {
		return failedReason;
	}

	/**
	 * Sets the failed reason.
	 *
	 * @param failedReason the new failed reason
	 */
	public void setFailedReason(String failedReason) {
		this.failedReason = failedReason;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "TempCR [tempCRId=" + tempCRId + ", triggerId=" + triggerId
				+ ", crId=" + crId + ", jsonString=" + jsonString + "]";
	}

}
