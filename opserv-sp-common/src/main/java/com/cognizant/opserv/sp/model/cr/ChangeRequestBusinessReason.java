package com.cognizant.opserv.sp.model.cr;

import java.io.Serializable;

import com.cognizant.opserv.sp.common.ChangeRequestTriggerType;
import com.cognizant.opserv.sp.model.BaseModel;
import com.cognizant.opserv.sp.model.SalesPosition;

public class ChangeRequestBusinessReason extends BaseModel implements Serializable{

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -4032178554436663425L;
	
	private ChangeRequestTriggerType trigger;
	
	private SalesPosition salesPosition;
	
	private String changeRequestType;
	
	private Integer businessReason;
	
	private Integer categoryId;

	/**
	 * @return the trigger
	 */
	public ChangeRequestTriggerType getTrigger() {
		return trigger;
	}

	/**
	 * @param trigger the trigger to set
	 */
	public void setTrigger(ChangeRequestTriggerType trigger) {
		this.trigger = trigger;
	}

	/**
	 * @return the salesPosition
	 */
	public SalesPosition getSalesPosition() {
		return salesPosition;
	}

	/**
	 * @param salesPosition the salesPosition to set
	 */
	public void setSalesPosition(SalesPosition salesPosition) {
		this.salesPosition = salesPosition;
	}

	/**
	 * @return the changeRequestType
	 */
	public String getChangeRequestType() {
		return changeRequestType;
	}

	/**
	 * @param changeRequestType the changeRequestType to set
	 */
	public void setChangeRequestType(String changeRequestType) {
		this.changeRequestType = changeRequestType;
	}

	/**
	 * @return the businessReason
	 */
	public Integer getBusinessReason() {
		return businessReason;
	}

	/**
	 * @param businessReason the businessReason to set
	 */
	public void setBusinessReason(Integer businessReason) {
		this.businessReason = businessReason;
	}

	/**
	 * @return the categoryId
	 */
	public Integer getCategoryId() {
		return categoryId;
	}

	/**
	 * @param categoryId the categoryId to set
	 */
	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

}
