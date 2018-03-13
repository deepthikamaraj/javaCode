package com.cognizant.opserv.sp.communication.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.jms.JMSException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cognizant.opserv.common.domain.MessageTemplate;
import com.cognizant.opserv.sp.common.ContactType;
import com.cognizant.opserv.sp.common.NotificationType;
import com.cognizant.opserv.sp.common.communication.CommunicationStatusType;
import com.cognizant.opserv.sp.communication.persitence.dao.service.CommunicationDAOService;
import com.cognizant.opserv.sp.communication.service.CommunicationService;
import com.cognizant.opserv.sp.core.dao.TPersContactDAO;
import com.cognizant.opserv.sp.exception.CommunicationServiceException;
import com.cognizant.opserv.sp.exception.CommunicationServiceException.CommunicationServiceExceptionCode;
import com.cognizant.opserv.sp.model.auth.UserDetails;
import com.cognizant.opserv.sp.model.communication.Announcement;
import com.cognizant.opserv.sp.model.communication.Communication;
import com.cognizant.opserv.sp.model.communication.Recipient;
import com.cognizant.peg.core.common.PaginationInfo;
import com.cognizant.peg.core.exception.BusinessException;
import com.cognizant.peg.core.exception.OpservDataAccessException;
import com.cognizant.peg.core.logger.OpservLogger;
import com.cognizant.peg.core.logger.OpservLoggerFactory;
import com.cognizant.peg.notification.client.service.NotifyClient;
import com.cognizant.peg.notification.core.common.NotifyMessage;
import com.cognizant.peg.notification.core.common.NotifyServiceException;
import com.cognizant.peg.notification.core.common.NotifyType;

/**
 * ****************************************************************************.
 *
 * @class CommunicationServiceImpl contains implementation for  CommunicationService
 * @author Cognizant Technology Solutions
 * @version OpServ 3.0
 * @since 23/06/2016
 * ***************************************************************************
 */
@Service("communicationService")
public class CommunicationServiceImpl implements CommunicationService {

	@Autowired
	private CommunicationDAOService communicationDAOService;
	
	@Autowired
    private NotifyClient notifyClient;
	
	@Autowired
	private TPersContactDAO tPersContactDAO;
	
	private static final OpservLogger LOGGER = OpservLoggerFactory.getOpservLogger(CommunicationServiceImpl.class);
	/**
	 * Creates the announcement.
	 *
	 * @param announcement the announcement
	 * @throws BusinessException the business exception
	 */
	@Override
	@Transactional
	public Announcement createAnnouncement(Announcement announcement)
			throws CommunicationServiceException {
		try {
			return communicationDAOService.createAnnouncement(announcement);
		} catch (OpservDataAccessException e) {
			LOGGER.error("This issue occurred while creating announcement :"+ e.getMessage());
			Object[] args = new Object[]{announcement.getName()};
			throw new CommunicationServiceException(CommunicationServiceExceptionCode.COMM_SER_EX_CD_001, e.getMessage(),args,e);
		}
		
	}
	
	/**
	 * To publish an announcement
	 * @param announcement
	 * @throws BusinessException
	 */
	@Override
	@Transactional
	public void publishAnnouncement(Announcement announcement)
			throws CommunicationServiceException {
		try {
			communicationDAOService.publishAnnouncement(announcement.getId(), announcement.getPublishedDate(), announcement.getUpdatedBy(),announcement.getStatus().getId());
			
			if(announcement.getNotification().equals(NotificationType.EMAIL) || announcement.getNotification().equals(NotificationType.BOTH)) {
				NotifyMessage notifyMessage = convertNotificatnMsgToNoifyMsg(announcement);
				notifyClient.send(notifyMessage);
			}
		} catch (OpservDataAccessException e) {
			LOGGER.error("This issue occurred while publishing announcement :"+ e.getMessage());
			Object[] args = new Object[]{announcement.getName()};
			throw new CommunicationServiceException(CommunicationServiceExceptionCode.COMM_SER_EX_CD_002, e.getMessage(),args,e);
		} catch (NotifyServiceException e) {
			LOGGER.error("Issue while sending message to queue :"+ e.getMessage());
			Object[] args = new Object[]{announcement.getName()};
			throw new CommunicationServiceException(CommunicationServiceExceptionCode.COMM_SER_EX_CD_002, e.getMessage(),args,e);
		} catch (JMSException e) {
			LOGGER.error("Issue while publishing message to queue :"+ e.getMessage());
			Object[] args = new Object[]{announcement.getName()};
			throw new CommunicationServiceException(CommunicationServiceExceptionCode.COMM_SER_EX_CD_002, e.getMessage(),args,e);
		}
	}
	
	/**
	 * To get announcements
	 * @param userDetails
	 * @throws BusinessException
	 */
	@Override
	@Transactional(readOnly = true)
	public List<Announcement> getAnnouncementsByUser(
			UserDetails userDetails) throws CommunicationServiceException {
		try {
			
			if(null == userDetails || null == userDetails.getUserId() || null == userDetails.getTenantId()) {
				String params = "userDetails ="+ userDetails +", tenant_id = "+ userDetails.getTenantId();
				LOGGER.error("Validation failed "+params);
				Object[] args = new Object[]{params};
				throw new  CommunicationServiceException(args);
			}
			return communicationDAOService.getAnnouncementsByUser(userDetails);
		} catch(OpservDataAccessException dataAccessException) {
			
			LOGGER.error("Issue occurred fetching announcement for the user :"+ userDetails.getUserName()+":"+dataAccessException.getMessage());
			Object[] args = new Object[]{userDetails.getUserName()};
			throw new CommunicationServiceException(CommunicationServiceExceptionCode.COMM_SER_EX_CD_003, dataAccessException.getMessage(),args,dataAccessException);
		}
	}

	/**
	 * To acknowledge Announcement
	 * @param communication
	 * @param recipient
	 * @throws BusinessException
	 */
	@Override
	@Transactional
	public void acknowledgeAnnouncementByUser(Communication communication, Recipient recipient)
			throws CommunicationServiceException {
		try {
			
			if(null == communication || null == communication.getId() || null == recipient || null == recipient.getAckStatus() || 
					null == recipient.getAckStatus().getId() ||	null == recipient.getRecipientId() || null == communication.getTenantId()) {
				
				String params = "communication ="+ communication +", tenant_id = "+ communication.getTenantId();
				LOGGER.error("Validation failed "+params);
				Object[] args = new Object[]{params};
				throw new  CommunicationServiceException(args);
			}
			communicationDAOService.acknowledgeAnnouncementByUser(communication, recipient);
		} catch(OpservDataAccessException dataAccessException) {
			LOGGER.error("Issue occurred while acknowledging the announcement"+dataAccessException.getMessage());
			Object[] args = new Object[]{communication.getName()};
			throw new CommunicationServiceException(CommunicationServiceExceptionCode.COMM_SER_EX_CD_004, dataAccessException.getMessage(),args,dataAccessException);
		}
	}
	
	/**
	 * To get announcements
	 * @param userDetails
	 * @param type
	 * @param paginInfo
	 * @throws BusinessException
	 */
	@Override
	@Transactional(readOnly = true)
	public List<Announcement> getAnnouncementsByPublisher(
			UserDetails userDetails, CommunicationStatusType type, PaginationInfo paginInfo) throws CommunicationServiceException {
		try {
			
			if(null == userDetails || null == userDetails.getUserId() || null == userDetails.getTenantId() || null == type || 
					null == paginInfo) {
				
				String params = "userDetails ="+ userDetails +", tenant_id = "+ userDetails.getTenantId();
				LOGGER.error("Validation failed "+params);
				Object[] args = new Object[]{params};
				throw new  CommunicationServiceException(args);
			}
			return communicationDAOService.getAnnouncementsByPublisher(userDetails, type, paginInfo);
			
		} catch(OpservDataAccessException dataAccessException) {
			LOGGER.error("Issue occurred while fetching announcement by publisher :"+dataAccessException.getMessage());
			Object[] args = new Object[]{userDetails.getUserName()};
			throw new CommunicationServiceException(CommunicationServiceExceptionCode.COMM_SER_EX_CD_005, dataAccessException.getMessage(),args,dataAccessException);
		}
	}
	
	/**
	 * Convert notificatn msg to noify msg.
	 *
	 * @param notificationMessage the notification message
	 * @return the notify message
	 */
	private NotifyMessage convertNotificatnMsgToNoifyMsg(Announcement announcement) {

		NotifyMessage message = new NotifyMessage();

		message.setClientCode(Integer.valueOf(announcement.getTenantId()));
		message.setNotifyType(NotifyType.EMAIL);
		message.setMsgTemplate(MessageTemplate.ANNOUNCEMENT_PUBLISHED.toString());

		if(null != announcement.getDocument()){
			List<String> docLocs = new ArrayList<>();
			docLocs.add(announcement.getDocument().getDocLocation());
			message.setAttachFiles(docLocs);
		}
		List<Integer> staffIds = new ArrayList<Integer>(); 
		for (Recipient recipient : announcement.getRecipients()) {
			staffIds.add(recipient.getRecipientId());
		}
		if (null != announcement.getRecipients()) {
			List<String> eMailRecipient = tPersContactDAO.FindTPersEmailByStaffId(
					staffIds, ContactType.EMAIL.getId());
			message.setRecipients(eMailRecipient);
		}
		return message;
	}

}
