package com.cognizant.opserv.sp.communication.service.internal;

import com.cognizant.opserv.sp.model.communication.Announcement;
import com.cognizant.peg.core.exception.BusinessException;

/**
 * ****************************************************************************.
 * 
 * @class CommunicationInternalService contains all the internal services for communication like email,dashboard notification
 * @author Cognizant Technology Solutions
 * @version OpServ 3.0
 * @since 19/07/2016
 * ***************************************************************************
 */
public interface CommunicationInternalService {

	/**
	 * Creates the and publish announcement.
	 *
	 * @param announcement the announcement
	 * @throws BusinessException the business exception
	 */
	public void createAndPublishAnnouncement(Announcement announcement)throws BusinessException;
}
