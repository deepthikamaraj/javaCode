package com.cognizant.opserv.sp.model;

import java.io.Serializable;
import com.cognizant.opserv.sp.common.CallPlanType;
import com.cognizant.opserv.sp.common.ChangeRequestStatusType;

/**
 * ****************************************************************************.
 *
 * @class CustomerCallPlan
 * @author Cognizant Technology Solutions
 * @version OpServ 1.0
 * @since 11/05/2016
 * ***************************************************************************
 */
public class CustomerCallPlan extends BaseModel implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -1763431832599268818L;
	
	/**
	 * plannedCalls is the planned number of calls
	 */
	private int plannedCalls;

	/**
	 * type is the call plan type
	 */
	private CallPlanType type;
	
	/**
	 * List of call plan items
	 *//*
	private List<CallPlanItem> callPlanItems;*/
	
	/**
	 * Call Plan Configuration
	 *//*
	private CallPlanConfiguration callPlanConfig;*/
	/**
	 * Call Plan changeRequestStatusType
	 */
	private ChangeRequestStatusType changeRequestStatusType;
	
	/**
	 * Gets the change request status type.
	 *
	 * @return the change request status type
	 */
	public ChangeRequestStatusType getChangeRequestStatusType() {
		return changeRequestStatusType;
	}

	/**
	 * Sets the change request status type.
	 *
	 * @param changeRequestStatusType the new change request status type
	 */
	public void setChangeRequestStatusType(
			ChangeRequestStatusType changeRequestStatusType) {
		this.changeRequestStatusType = changeRequestStatusType;
	}

	/**
	 * @return the plannedCalls
	 */
	public int getPlannedCalls() {
		return plannedCalls;
	}

	/**
	 * @param plannedCalls
	 *            the plannedCalls to set
	 */
	public void setPlannedCalls(int plannedCalls) {
		this.plannedCalls = plannedCalls;
	}

	/**
	 * @return the type
	 */
	public CallPlanType getType() {
		return type;
	}

	/**
	 * @param type - the type to set
	 */
	public void setType(CallPlanType type) {
		this.type = type;
	}
/*
	public List<CallPlanItem> getCallPlanItems() {
		return callPlanItems;
	}

	public void setCallPlanItems(List<CallPlanItem> callPlanItems) {
		this.callPlanItems = callPlanItems;
	}*/
/*
	public CallPlanConfiguration getCallPlanConfig() {
		return callPlanConfig;
	}

	public void setCallPlanConfig(CallPlanConfiguration callPlanConfig) {
		this.callPlanConfig = callPlanConfig;
	}
	*/
	

}
