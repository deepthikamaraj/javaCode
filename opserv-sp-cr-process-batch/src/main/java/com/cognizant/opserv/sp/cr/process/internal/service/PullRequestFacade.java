package com.cognizant.opserv.sp.cr.process.internal.service;

import com.cognizant.opserv.sp.event.messages.OfflineRequestMessage;
import com.cognizant.opserv.sp.exception.AffiliationServiceException;
import com.cognizant.opserv.sp.exception.AlignmentServiceException;
import com.cognizant.opserv.sp.exception.ChangeRequestServiceException;
import com.cognizant.opserv.sp.exception.SalesPositionServiceException;
import com.cognizant.opserv.sp.exception.ViewServiceException;

// TODO: Auto-generated Javadoc
/**
 * The Interface PullRequestFacade.
 */
public interface PullRequestFacade {

	/**
	 * Pull customer - Placeholder for message. This will be the message object
	 * pulled from online.
	 *
	 * @param message the message
	 * @throws ChangeRequestServiceException the change request service exception
	 * @throws AlignmentServiceException the alignment service exception
	 * @throws AffiliationServiceException 
	 * @throws ViewServiceException
	 * @throws SalesPositionServiceException
	 */
	void pullCustomer(OfflineRequestMessage message) throws ChangeRequestServiceException, AlignmentServiceException, AffiliationServiceException, ViewServiceException, SalesPositionServiceException;
}
