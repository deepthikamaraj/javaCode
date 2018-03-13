package com.cognizant.opserv.sp.cr.process.internal.service;

import com.cognizant.opserv.sp.event.messages.OfflineRequestMessage;
import com.cognizant.opserv.sp.exception.AlignmentServiceException;
import com.cognizant.opserv.sp.exception.ChangeRequestServiceException;
import com.cognizant.opserv.sp.exception.ViewServiceException;

/**
 * The Interface ZipMovementRequestFacade.
 */
public interface ZipMovementRequestFacade {

	/**
	 * Move zip.
	 *
	 * @param message the message
	 * @throws ChangeRequestServiceException the change request service exception
	 * @throws AlignmentServiceException the alignment service exception
	 * @throws ViewServiceException 
	 */
	void moveZip(OfflineRequestMessage message) throws ChangeRequestServiceException, AlignmentServiceException, ViewServiceException;
}
