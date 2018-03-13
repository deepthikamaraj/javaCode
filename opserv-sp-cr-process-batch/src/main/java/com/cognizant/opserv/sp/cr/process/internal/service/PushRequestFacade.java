package com.cognizant.opserv.sp.cr.process.internal.service;
import com.cognizant.opserv.sp.event.messages.OfflineRequestMessage;
import com.cognizant.opserv.sp.exception.AffiliationServiceException;
import com.cognizant.opserv.sp.exception.AlignmentServiceException;
import com.cognizant.opserv.sp.exception.ChangeRequestServiceException;
import com.cognizant.opserv.sp.exception.SalesPositionServiceException;
import com.cognizant.opserv.sp.exception.ViewServiceException;

/**
 * The Interface PushRequestFacade.
 */
public interface PushRequestFacade {

	//TODO : Temporary placeholder for message. This will be the message object pushed from online.
	/**
	 * Push customer - Placeholder for message. This will be the message object
	 * pushed from online.
	 * 
	 * @param message
	 *            the message
	 * @throws ChangeRequestServiceException
	 *             the change request service exception
	 * @throws AlignmentServiceException
	 * @throws AffiliationServiceException 
	 * @throws ViewServiceException
	 * @throws SalesPositionServiceException
	 */
	void pushCustomer(OfflineRequestMessage message) throws ChangeRequestServiceException, AlignmentServiceException, AffiliationServiceException, ViewServiceException, SalesPositionServiceException;
}
