package com.cognizant.opserv.sp.model.cr;

import java.io.Serializable;

import com.cognizant.opserv.sp.model.CustomerCallPlan;
/**
 * ****************************************************************************.
 *
 * @class CustomerCallPlanChangeRequestDetails
 * @author Cognizant Technology Solutions
 * @version OpServ 1.0
 * @since 11/05/2016
 * ***************************************************************************
 */
public class CustomerCallPlanChangeRequestDetails 
									extends ChangeRequestLineItem 
									implements	Serializable 
{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1119132310037125183L;
	
	/**
	 * 
	 */
	private CustomerCallPlan oldCallPlanObject;
	/**
	 * 
	 */
	private CustomerCallPlan newCallPlanObject;
	
	/**
	 * @return the oldCallPlanObject
	 */
	public CustomerCallPlan getOldCallPlanObject() {
		return oldCallPlanObject;
	}
	/**
	 * @param oldCallPlanObject the oldCallPlanObject to set
	 */
	public void setOldCallPlanObject(CustomerCallPlan oldCallPlanObject) {
		this.oldCallPlanObject = oldCallPlanObject;
	}
	/**
	 * @return the newCallPlanObject
	 */
	public CustomerCallPlan getNewCallPlanObject() {
		return newCallPlanObject;
	}
	/**
	 * @param newCallPlanObject the newCallPlanObject to set
	 */
	public void setNewCallPlanObject(CustomerCallPlan newCallPlanObject) {
		this.newCallPlanObject = newCallPlanObject;
	}

}

