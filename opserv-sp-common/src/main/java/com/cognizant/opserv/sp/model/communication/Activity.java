package com.cognizant.opserv.sp.model.communication;

import java.util.Date;

public class Activity extends Communication {

	private Date activityStartDate;
	private Date activityEndDate;
	
	/**
	 * @return the activityStartDate
	 */
	public Date getActivityStartDate() {
		return activityStartDate;
	}
	
	/**
	 * @param activityStartDate
	 *            the activityStartDate to set
	 */
	public void setActivityStartDate(Date activityStartDate) {
		this.activityStartDate = activityStartDate;
	}
	
	/**
	 * @return the activityEndDate
	 */
	public Date getActivityEndDate() {
		return activityEndDate;
	}
	
	/**
	 * @param activityEndDate
	 *            the activityEndDate to set
	 */
	public void setActivityEndDate(Date activityEndDate) {
		this.activityEndDate = activityEndDate;
	}
	
}
