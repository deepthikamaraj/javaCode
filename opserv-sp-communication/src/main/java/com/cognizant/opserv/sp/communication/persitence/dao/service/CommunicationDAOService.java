package com.cognizant.opserv.sp.communication.persitence.dao.service;

import java.util.Date;
import java.util.List;

import com.cognizant.opserv.sp.common.communication.CommunicationStatusType;
import com.cognizant.opserv.sp.model.auth.UserDetails;
import com.cognizant.opserv.sp.model.communication.Announcement;
import com.cognizant.opserv.sp.model.communication.Communication;
import com.cognizant.opserv.sp.model.communication.Recipient;
import com.cognizant.peg.core.common.PaginationInfo;
import com.cognizant.peg.core.exception.OpservDataAccessException;

/**
 * ****************************************************************************.
 * @Interface CommunicationDAOService contains methods to communicate between Service and DAO
 * @author Cognizant Technology Solutions
 * @version OpServ 3.0
 * @since 23/06/2016
 * ***************************************************************************
 */
public interface CommunicationDAOService {

	
	/**
	 * To publish an announcement
	 * @param announcement
	 * @throws OpservDataAccessException
	 */
	public Announcement createAnnouncement(Announcement announcement) throws OpservDataAccessException;
	
	/**
	 * To publish an announcement
	 * @param announcement
	 * @param userId
	 * @throws OpservDataAccessException
	 */
	public void publishAnnouncement(Long communicationId,Date publishedDate,Integer userId,int status) throws OpservDataAccessException;
	
	/**
	 * To get all announcements by user
	 * @param userDetails
	 * @throws OpservDataAccessException
	 */
	public List<Announcement> getAnnouncementsByUser(UserDetails userDetails) throws OpservDataAccessException;
	
	/**
	 * To acknowledge announcement by user
	 * @param recipient
	 * @throws OpservDataAccessException
	 */
	public void acknowledgeAnnouncementByUser(Communication communication, Recipient recipient) throws OpservDataAccessException;
	
	/**
	 * To get announcements by publisher
	 * @param userDetails
	 * @throws OpservDataAccessException
	 */
	public List<Announcement> getAnnouncementsByPublisher(UserDetails userDetails, CommunicationStatusType type,
			PaginationInfo paginInfo) throws OpservDataAccessException;
}
