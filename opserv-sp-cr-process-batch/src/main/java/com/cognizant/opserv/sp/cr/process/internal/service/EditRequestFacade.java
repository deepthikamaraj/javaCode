package com.cognizant.opserv.sp.cr.process.internal.service;
import com.cognizant.opserv.sp.event.messages.OfflineRequestMessage;
import com.cognizant.opserv.sp.exception.AlignmentServiceException;
import com.cognizant.opserv.sp.exception.CallPlanServiceException;
import com.cognizant.opserv.sp.exception.ChangeRequestServiceException;
import com.cognizant.opserv.sp.exception.MetricExecutionException;
import com.cognizant.opserv.sp.exception.SalesPositionServiceException;
import com.cognizant.opserv.sp.exception.ViewServiceException;

/**
 * 
 * @author Cognizant Technology Solutions
 *
 */
public interface EditRequestFacade {

	//TODO : Temporary placeholder for message. This will be the message object pushed from online.
	void editCustomer(OfflineRequestMessage message) throws ChangeRequestServiceException, AlignmentServiceException, MetricExecutionException, CallPlanServiceException, ViewServiceException, SalesPositionServiceException;
}
