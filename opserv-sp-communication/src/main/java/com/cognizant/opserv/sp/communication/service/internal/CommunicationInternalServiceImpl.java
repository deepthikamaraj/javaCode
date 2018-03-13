package com.cognizant.opserv.sp.communication.service.internal;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cognizant.opserv.sp.common.communication.CommunicationStatusType;
import com.cognizant.opserv.sp.communication.persitence.dao.service.CommunicationDAOService;
import com.cognizant.opserv.sp.model.communication.Announcement;
import com.cognizant.peg.core.exception.BusinessException;

/**
 * ****************************************************************************.
 *
 * @class CommunicationServiceImpl contains implementation for  CommunicationService
 * @author Cognizant Technology Solutions
 * @version OpServ 3.0
 * @since 19/07/2016
 * ***************************************************************************
 */
@Service("communicationInternalService")
public class CommunicationInternalServiceImpl implements CommunicationInternalService {

	@Autowired
	private CommunicationDAOService communicationDAOService;
	/**
	 * Creates the and publish announcement.
	 *
	 * @param announcement the announcement
	 * @throws BusinessException the business exception
	 */
	@Override
	@Transactional
	public void createAndPublishAnnouncement(Announcement announcement)
			throws BusinessException {
		try {
			communicationDAOService.createAnnouncement(announcement);
			communicationDAOService.publishAnnouncement(announcement.getId(), new Date(),announcement.getCreatedBy(),CommunicationStatusType.PUBLISHED.getId());
		} catch (Exception e) {
			throw new BusinessException("Exception occured while creating announcement", e.getMessage(),e);
		}
	}
}
