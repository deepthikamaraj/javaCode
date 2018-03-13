package com.cognizant.opserv.sp.model;

import java.io.Serializable;

public class BusinessReason extends BaseModel implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -9176700297902404051L;
	
	private String reason;
	private String description;
	private String chngType;
	private Integer businessReasonId;
	
	/**
	 * @return the reason
	 */
	public String getReason() {
		return reason;
	}
	
	/**
	 * @param reason
	 *            the reason to set
	 */
	public void setReason(String reason) {
		this.reason = reason;
	}
	
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	
	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	
	/**
	 * @return the chngType
	 */
	public String getChngType() {
		return chngType;
	}

	/**
	 * @param chngType
	 *            the chngType to set
	 */
	public void setChngType(String chngType) {
		this.chngType = chngType;
	}
	
	/**
	 * @return the businessReasonId
	 */
	public Integer getBusinessReasonId() {
		return businessReasonId;
	}

	/**
	 * @param chngType
	 *            the businessReasonId to set
	 */
	public void setBusinessReasonId(Integer businessReasonId) {
		this.businessReasonId = businessReasonId;
	}

}
