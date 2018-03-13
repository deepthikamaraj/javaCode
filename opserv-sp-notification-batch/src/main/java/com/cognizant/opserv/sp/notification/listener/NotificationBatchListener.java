/******************************************************************************
 *  
 * @class NotificationBatchListener handles notifications.
 * @author Cognizant Technology Solutions
 * @version OpServ 3.0
 * @since 06/01/2016
 *
 *****************************************************************************/
package com.cognizant.opserv.sp.notification.listener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cognizant.opserv.sp.common.CommonConstants;
import com.cognizant.opserv.sp.common.ContactType;
import com.cognizant.opserv.sp.common.NoteStatus;
import com.cognizant.opserv.sp.common.NotificationType;
import com.cognizant.opserv.sp.common.communication.CommunicationStatusType;
import com.cognizant.opserv.sp.common.communication.CommunicationType;
import com.cognizant.opserv.sp.communication.service.internal.CommunicationInternalService;
import com.cognizant.opserv.sp.core.dao.TPersContactDAO;
import com.cognizant.opserv.sp.model.NotificationMessage;
import com.cognizant.opserv.sp.model.communication.Announcement;
import com.cognizant.opserv.sp.model.communication.Recipient;
import com.cognizant.opserv.tenant.util.BatchMultiTenancyUtil;
import com.cognizant.peg.core.exception.BusinessException;
import com.cognizant.peg.core.logger.OpservLogger;
import com.cognizant.peg.core.logger.OpservLoggerFactory;
import com.cognizant.peg.notification.client.service.NotifyClient;
import com.cognizant.peg.notification.core.common.NotifyMessage;
import com.cognizant.peg.notification.core.common.NotifyServiceException;
import com.cognizant.peg.notification.core.common.NotifyType;
/**
 * ****************************************************************************.
 * @class NotificationBatchListener which receives messages from notification queue
 * @author Cognizant Technology Solutions
 * @version OpServ 3.0
 * @since 23/06/2016
 * ***************************************************************************
 */
@Component("notificationBatchListener")
public class NotificationBatchListener implements MessageListener {


	/** The Constant LOGGER. */
	private static final OpservLogger  LOGGER = OpservLoggerFactory.getOpservLogger(NotificationBatchListener.class);

	@Autowired
    NotifyClient notifyClient;
	
	@Autowired
	private CommunicationInternalService communicationInternalService;
	
	@Autowired
	TPersContactDAO tPersContactDAO;

	/**
	 * @Method onMessage - This method executes the message
	 * @param message - holds message info
	 * @return void	
	 */
	public void onMessage(Message message) {

		if (message instanceof ObjectMessage) {
			try {
				
				NotificationMessage notifyMessage = (NotificationMessage) (((ObjectMessage) message).getObject());
				Map<String, String> args =notifyMessage.getParams();
				String tenantCode= args.get(CommonConstants.TENANT_CODE);
				if(null != tenantCode){
					BatchMultiTenancyUtil.setTenantContext(tenantCode);
				}
				processMessage(notifyMessage);
				message.acknowledge();
			} catch (JMSException e) {
				LOGGER.error("An JMS Exception occured", e);
			} 
			catch (Exception e) {
				LOGGER.error("An Exception occured in notification", e);
			} 
		}else{
			throw new IllegalArgumentException("Message must be of type ObjectMessage");
		}
	}

	/**
	 * @method processMessage - Process message.Which includes below actions
	 * 	1. Save the message details DB as announcement
	 *  2. Pass the message for notification
	 *  3. Publish announcement
	 * @param message the message
	 * @return void	
	 * @throws JMSException 
	 * @throws NotifyServiceException 
	 * @throws BusinessException 
	 */
	private void processMessage(NotificationMessage notifyMessage) throws NotifyServiceException, JMSException, BusinessException{
		LOGGER.info("Make an anouncement for the message here....."+notifyMessage );
		createAnounceMent(notifyMessage);	
		NotifyMessage message=convertNotificatnMsgToNoifyMsg(notifyMessage);
		notifyClient.send(message);
	}
	
	/**
	 * To create a new announcement from a message
	 *
	 * @param notifyMessage the notify message
	 * @throws BusinessException the business exception
	 */
	private void createAnounceMent(NotificationMessage notifyMessage)throws BusinessException{
		LOGGER.info("Make an anouncement for the message here....." );
		Announcement announcement = configureAnnouncement(notifyMessage);
		communicationInternalService.createAndPublishAnnouncement(announcement);
	}
	
	
	/**
	 * To create an announcement from a message.
	 *
	 * @param notifyMessage the notify message
	 * @return the announcement
	 */
	private Announcement configureAnnouncement(NotificationMessage notifyMessage){
		
        List<Integer> staffIds=new ArrayList<Integer>();
        List<Recipient> recipients=new ArrayList<Recipient>();
        
		Announcement announcement= new Announcement();
		announcement.setAckRequired(false);  
		announcement.setDetails(notifyMessage.getContent());
		announcement.setName(notifyMessage.getSubject());
		announcement.setCreatedDate(new Date());
		
		Map<String, String> args =notifyMessage.getParams();
		
		String userId= args.get(CommonConstants.USER_ID);
		
		if(null != userId)
			announcement.setCreatedBy(Integer.valueOf(userId)); 
		announcement.setPriority(true);
		announcement.setStartDate(new Date());
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.add(Calendar.DATE, 15); // add 10 days
		announcement.setEndDate(cal.getTime());
		announcement.setStatus(CommunicationStatusType.DRAFT);
		announcement.setType(CommunicationType.ANNOUNCEMENT);
		announcement.setNotification(NotificationType.EMAIL);
		notifyMessage.getNotifyType();
		if(null!=notifyMessage.getRecipients()){
			staffIds.addAll(notifyMessage.getRecipients());
		}
		if(null!=notifyMessage.getBccs()){
			staffIds.addAll(notifyMessage.getBccs());
		}
		if(null!=notifyMessage.getCcs()){
			staffIds.addAll(notifyMessage.getCcs());
		}
		if(null!=staffIds && !staffIds.isEmpty()){
			for(Integer staffId :staffIds ){
				Recipient recipient=new Recipient();
				recipient.setRecipientId(staffId);
				recipient.setTargetRecipient(true);
				recipient.setNoteStatus(NoteStatus.NOTIFIED);
				recipients.add(recipient);
			}
		}
		announcement.setRecipients(recipients);
		
		if(null != notifyMessage.getClientCode())
			announcement.setTenantId(Short.valueOf(notifyMessage.getClientCode().toString())); 
		return announcement;
	}
	
	
	/**
	 * Convert notificatn msg to noify msg.
	 *
	 * @param notificationMessage the notification message
	 * @return the notify message
	 */
	private NotifyMessage convertNotificatnMsgToNoifyMsg(
			NotificationMessage notificationMessage) {

		NotifyMessage message = new NotifyMessage();

		message.setClientCode(notificationMessage.getClientCode());
		if(notificationMessage.getNotifyType().getId()== 1){
			message.setNotifyType(NotifyType.EMAIL);
		}
		message.setMsgTemplate(notificationMessage.getMsgTemplate());

		message.setParams(notificationMessage.getParams());

		message.setAttachFiles(notificationMessage.getAttachFiles());

		if (null != notificationMessage.getRecipients()) {
			List<String> eMailRecipient = getEmailids(notificationMessage
					.getRecipients());
			message.setRecipients(eMailRecipient);
		}

		if (null != notificationMessage.getBccs()) {
			List<String> eMailBccs = getEmailids(notificationMessage.getBccs());
			message.setBccs(eMailBccs);
		}

		if (null != notificationMessage.getCcs()) {
			List<String> getCcs = getEmailids(notificationMessage.getCcs());
			message.setCcs(getCcs);
		}
		return message;

	}
	
	/**
	 * Gets the emailids.
	 *
	 * @param staffIds the staff ids
	 * @return the emailids
	 */
	private List<String> getEmailids(List<Integer> staffIds) {

		return  tPersContactDAO.FindTPersEmailByStaffId(
				staffIds, ContactType.EMAIL.getId());
	}
}