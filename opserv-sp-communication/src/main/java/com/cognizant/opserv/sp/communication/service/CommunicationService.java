package com.cognizant.opserv.sp.communication.service;

import java.util.List;

import com.cognizant.opserv.sp.common.communication.CommunicationStatusType;
import com.cognizant.opserv.sp.exception.CommunicationServiceException;
import com.cognizant.opserv.sp.model.auth.UserDetails;
import com.cognizant.opserv.sp.model.communication.Announcement;
import com.cognizant.opserv.sp.model.communication.Communication;
import com.cognizant.opserv.sp.model.communication.Recipient;
import com.cognizant.peg.core.common.PaginationInfo;
import com.cognizant.peg.core.exception.BusinessException;

/**
 * ****************************************************************************.
 * 
 * @class CommunicationService contains all the services for communication like email,dashboard notification
 * @author Cognizant Technology Solutions
 * @version OpServ 3.0
 * @since 23/06/2016
 * ***************************************************************************
 */
public interface CommunicationService {
	
	/**
	 * Creates the announcement.
	 *
	 * @param announcement the announcement
	 * @throws BusinessException the business exception
	 */
	public Announcement createAnnouncement(Announcement announcement)throws CommunicationServiceException;
	/**
	 * To publish an announcement
	 * @param announcement
	 * @throws BusinessException
	 */
	public void publishAnnouncement(Announcement announcement) throws CommunicationServiceException;
	
	/**
	 * To get announcements by user
	 * @param userDetails
	 * @throws BusinessException
	 */
	public List<Announcement> getAnnouncementsByUser(UserDetails userDetails) throws CommunicationServiceException;
	
	/**
	 * To acknowledge announcement by user
	 * @param communication
	 * @param recipient
	 * @throws BusinessException
	 */
	public void acknowledgeAnnouncementByUser(Communication communication, Recipient recipient) throws CommunicationServiceException;
	
	/**
	 * To get announcements by publisher
	 * @param userDetails
	 * @param type
	 * @param paginInfo
	 * @throws BusinessException
	 */
	public List<Announcement> getAnnouncementsByPublisher(UserDetails userDetails, CommunicationStatusType type, 
			PaginationInfo paginInfo) throws CommunicationServiceException;
}
