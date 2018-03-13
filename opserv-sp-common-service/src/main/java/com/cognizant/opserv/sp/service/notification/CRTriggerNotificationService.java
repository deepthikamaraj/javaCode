package com.cognizant.opserv.sp.service.notification;

import com.cognizant.opserv.sp.model.auth.UserDetails;

/**
 * ****************************************************************************.
 *
 * @interface CRTriggerNotificationService, to trigger a notification event
 * @author Cognizant Technology Solutions
 * @version OpServ 3.0
 * @since 20/09/2016
 * ***************************************************************************
 */
public interface CRTriggerNotificationService {

	/**
	 * Create a new message for the change request and publish to queue.
	 *
	 * @param ChngReqId the chng req id
	 * @param crType as customer or zip
	 * @param userDetails the user details
	 * @param action the action , possible values CommonConstants-{CR_ACTION_SUBMIT,CR_ACTION_APPROVE,CR_ACTION_REJECT}
	 */
	public void  triggerCrNotification(Long ChngReqId,UserDetails userDetails,String action);
}
